package br.com.salao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable{

	private static final long serialVersionUID = -96208563885199855L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date placedAt;

	@NotEmpty(message = "Name is required")
	@Size(min = 1, message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Street is required")
	private String street;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	@NotEmpty(message = "State is required")
	private String state;
	
	@NotEmpty(message = "Zip is required")
	private String zip;
	
	//@CreditCardNumber(message = "Not a valid credit card number")
	private String ccNumber;
	
	//@Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	
	//@Digits(integer=3, fraction = 0, message="Invalid CVV")
	private String ccCVV;
	
	@ManyToMany(targetEntity = Taco.class) 
	private List<Taco> tacos = new ArrayList<>();
	
	@ManyToOne
	private User user;
		
	public void addTaco(Taco taco) {
		tacos.add(taco);		
	}
	
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
 
	@Override
	public String toString() {
		return "Order [id=" + id + ", placedAt=" + placedAt + ", name=" + name + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", ccNumber=" + ccNumber + ", ccExpiration=" + ccExpiration
				+ ", ccCVV=" + ccCVV + "]";
	}
	
	
	
}
