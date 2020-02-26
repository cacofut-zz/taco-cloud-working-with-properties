package br.com.salao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.salao.entity.Ingredient;
import br.com.salao.entity.Ingredient.Type;
import br.com.salao.entity.Order;
import br.com.salao.entity.Taco;
import br.com.salao.repository.IngredientRepositorySpringData;
import br.com.salao.repository.TacoRepositorySpringData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design") 
@SessionAttributes("order")
public class DesignTacoController {
		
	//private IngredientRepository ingredientRepository;
	private IngredientRepositorySpringData ingredientRepository;
	
	//private TacoRepository tacoRepository;
	private TacoRepositorySpringData tacoRepository;
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@Autowired
	public DesignTacoController(IngredientRepositorySpringData ingredientRepository, TacoRepositorySpringData tacoRepository) {	
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@GetMapping
	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = new ArrayList<>();
		Iterable<Ingredient> ingredients_iterable = ingredientRepository.findAll();
		ingredients_iterable.forEach( i -> ingredients.add(i));
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			List<Ingredient> list =	ingredients.stream()
				.filter( i -> i.getType().equals(type)).collect(Collectors.toList());
			model.addAttribute(type.toString().toLowerCase(), list);			
		}
				 			
		return "design";
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
		log.info("Order tacos add" + order.getTacos());
		if( errors.hasErrors() ) {
			log.info("processing design: " + errors);
			return "design";
		}
		// Save the taco design
		tacoRepository.save(taco);
		order.addTaco(taco);
		// We'll do this in chapter 33
		
		log.info("processing design: " + taco);
		log.info("Order tacos add" + order.getTacos());
		return "redirect:/orders/current";
	}
}
