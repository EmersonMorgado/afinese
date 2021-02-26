package br.com.emersonmorgado.peso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emersonmorgado.peso.controller.dto.PassUpdateUserDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateProfileDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateUserDto;
import br.com.emersonmorgado.peso.controller.form.NewUserForm;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.service.AuthoritiesService;
import br.com.emersonmorgado.peso.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public String findUsers(Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);			
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "admin/users";
	}
	
	@GetMapping("user-new")
	public String newUser(Model model, Authentication authentication, NewUserForm newUserDto) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		return "admin/newUserForm";
	}
	
	@PostMapping("user-new")
	public String addUser(@Valid NewUserForm newUserDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			return "admin/newUserForm";
		}
		if(userService.existById(newUserDto.getUsername())) {
			model.addAttribute("userExist", "Usuário já cadastrado");
			return "admin/newUserForm";
		}
		userService.addUser(newUserDto.setUser(), newUserDto.setAuthorities());
		return "redirect:/admin/users";
	}
	
	@GetMapping("user-update/{username}")
	public String updateUserForm(@PathVariable("username") String username, UpdateUserDto updateUserDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		User user = userService.getFindByUsername(username);
		updateUserDto.setUser(user);
		return "/admin/updateUserForm";
	}

	@PostMapping("user-update")
	public String updateUser(@Valid UpdateUserDto updateUser, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			return "admin/updateUserForm";
		}
		userService.updateUser(updateUser);
		return "redirect:/admin/users";
	}
	@GetMapping("user-update-pass/{username}")
	public String updateUserForm(@PathVariable("username") String username, PassUpdateUserDto passUpdateUserDto, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		passUpdateUserDto.setUsername(username);
		return "/admin/passUpdateForm";
	}
	
	@PostMapping("user-update-pass")
	public String updateUserForm(@Valid PassUpdateUserDto passUpdateUserDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			return "admin/passUpdateForm";
		}
		userService.updatePass(passUpdateUserDto);
		return "redirect:/admin/users";
	}
	
	@PostMapping("user-delete")
	public String updateUserForm(@Valid UpdateProfileDto updateProfileDto, BindingResult result, Model model, Authentication authentication) {
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			return "admin/users";
		}
		userService.deletUser(updateProfileDto);
		return "redirect:/admin/users";
	}
	
}
