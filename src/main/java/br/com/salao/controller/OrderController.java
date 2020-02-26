package br.com.salao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import br.com.salao.entity.Order;
import br.com.salao.entity.User;
import br.com.salao.properties.OrderProps;
import br.com.salao.repository.OrderRepositorySpringData;
import br.com.salao.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {
		
	//private OrderRepository orderRepository;	
	private UserRepository userRepo;
	private OrderRepositorySpringData orderRepo;
	private OrderProps orderProps;

	@Autowired
	public OrderController(OrderRepositorySpringData orderRepo, UserRepository userRepo, OrderProps orderProps) {	
		this.orderRepo  = orderRepo;
		this.userRepo   = userRepo;
		this.orderProps = orderProps;
	}

	@GetMapping("/current")
	public String orderForm() {		
		return "orderForm";
	}
	
	/*
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if( errors.hasErrors() ) {
			log.info("Order submitted: " + order);
			System.out.println(errors);
			return "orderForm";
		}
		orderRepo.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		return "redirect:/";
	}*/
	
	/*
	 * usando o Principal para obter o usuário*/
	/*
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, 
			Principal principal) {
		if( errors.hasErrors() ) {
			log.info("Order submitted: " + order);
			System.out.println(errors);
			return "orderForm";
		}
		User user = userRepo.findByUsername(principal.getName());
		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
	*/
	
	/* usando Authentication para obter o usuário */
	/*
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, 
			@AuthenticationPrincipal User user) {
		if( errors.hasErrors() ) {
			log.info("Order submitted: " + order);
			System.out.println(errors);
			return "orderForm";
		}

		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
	*/
	
	/*Usando o contexto security para obter o usuário*/
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if( errors.hasErrors() ) {
			log.info("Order submitted: " + order);
			System.out.println(errors);
			return "orderForm";
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByUsername(authentication.getName());

		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
	
	@GetMapping
	public String ordersForUser(Model model) {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepo.findByUsername(authentication.getName());
		Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
		List<Order> orders = orderRepo.findByUserOrderByPlacedAtDesc(user, pageable);

		model.addAttribute("orders", orders);
		return "orderList";
	}
}
