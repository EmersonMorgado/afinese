package br.com.emersonmorgado.peso.controller.dto;

import javax.validation.constraints.NotBlank;

import br.com.emersonmorgado.peso.model.User;

public class UpdateUserDto {
	
	@NotBlank
	private String username;
	
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

	public boolean getEnabled() {
		if(status.equals("true")) {
			return true;
		}
		return false;
	}

	public void setUser(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.status = user.stsEnabled();
		this.type = user.getAuthority().getAuthority().toString();
		
	}
}
