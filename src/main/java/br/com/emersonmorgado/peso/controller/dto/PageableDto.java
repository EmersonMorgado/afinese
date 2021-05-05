package br.com.emersonmorgado.peso.controller.dto;

import java.util.List;

public class PageableDto {
	
	private Boolean hasPrevious;
	private Boolean hasNext;
	private Integer number;
	private Integer totalpage;
	private Integer registerPage;
	private Long totalRegister;
	private List<?> registers;
	
	public PageableDto(List<?> weightDto, Boolean hasPrevious, Boolean hasNext, Integer number, Integer totalpage,
			Integer registerPage, Long totalRegister) {
		this.registers = weightDto;
		this.hasPrevious = hasPrevious;
		this.hasNext = hasNext;
		this.number = number;
		this.totalpage = totalpage;
		this.registerPage = registerPage;
		this.totalRegister = totalRegister;
	}

	public List<?> getRegisters() {
		return registers;
	}

	public void setRegisters(List<?> registers) {
		this.registers = registers;
	}

	public Boolean getHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}

	public Integer getRegisterPage() {
		return registerPage;
	}

	public void setRegisterPage(Integer registerPage) {
		this.registerPage = registerPage;
	}

	public Long getTotalRegister() {
		return totalRegister;
	}

	public void setTotalRegister(Long totalRegister) {
		this.totalRegister = totalRegister;
	}
}
