package br.com.salao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.salao.entity.Authority;
import br.com.salao.entity.Group;
import br.com.salao.entity.Ingredient;
import br.com.salao.entity.User;
import br.com.salao.entity.Ingredient.Type;
import br.com.salao.entity.Order;
import br.com.salao.entity.Taco;
import br.com.salao.repository.AuthorityRepository;
import br.com.salao.repository.GroupRepository;
import br.com.salao.repository.IngredientRepositorySpringData;
import br.com.salao.repository.OrderRepositorySpringData;
import br.com.salao.repository.TacoRepositorySpringData;
import br.com.salao.repository.UserRepository;

@SpringBootApplication
public class TacoCloud01Application implements WebMvcConfigurer, CommandLineRunner{
	
	
	@Autowired
	//private IngredientRepository ingredientRepository;
	private IngredientRepositorySpringData ingredientRepository;
	
	@Autowired
	private TacoRepositorySpringData tacoRepo;
	
	@Autowired
	private OrderRepositorySpringData orderRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired	
	private GroupRepository groupRepository;	

	public static void main(String[] args) {
		SpringApplication.run(TacoCloud01Application.class, args);
	}
	
	
	@Override
	public void addViewControllers( ViewControllerRegistry registry ) {
		registry.addViewController("/").setViewName("home");
	}



	@Override
	public void run(String... args) throws Exception {
		
		Authority role_user    = new Authority("ROLE_USER");
		Authority role_admin   = new Authority("ROLE_ADMIN");
		Authority role_manager = new Authority("ROLE_MANAGER");
		
		authorityRepository.save(role_user);
		authorityRepository.save(role_admin);
		authorityRepository.save(role_manager);
		
		Group group_user = new Group("Grupo de usuário");
							
		User user1 = new User(
			"cacofut", 
			//"{bcrypt}$2a$10$N/m4wiAuv2MoxlOQdC0TweCotk0DzTAsOkoL5KikUBBZnmXzbccEC",
			"$2a$10$N/m4wiAuv2MoxlOQdC0TweCotk0DzTAsOkoL5KikUBBZnmXzbccEC",
			"Cristiano Carvalho Amaral", 
			"Rua Ernest Renam 954", 
			"São Paulo", 
			"São Paulo", 
			"05659020", 
			"991874268", 
			true
		);
		
		user1.setAccountNonExpired(true);
		user1.setAccountNonLocked(true);
		user1.setCredentialsNonExpired(true);						
		user1.addAuthority(role_user);
		
		
		User user2 = new User(
			"joao", 
			//"{bcrypt}$2a$10$N/m4wiAuv2MoxlOQdC0TweCotk0DzTAsOkoL5KikUBBZnmXzbccEC", 
			"$2a$10$N/m4wiAuv2MoxlOQdC0TweCotk0DzTAsOkoL5KikUBBZnmXzbccEC",
			"João Roberto Silva", 
			"Rua Ernest Renam 954", 
			"São Paulo", 
			"São Paulo", 
			"05659020", 
			"991874268", 
			true
		);
			
		user2.setAccountNonExpired(true);
		user2.setAccountNonLocked(true);
		user2.setCredentialsNonExpired(true);						
		user2.addAuthority(role_user);
		
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		group_user.addUsers(user1, user2);
		group_user.addAuthority(role_user);;		
		groupRepository.save(group_user);
		
		
		List<Ingredient> ingredients = Arrays.asList(
			new Ingredient(null, "FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient(null, "COTO", "Corn Tortilla",  Type.WRAP),
			new Ingredient(null, "GRBF", "Ground Beef", 	 Type.PROTEIN),
			new Ingredient(null, "CARN", "Carnitas", 		 Type.PROTEIN),
			new Ingredient(null, "TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient(null, "LETC", "Lettuce", 		 Type.VEGGIES),
			new Ingredient(null, "CHED", "Cheddar", 		 Type.CHEESE),
			new Ingredient(null, "JACK", "Monterrey",		 Type.CHEESE),
			new Ingredient(null, "SLSA", "Salsa", 		 Type.SAUCE),
			new Ingredient(null, "SRCR", "Sour cream", 	 Type.SAUCE)
			  
		);
		for (Ingredient ingredient : ingredients) {
			ingredientRepository.save(ingredient);
		}
			
		
		
		Taco t1 = new Taco("t1 dfdfsd", new Date(), ingredients);
		tacoRepo.save(t1);
		
		Order or1 = new Order();
		or1.setName("novo order 1");
		or1.setStreet("rua novo order");
		or1.setCity("São Paulo");
		or1.setState("São Paulo");
		or1.setZip("05659020");
		or1.setCcNumber("12121");
		or1.setCcExpiration("1212");
		or1.setCcCVV("0212");		
		or1.addTaco(t1);
		
		Order or2 = new Order();
		or2.setName("novo order 1");
		or2.setStreet("rua novo order");
		or2.setCity("São Paulo");
		or2.setState("São Paulo");
		or2.setZip("05659020");
		or2.setCcNumber("12121");
		or2.setCcExpiration("1212");
		or2.setCcCVV("0212");		
		or2.addTaco(t1);
		
		Order or3 = new Order();
		or3.setName("novo order 1");
		or3.setStreet("rua novo order");
		or3.setCity("São Paulo");
		or3.setState("São Paulo");
		or3.setZip("05659020");
		or3.setCcNumber("12121");
		or3.setCcExpiration("1212");
		or3.setCcCVV("0212");		
		or3.addTaco(t1);
		
		Order or4 = new Order();
		or4.setName("novo order 1");
		or4.setStreet("rua novo order");
		or4.setCity("São Paulo");
		or4.setState("São Paulo");
		or4.setZip("05659020");
		or4.setCcNumber("12121");
		or4.setCcExpiration("1212");
		or4.setCcCVV("0212");		
		or4.addTaco(t1);
		
		Order or5 = new Order();
		or5.setName("novo order 1");
		or5.setStreet("rua novo order");
		or5.setCity("São Paulo");
		or5.setState("São Paulo");
		or5.setZip("05659020");
		or5.setCcNumber("12121");
		or5.setCcExpiration("1212");
		or5.setCcCVV("0212");		
		or5.addTaco(t1);
		
		Order or6 = new Order();
		or6.setName("novo order 1");
		or6.setStreet("rua novo order");
		or6.setCity("São Paulo");
		or6.setState("São Paulo");
		or6.setZip("05659020");
		or6.setCcNumber("12121");
		or6.setCcExpiration("1212");
		or6.setCcCVV("0212");		
		or6.addTaco(t1);
		
		Order or7 = new Order();
		or7.setName("novo order 1");
		or7.setStreet("rua novo order");
		or7.setCity("São Paulo");
		or7.setState("São Paulo");
		or7.setZip("05659020");
		or7.setCcNumber("12121");
		or7.setCcExpiration("1212");
		or7.setCcCVV("0212");		
		or7.addTaco(t1);
		
		
		or1.setUser(user1);
		or2.setUser(user1);
		or3.setUser(user1);
		or4.setUser(user1);
		or5.setUser(user1);
		or6.setUser(user1);
		or7.setUser(user1);
					
		orderRepo.save(or1);
		orderRepo.save(or2);
		orderRepo.save(or3);
		orderRepo.save(or4);
		orderRepo.save(or5);
		orderRepo.save(or6);
		orderRepo.save(or7);
		
	}

}
