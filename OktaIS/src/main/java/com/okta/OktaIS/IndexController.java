package com.okta.OktaIS;


import java.nio.charset.StandardCharsets;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.xml.util.XMLHelper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLAuthenticationToken;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.util.SAMLUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

 	
	@RequestMapping(value = "/")	
	  public String submitRequest(Model model, Authentication   principal) throws MessageEncodingException {
	    try {
	    	
	    	SAMLCredential credential = (SAMLCredential) principal.getCredentials();	    	
	    	model.addAttribute("username",credential.getNameID().getValue());
	    	model.addAttribute("firstname",credential.getAttributeAsString("firstName"));
	    	model.addAttribute("user",credential.getAttributeAsString("login"));
	    	model.addAttribute("lastname",credential.getAttributeAsString("lastName"));
	    	return "index";
	    } catch(UsernameNotFoundException ex) {
	      return "User doesnot exists, " + HttpStatus.UNAUTHORIZED;
	    }
	  }	
	
}