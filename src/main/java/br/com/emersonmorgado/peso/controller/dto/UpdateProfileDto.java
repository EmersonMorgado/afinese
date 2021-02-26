package br.com.emersonmorgado.peso.controller.dto;

import javax.validation.constraints.NotBlank;

import br.com.emersonmorgado.peso.model.User;

public class UpdateProfileDto {

		@NotBlank
		private String username;
		
		@NotBlank
		private String email;

		private String password;
		private String newPassword;
		
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}

		public void setUser(User user) {
			this.username = user.getUsername();
			this.email = user.getEmail();			
		}
}
