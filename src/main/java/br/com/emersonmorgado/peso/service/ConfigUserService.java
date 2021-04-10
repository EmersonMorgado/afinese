package br.com.emersonmorgado.peso.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.UserProfile;
import br.com.emersonmorgado.peso.repository.UserProfileRepository;

@Service
public class ConfigUserService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	public boolean setUserProfile(AuthoritiesService authoritiesService) {
		
		if(!userProfileRepository.existsByUserUsername(authoritiesService.getAuthorities().getUsername())) {
			UserProfile userProfile = new UserProfile();
			userProfile.setBirthday(LocalDate.parse("2000-01-01"));
			userProfile.setHeight(0L);
			userProfile.setTargetWeight(0L);
			User user = new User();
			user.setUsername(authoritiesService.getAuthorities().getUsername());
			userProfile.setUser(user);
			userProfileRepository.save(userProfile);
		}
		return true;
	}

}
