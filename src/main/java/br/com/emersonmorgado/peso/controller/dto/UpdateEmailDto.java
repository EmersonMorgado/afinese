package br.com.emersonmorgado.peso.controller.dto;

import javax.validation.constraints.NotBlank;

import br.com.emersonmorgado.peso.model.User;

public class UpdateEmailDto {
		
		@NotBlank
		private String email;
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setUser(User user) {
			this.email = user.getEmail();			
		}
}
