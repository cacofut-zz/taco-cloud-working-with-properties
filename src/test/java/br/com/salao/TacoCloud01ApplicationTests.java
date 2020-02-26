package br.com.salao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.salao.entity.Ingredient;
import br.com.salao.entity.Ingredient.Type;

@SpringBootTest
class TacoCloud01ApplicationTests {

	@Test
	void contextLoads() {
		
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
