package br.com.salao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.salao.entity.Ingredient;

public interface IngredientRepositorySpringData extends CrudRepository<Ingredient, Long>{

}
