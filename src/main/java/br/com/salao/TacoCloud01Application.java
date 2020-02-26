package br.com.salao;

import java.util.Arrays;
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
import br.com.salao.repository.AuthorityRepository;
import br.com.salao.repository.GroupRepository;
import br.com.salao.repository.IngredientRepositorySpringData;
import br.com.salao.repository.UserRepository;

@SpringBootApplication
public class TacoCloud01Application implements WebMvcConfigurer, CommandLineRunner{
	
	@Autowired
	//private IngredientRepository ingredientRepository;
	private IngredientRepositorySpringData ingredientRepository;
	
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
			
		
	}

}
