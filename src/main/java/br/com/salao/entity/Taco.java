package br.com.salao.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	 
	@NotEmpty
	@Size(min = 5, message = "Name must be at least 5 characteres long")
	private String name;
	
	private Date createdAt;
	
	@ManyToMany(targetEntity = Ingredient.class)
	@NotEmpty(message = "You must be choose at least 1 ingredient")
	private List<Ingredient> ingredients;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
}
