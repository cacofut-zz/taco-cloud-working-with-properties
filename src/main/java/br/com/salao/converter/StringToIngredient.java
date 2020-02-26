package br.com.salao.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.salao.entity.Ingredient;

@Component
public final class StringToIngredient implements Converter<String, Ingredient>{
	@Override
	public Ingredient convert(String id) {			
		Long ingredient_id = Long.parseLong(id);
		return new Ingredient(ingredient_id, null, null, null);
	}

}
