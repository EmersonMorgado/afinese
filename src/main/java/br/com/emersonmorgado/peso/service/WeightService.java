package br.com.emersonmorgado.peso.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.emersonmorgado.peso.controller.dto.PageableDto;
import br.com.emersonmorgado.peso.controller.dto.WeightDto;
import br.com.emersonmorgado.peso.controller.form.WeightForm;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.Weight;
import br.com.emersonmorgado.peso.repository.WeightRepository;

@Service
public class WeightService {
	
	@Autowired
	private WeightRepository weightRepository;
	
	@Autowired
	private UserProfileSevice userProfileService;

	public void addWeigh(WeightForm weightForm) {
		
		Weight weight = new Weight();
		User user = new User();
		user.setUsername(weightForm.getUsername());
		weight.setUser(user);
		weight.setWeight(Double.valueOf(weightForm.getWeight()));
		weight.setDate(LocalDate.parse(weightForm.getDate()));
		weight.setTargetweight(userProfileService.findTargetWeightByUsername(weightForm.getUsername()));
		System.out.println(" Meta de Peso: " + weight.getTargetweight());
		weightRepository.save(weight);
	}
	
	public Double getTargetWeightByUserName(String username) {
		return userProfileService.findTargetWeightByUsername(username);
	}

	public Page<Weight> findByUserUsername(String username, Pageable pageable) {
		return weightRepository.findByUserUsername(username, pageable);
	}
	
	public PageableDto findWeightsByUsername(String username, Integer registerPerPage, Integer page) {
		
		Sort sort = Sort.by(Sort.Direction.DESC,"date");
		Pageable pageable = PageRequest.of(page, registerPerPage, sort);
		Page<Weight>  weights= weightRepository.findByUserUsername(username, pageable);
		
		List<Weight> listWeight = weights.getContent();
		List<WeightDto> weightDto = new ArrayList<WeightDto>();
//		DecimalFormat df = new DecimalFormat("#.##");
		try {
			listWeight.forEach(weight->extracted(weight, weightDto));		
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			}
		PageableDto pDto = new PageableDto( 
										(List<WeightDto>)weightDto, 
										weights.hasPrevious(), 
										weights.hasNext(), 
										weights.getNumber(), 
										weights.getTotalPages(), 
										registerPerPage, 
										weights.getTotalElements()
										);
		return pDto;
	}
	
	private Double round(Double value) {
		return Math.round(value*100.0) /100.0;
	}
	
	private void extracted(Weight weight, List<WeightDto> weightDto) {
		WeightDto wDto = new WeightDto(weight.getId_weight(),
										weight.getDate(),
										round(weight.getTargetweight()),
										round(weight.getWeight()),
										round(weight.getTargetweight() / weight.getWeight()),
										round(weight.getWeight()- weight.getTargetweight()));
	
						weightDto.add(wDto);
	}
	

	
	public void deleteWeigh(WeightForm weightDto) {
		weightRepository.deleteById(Long.valueOf(weightDto.getId()));
		
	}

	public Page<Weight> findAll(Pageable pageable) {
		return weightRepository.findAll(pageable);
	}

	public Weight fingLastWeight(String username) {
		return null;
	}
}
