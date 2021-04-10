package br.com.emersonmorgado.peso.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.service.AuthoritiesService;
import br.com.emersonmorgado.peso.service.ConfigUserService;


@Controller
public class HomeController {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private ConfigUserService configUserService;

	@GetMapping
	@RequestMapping("/")
	public String root(Model model, Principal principal, Authentication authentication) {
	
		try {
			if(!principal.getName().isEmpty()) {
				authoritiesService.setAuthority(authentication);
				model.addAttribute(authoritiesService);
				configUserProfile();
				return "user/home";
			}
		} catch (Exception e) {
			System.out.println("Acesso por usuário não logado");
		}
		return "login";
	}
	
	@GetMapping
	@RequestMapping("/home")
	public String home(Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		configUserProfile();
		return "user/home";
	}
	
	private void configUserProfile() {
		configUserService.setUserProfile(authoritiesService);
	}

}
