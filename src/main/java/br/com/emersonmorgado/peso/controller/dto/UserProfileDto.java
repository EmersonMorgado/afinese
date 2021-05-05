package br.com.emersonmorgado.peso.controller.dto;

import java.time.LocalDate;

public class UserProfileDto {

	private String username;
	private String email;
	private String name;
	private String birthday;
	private String sex;
	private String height;
	private LocalDate birthdayDate;
	private String targetWeight;
	private Double imc;
	private LocalDate LastWeightRegister;
	private Double LastWeight;

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

	public LocalDate getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(LocalDate birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(String targetWeight) {
		this.targetWeight = targetWeight;
	}
	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public LocalDate getLastWeightRegister() {
		return LastWeightRegister;
	}

	public void setLastWeightRegister(LocalDate lastWeightRegister) {
		LastWeightRegister = lastWeightRegister;
	}

	public Double getLastWeight() {
		return LastWeight;
	}

	public void setLastWeight(Double lastWeight) {
		LastWeight = lastWeight;
	}
	
}
