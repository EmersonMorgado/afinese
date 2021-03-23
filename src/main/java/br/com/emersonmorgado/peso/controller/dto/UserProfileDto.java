package br.com.emersonmorgado.peso.controller.dto;

import java.time.format.DateTimeFormatter;

import br.com.emersonmorgado.peso.model.User;

public class UserProfileDto {

	private String username;
	private String email;
	private String name;
	private String birthday;
	private String sex;
	private String height;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setUser(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.name = user.getUserProfile().getName();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.birthday = user.getUserProfile().getBirthday().format(formatter);
		this.sex = user.getUserProfile().getSex().toString();
		this.height = String.valueOf(user.getUserProfile().getHeight());		
	}
}
