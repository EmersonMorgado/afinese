package br.com.emersonmorgado.peso.controller.dto;

import javax.validation.constraints.NotBlank;

import br.com.emersonmorgado.peso.model.User;

public class UpdatePassDto {

		@NotBlank
		private String password;
		@NotBlank
		private String newPassword;
		
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
}
