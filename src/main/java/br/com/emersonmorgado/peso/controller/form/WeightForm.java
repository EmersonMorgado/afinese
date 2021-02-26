package br.com.emersonmorgado.peso.controller.form;

import javax.validation.constraints.NotBlank;

public class WeightForm {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String weight;
	
	@NotBlank
	private String date;
	
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
