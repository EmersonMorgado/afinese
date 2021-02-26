package br.com.emersonmorgado.peso.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping
	@RequestMapping("/login")
	public String home(Principal principal) {
		try {
			if(!principal.getName().isEmpty()) {
				return "redirect:/";
			}
		} catch (Exception e) {
			
		}		
		return "login";
	}
}
