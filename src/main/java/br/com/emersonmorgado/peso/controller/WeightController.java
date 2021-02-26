package br.com.emersonmorgado.peso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.emersonmorgado.peso.controller.form.WeightForm;
import br.com.emersonmorgado.peso.model.Weight;
import br.com.emersonmorgado.peso.service.AuthoritiesService;
import br.com.emersonmorgado.peso.service.WeightService;

@Controller
@RequestMapping("user")
public class WeightController {
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	private WeightService weightService;
	
	@GetMapping("weight")
	public String weight() {
		return "redirect:/user/weight/0";
	}
	
	@GetMapping("weight/{page}")
	public String addWeight(@PathVariable("page") Integer page, WeightForm weightForm, Model model, Authentication authentication){
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		
		Sort sort = Sort.by(Sort.Direction.DESC,"date");
		Pageable pageable = PageRequest.of(page, 10, sort);
		Page<Weight> weights = weightService.findAllByUsername(authoritiesService.getAuthorities().getUsername(), pageable);		
		System.out.println(weights.getNumber());
		
		model.addAttribute("weights", weights);
		return "/user/weight";
	}
	
	@PostMapping("weight-new")
	public String addWeight(@Valid WeightForm weightForm, BindingResult result, Model model, Authentication authentication){
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		if(result.hasErrors()) {
			Sort sort = Sort.unsorted();
			Pageable pageable = PageRequest.of(0, 10, sort);
			Page<Weight> weights = weightService.findAllByUsername(authoritiesService.getAuthorities().getUsername(),pageable);
			model.addAttribute("weights", weights);
			return "/user/weight";
		}
		weightService.addWeigh(weightForm);
		return "redirect:/user/weight";
	}
	
	@PostMapping("weight-delete")
	public String deleteWeight(WeightForm weightForm, BindingResult result, Model model, Authentication authentication){
		authoritiesService.setAuthority(authentication);
		model.addAttribute(authoritiesService);
		weightService.deleteWeigh(weightForm);
		return "redirect:/user/weight";
	}

}
