package br.com.salao.repository;


import br.com.salao.entity.Ingredient;

public interface IngredientRepository{
	
	Iterable<Ingredient> findAll();
	
	Ingredient findOne(String id);
	
	Ingredient save(Ingredient ingredient);
	
	
	
	
}
