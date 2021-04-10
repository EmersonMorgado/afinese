package br.com.emersonmorgado.peso.controller.form;

import br.com.emersonmorgado.peso.model.Weight;

public class WeightChartForm {
	
	private String date;
	private String weight;
	private String target;
	
	public WeightChartForm(String date, String weight, String target) {
		super();
		this.date = date;
		this.weight = weight;
		this.target = target;
	}
	
	
}
