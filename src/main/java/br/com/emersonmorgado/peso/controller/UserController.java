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

import br.com.emersonmorgado.peso.controller.dto.UpdateEmailDto;
import br.com.emersonmorgado.peso.controller.dto.UpdatePassDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateProfileDto;
import br.com.emersonmorgado.peso.controller.dto.UserProfileDto;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.repository.UserProfileRepository;
import br.com.emersonmorgado.peso.repository.UserRepository;
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
	public String geProfile(UserProfileDto userProfileDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		userService.setUserProfile(userService.getUserFindByUsername(authentication.getName()), userProfileDto);
		return "user/profile";
	}
	
	@PostMapping("profile")
	public String updateProfile(UserProfileDto userProfileDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		userService.updateUserProfile(userService.getUserFindByUsername(authentication.getName()), userProfileDto);
		return "redirect:profile";
	}
	
	@GetMapping("profile-update-pass")
	public String updatePassword(UpdatePassDto updatePassDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		return "user/updateProfileFormPass";
	}
	@PostMapping("profile-update-pass")
	public String updateUserPass(@Valid UpdatePassDto updatePassDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			return "user/updateProfileFormPass";
		}
		try {
			userService.updatePassUserForm(updatePassDto, authentication.getName());
		} catch (Exception e) {
			model.addAttribute("serverInfo", e.getMessage());
			return "user/updateProfileFormPass";
		}
		return "redirect:/home";
	}
	@GetMapping("profile-update-email")
	public String updateEmail(UpdateEmailDto updateEmailDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		User user = userService.getUserFindByUsername(authentication.getName());
		updateEmailDto.setUser(user);
		return "user/updateProfileFormEmail";
	}

	@PostMapping("profile-update-email")
	public String updateUserEmail(@Valid UpdateEmailDto updateEmailDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			User user = userService.getUserFindByUsername(authentication.getName());
			updateEmailDto.setUser(user);
			return "user/updateProfileFormEmail";
		}
		model.addAttribute("serverInfo", userService.updateEmail(updateEmailDto, authentication.getName()));
		return "user/updateProfileFormEmail";
	}
}
