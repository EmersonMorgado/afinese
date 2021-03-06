package br.com.emersonmorgado.peso.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonmorgado.peso.controller.form.UserProfileForm;
import br.com.emersonmorgado.peso.model.UserProfile;
import br.com.emersonmorgado.peso.repository.UserProfileRepository;
import br.com.emersonmorgado.peso.repository.UserRepository;

@Service
public class ServiceUserProfile {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void addUserPrifile(UserProfileForm userProfileForm, Principal principal) throws Exception {
		UserProfile userProfile = userProfileForm.addUserProfile();		
		if(userRepository.existsById(userProfile.getUser().getUsername())) {
			userProfileRepository.save(userProfile);
		}else {
			throw new Exception("username not found");
		}
	}
}
