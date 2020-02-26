package br.com.salao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.salao.entity.User;
import br.com.salao.repository.OrderRepositorySpringData;
import br.com.salao.repository.UserRepository;

@SpringBootTest
@Transactional
@ConfigurationProperties(prefix = "taco.orders")
class TacoCloud01ApplicationTests {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OrderRepositorySpringData orderRepo;
	
	private int pageSize = 20;
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Test
	void contextLoads() {
		System.out.println(pageSize);
		User user1 = userRepo.findById(1L).get();
		Pageable pageable = PageRequest.of(0, pageSize);
		System.out.println(orderRepo.findByUserOrderByPlacedAtDesc(user1, pageable));
//		List<Ingredient> ingredients = Arrays.asList(
//			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//			new Ingredient("COTO", "Corn Tortilla",  Type.WRAP),
//			new Ingredient("GRBF", "Ground Beef", 	 Type.PROTEIN),
//			new Ingredient("CARN", "Carnitas", 		 Type.PROTEIN),
//			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//			new Ingredient("LETC", "Lettuce", 		 Type.VEGGIES),
//			new Ingredient("CHED", "Cheddar", 		 Type.CHEESE),
//			new Ingredient("JACK", "Monterrey",		 Type.CHEESE),
//			new Ingredient("SLSA", "Salsa", 		 Type.SAUCE),
//			new Ingredient("SRCR", "Sour cream", 	 Type.SAUCE)
//			
//		);
//		
//		Type[] types = Ingredient.Type.values();
//		
//		
//		for (Type type : types) {
//			List<Ingredient> list =	ingredients.stream()
//				.filter( i -> i.getType().equals(type)).collect(Collectors.toList());
//			
//			System.out.println(list);
//		}
	}

}
