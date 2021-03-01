package br.com.emersonmorgado.peso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emersonmorgado.peso.controller.dto.UpdateProfileDto;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.service.AuthoritiesService;
import br.com.emersonmorgado.peso.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("profile")
	public String updateUserForm(UpdateProfileDto updateProfileDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		User user = userService.getFindByUsername(authentication.getName());
		updateProfileDto.setUser(user);
		return "user/updateProfileForm";
	}

	@PostMapping("profile-update")
	public String updateUser(@Valid UpdateProfileDto updateProfileDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			return "user/updateProfileForm";
		}
		try {
			userService.updateProfile(updateProfileDto);
		} catch (Exception e) {
			model.addAttribute("passError", e.getMessage());
			return "user/updateProfileForm";
		}
		return "redirect:/home";
	}
}
