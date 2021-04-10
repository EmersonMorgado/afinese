package br.com.emersonmorgado.peso.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.emersonmorgado.peso.controller.dto.PassUpdateUserDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateEmailDto;
import br.com.emersonmorgado.peso.controller.dto.UpdatePassDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateProfileDto;
import br.com.emersonmorgado.peso.controller.dto.UpdateUserDto;
import br.com.emersonmorgado.peso.controller.dto.UserProfileDto;
import br.com.emersonmorgado.peso.model.Authorities;
import br.com.emersonmorgado.peso.model.Sex;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.UserProfile;
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

	public void updatePassUserForm(UpdatePassDto updatePassDto, String userName) {
		User user = userRepository.findByUsername(userName);
		if(!updatePassDto.getNewPassword().trim().isEmpty()) {
			if(BCrypt.checkpw(updatePassDto.getPassword(), user.getPassword())){
				BCryptPasswordEncoder encoded = new BCryptPasswordEncoder();
				user.setPassword(encoded.encode(updatePassDto.getNewPassword()));
				userRepository.save(user);
				throw new NullPointerException("Senha alterada com sucesso!");
			}else {
				throw new NullPointerException("Invalid password");
			}
		}else if(!updatePassDto.getPassword().trim().isEmpty()){
			throw new NullPointerException("A nova senha não podem ser vazia");
		}
	}

	public String updateEmail(UpdateEmailDto updateEmailDto, String userName) {
		User user = userRepository.findByUsername(userName);
		user.setEmail(updateEmailDto.getEmail());
		userRepository.save(user);
		return "Email Atualizado com sucesso!";
		}
	
	public void deletUser(@Valid UpdateProfileDto updateProfileDto) {
		if(userRepository.existsById(updateProfileDto.getUsername())) {
			authoritiesRepository.deleteById(updateProfileDto.getUsername());
		}
	}
	
	public void setUserProfile(User user, UserProfileDto userProfileDto) {
		userProfileDto.setUsername(user.getUsername());
		userProfileDto.setEmail(user.getEmail());
		userProfileDto.setName(user.getUserProfile().getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		userProfileDto.setBirthday(user.getUserProfile().getBirthday().format(formatter));
		userProfileDto.setBirthdayDate( user.getUserProfile().getBirthday());
		userProfileDto.setSex(user.getUserProfile().getSex().toString());
		userProfileDto.setHeight(String.valueOf(user.getUserProfile().getHeight()));
		userProfileDto.setTargetWeight(String.valueOf(user.getUserProfile().getTargetWeight()));
	}
	
	public void updateProfile(User user, UserProfileDto userProfileDto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
		user.getUserProfile().setBirthday(LocalDate.parse(userProfileDto.getBirthday(), formatter));
		user.getUserProfile().setHeight(Double.valueOf(userProfileDto.getHeight()));
		user.getUserProfile().setSex(Sex.valueOf(userProfileDto.getSex()));
		user.getUserProfile().setName(userProfileDto.getName());
		user.getUserProfile().setTargetWeight(Double.valueOf(userProfileDto.getTargetWeight()));
		userRepository.save(user);
	}
}
