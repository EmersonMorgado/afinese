package br.com.emersonmorgado.peso.controller.form;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.emersonmorgado.peso.model.Authorities;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.UserStatus;

public class NewUserForm {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String type = "ROLE_USER";
	
	@NotBlank
	private String status = "true";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public User setUser() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user =  new User();
		user.setUsername(username);
		user.setPassword(encoder.encode(password));
		user.setEnabled(Boolean.valueOf(status));
		user.setEmail(email);
		return user;
	}

	public Authorities setAuthorities() {
		Authorities authority = new Authorities();
		authority.setUsername(username);
		authority.setAuthority(UserStatus.valueOf(type));
		return authority;
	}

}
