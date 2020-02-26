package br.com.salao.repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.salao.entity.Ingredient;
import br.com.salao.entity.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository{
	
	private JdbcTemplate jdbc;	
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {	
		this.jdbc = jdbc;
	}

	@Override
	public Taco save(Taco taco) {
		Long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		for (Ingredient ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
		}
		return taco;
	}

	/**
	 * 
	 * @param taco
	 * @return
	 */
	private Long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		String query = "INSERT INTO Taco(name, createdAt)VALUES(?, ?)";
		PreparedStatementCreatorFactory pscf = 
				new PreparedStatementCreatorFactory(query, Types.VARCHAR, Types.TIMESTAMP);
		
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
			Arrays.asList(
				taco.getName(), 
				new Timestamp(taco.getCreatedAt().getTime())
			)
		);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);		
		return keyHolder.getKey().longValue();
	}
	
	/**
	 * 
	 * @param ingredient
	 * @param tacoId
	 */
	private void saveIngredientToTaco(Ingredient ingredient, Long tacoId) {
		jdbc.update("INSERT INTO Taco_Ingredients(taco_id, ingredient_id)VALUES(?, ?)", tacoId, ingredient.getId());
		
	}

}
