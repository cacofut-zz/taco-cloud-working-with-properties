package br.com.salao.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.salao.entity.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{
	
	private JdbcTemplate jdbc;
 
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {	
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() {		
		return jdbc.query( "SELECT * FROM ingredient", this::mapToRowToIngredient );
	}

	@Override
	public Ingredient findOne(String id) {		
		return jdbc.queryForObject("SELECT * FROM ingredient WHERE id = ?", this::mapToRowToIngredient, id);
	}
	
	@Override
	public Ingredient save(Ingredient ingredient) {	
		System.out.println(ingredient);
		jdbc.update("INSERT INTO ingredient(id, codigo, name, type)VALUES(?, ?, ?, ?)",
			ingredient.getId(), ingredient.getCodigo(), ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}
	
	
	public Ingredient mapToRowToIngredient( ResultSet rs, int index ) throws SQLException {
		Ingredient ingredient = new Ingredient(
			rs.getLong("id"), 
			rs.getString("codigo"), 
			rs.getString("name"), 
			Ingredient.Type.valueOf(rs.getString("type"))
		);
		return ingredient;
	}


	
}
