package br.com.emersonmorgado.peso.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.emersonmorgado.peso.model.Authorities;
import br.com.emersonmorgado.peso.model.UserStatus;

@Service
public class AuthoritiesService {
	
	private Authorities authorities = new Authorities();

	public void setAuthority(Authentication authentication) {
		String auth = authentication.getAuthorities().toString().substring(1, authentication.getAuthorities().toString().length()-1);
		System.out.println("uuuuuuu");
		authorities.setAuthority(UserStatus.valueOf(auth));
		authorities.setUsername(authentication.getName());
	}

	public Authorities getAuthorities() {
		return authorities;
	}
}
