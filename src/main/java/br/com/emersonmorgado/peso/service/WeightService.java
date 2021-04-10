package br.com.emersonmorgado.peso.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.emersonmorgado.peso.controller.form.WeightForm;
import br.com.emersonmorgado.peso.controller.form.WeightProjection;
import br.com.emersonmorgado.peso.model.User;
import br.com.emersonmorgado.peso.model.Weight;
import br.com.emersonmorgado.peso.repository.WeightRepository;

@Service
public class WeightService {
	
	@Autowired
	private WeightRepository weightRepository;
	
	@Autowired
	private UserService userService;

	public void addWeigh(WeightForm weightForm) {
		Weight weight = new Weight();
		User user = new User();
		user.setUsername(weightForm.getUsername());
		weight.setUser(user);
		weight.setWeight(Double.valueOf(weightForm.getWeight()));
		weight.setDate(LocalDate.parse(weightForm.getDate()));		
		weightRepository.save(weight);		
	}
	
	public Double getTargetWeight(String username) {
		User user = userService.getFindByUsername(username);
		return user.getUserProfile().getTargetWeight();
	}

	public Page<Weight> findAllByUsername(String username, Pageable pageable) {
		Page<Weight>  weights= weightRepository.findByUserUsername(username, pageable);
		return weights;
	}

	public void deleteWeigh(WeightForm weightDto) {
		weightRepository.deleteById(Long.valueOf(weightDto.getId()));
		
	}

	public Page<Weight> findAll(Pageable pageable) {
		return weightRepository.findAll(pageable);
	}

	public List<WeightProjection> findWeightsAndDate() {
		return weightRepository.findWeightSt();
	}

}
