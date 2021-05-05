package br.com.emersonmorgado.peso.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Weight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_weight;
	private Double weight;
	private LocalDate date;
	private Double Targetweight;
	
	@ManyToOne
	private User user;

	public Long getId_weight() {
		return id_weight;
	}

	public void setId_weight(Long id_weight) {
		this.id_weight = id_weight;
	}
	
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTargetweight() {
		return Targetweight;
	}

	public void setTargetweight(Double targetweight) {
		Targetweight = targetweight;
	}
	
}
