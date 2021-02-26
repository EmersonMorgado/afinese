package br.com.emersonmorgado.peso.controller.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.emersonmorgado.peso.model.Sex;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.UserProfile;

public class UserProfileForm {
	private String name;
	private String birthday;
	private String sex;
	private String height;
	private String username;	
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public UserProfile addUserProfile() {
		User user = new User();
		user.setUsername(username);
		
		UserProfile userProfile = new UserProfile(user);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		userProfile.setBirthday(LocalDate.parse(birthday, formatter));
		userProfile.setHeight(Double.parseDouble(height));
		userProfile.setName(name);
		userProfile.setSex(Sex.valueOf(sex));
		return userProfile;
	}
}
