package com.hmsy.prpwc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hmsy.sec.SECConstants;
import com.hmsy.sec.Security;
import com.hmsy.sec.SecurityPrefs;
import com.hmsy.shr.action.ActionToken;


@Controller
public class HomeController extends AbstractController{
	
	@RequestMapping("/shr/Controller")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		try {
			final Security security = new Security();
			final HttpSession session = request.getSession();
			final ActionToken actionToken = new ActionToken();
			
			security.setXLAT(request.getParameter("XLAT"), security, actionToken); 
			if (security.isValidContext(actionToken.getData()) && !StringUtils.isEmpty(security.getUser())) { 
				session.setAttribute("sec", (Object)security);
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				session.setAttribute("today", df.format(new Date()));
			} 
			
			String role = security.getRole();
			String usernm = security.getUserName();
			String context = security.getContext();
			String prov_id = SecurityPrefs.getPreferenceBean(SECConstants.CTX_SYSTEM, "", security.getUser(), "", "PRV.PROVIDER_MASTER.PROVIDER_ID").getValue();
            String client_cd = SecurityPrefs.getPreferenceBean(SECConstants.CTX_SYSTEM, "", security.getUser(), "", "PRV.PROVIDER_MASTER.CLIENT_CD").getValue();
            
            System.out.println("Role:"+role+"usernm:"+usernm+"context:"+context+"provid:"+prov_id+"client_cd:"+client_cd);
	        if(security.getRole().equals("HMS") || security.getRole().equals("ADM")){
	        	role = "Y";
	        }else if(security.getRole().equals("PRV")){
	        	role = "N";
	        }
	        
			
		} catch (Exception e) {
			logger.error("Exception in "+this.getClass().getName()+": handleRequestInternal(HttpServletRequest  request,Model model) " + e.getMessage(),e);
		}
		return new ModelAndView("redirect:" + "/himps");
	}

}
