package br.com.emersonmorgado.peso.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.emersonmorgado.peso.controller.dto.PassUpdateUserDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateProfileDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateUserDto;
import br.com.emersonmorgado.peso.model.Authorities;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.UserStatus;
import br.com.emersonmorgado.peso.repository.AuthoritiesRepository;
import br.com.emersonmorgado.peso.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public boolean existById(String id) {
		return userRepository.existsById(id);
	}

	public void addUser(User user, Authorities authorities) {
		userRepository.save(user);
		authoritiesRepository.save(authorities);
	}

	public User getFindByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void updateUser(UpdateUserDto updateUserDto) {
		User user = userRepository.findByUsername(updateUserDto.getUsername());
		user.setEmail(updateUserDto.getEmail());
		user.setEnabled(updateUserDto.getEnabled());
		user.getAuthority().setAuthority(UserStatus.valueOf(updateUserDto.getType()));
		userRepository.save(user);
	}

	public void updatePass(PassUpdateUserDto passUpdateUserDto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(userRepository.existsById(passUpdateUserDto.getUsername())){
			User user = userRepository.findByUsername(passUpdateUserDto.getUsername());
			user.setPassword(encoder.encode(passUpdateUserDto.getPassword()));
			userRepository.save(user);
		}
	}

	public void updateProfile(UpdateProfileDto updateProfileDto) {
		boolean changer = false;
		User user = userRepository.findByUsername(updateProfileDto.getUsername());
		if(!updateProfileDto.getNewPassword().trim().isEmpty()) {
			if(updateProfileDto.getPassword().trim().isEmpty()) {
				throw new NullPointerException("A senha atual senha não podem ser vazia");
			}
			if(BCrypt.checkpw(updateProfileDto.getPassword(), user.getPassword())){
				BCryptPasswordEncoder encoded = new BCryptPasswordEncoder();
				user.setPassword(encoded.encode(updateProfileDto.getNewPassword()));
				changer = true;
			}else {
				throw new NullPointerException("Invalid password");
			}
		}else if(!updateProfileDto.getPassword().trim().isEmpty()){
			throw new NullPointerException("Nova senha não podem ser vazia");
		}
		if(!updateProfileDto.getEmail().equals(user.getEmail())) {
			user.setEmail(updateProfileDto.getEmail());
			changer = true;
		}
		if(changer) {
			userRepository.save(user);
			throw new NullPointerException("Atualização realizada!");
		}
	}

	public void deletUser(@Valid UpdateProfileDto updateProfileDto) {
		if(userRepository.existsById(updateProfileDto.getUsername())) {
			authoritiesRepository.deleteById(updateProfileDto.getUsername());
		}
	}
}
