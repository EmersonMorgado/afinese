package br.com.emersonmorgado.peso.controller.dto;

import java.time.LocalDate;

public class WeightDto {
	
	private Long id;
	private LocalDate date;
	private Double target;
	private Double weight;
	private Double percent;
	private Double weightMinTarget;
	
	public WeightDto(Long id, LocalDate date, Double target, Double weight, Double percent, Double weightMinTarget) {
		this.id = id;
		this.date = date;
		this.target = target;
		this.weight = weight;
		this.percent = percent;
		this.weightMinTarget = weightMinTarget;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getTarget() {
		return target;
	}
	public void setTarget(Double target) {
		this.target = target;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public Double getWeightMinTarget() {
		return weightMinTarget;
	}
	public void setWeightMinTarget(Double weightMinTarget) {
		this.weightMinTarget = weightMinTarget;
	}
}
