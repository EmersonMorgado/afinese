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
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@GetMapping("profile")
	public String updateUserForm(UserProfileDto userProfileDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		User user = userService.getFindByUsername(authentication.getName());
		userProfileDto.setUser(user);
//		UserProfile userProfile = new UserProfile();
//		LocalDate birthday =  LocalDate.of(1981, 10, 12);
//		userProfile.setBirthday(birthday);
//		userProfile.setHeight(1);
//		userProfile.setName("Emerson do Nascimento Silvestre Morgado");
//		userProfile.setSex(Sex.MALE);
//		userProfile.setUser(user);
//		
//		userProfileRepository.save(userProfile);
//		
//		user.setUserProfile(userProfile);
//		userRepository.save(user);

		return "user/profile";
	}
	
	@GetMapping("profile-update-pass")
	public String updatePassword(UpdateProfileDto updateProfileDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		User user = userService.getFindByUsername(authentication.getName());
		updateProfileDto.setUser(user);
		return "user/updateProfileFormPass";
	}
	@PostMapping("profile-update-pass")
	public String updateUserPass(@Valid UpdateProfileDto updateProfileDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			return "user/updateProfileFormPass";
		}
		try {
			userService.updatePassUserForm(updateProfileDto);
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
		User user = userService.getFindByUsername(authentication.getName());
		updateEmailDto.setUser(user);
		return "user/updateProfileFormEmail";
	}

	@PostMapping("profile-update-email")
	public String updateUserEmail(@Valid UpdateEmailDto updateEmailDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			User user = userService.getFindByUsername(authentication.getName());
			updateEmailDto.setUser(user);
			return "user/updateProfileFormEmail";
		}
		model.addAttribute("serverInfo", userService.updateEmail(updateEmailDto, authentication.getName()));
		return "user/updateProfileFormEmail";
	}
}
