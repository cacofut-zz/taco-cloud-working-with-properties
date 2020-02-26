package br.com.salao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	public Group(String name) {
		this.name = name;
	}
	
	@ManyToMany(targetEntity = User.class)
	private List<User> users = new ArrayList<>();
	
	@ManyToMany(targetEntity = Authority.class)
	private List<Authority> authorities = new ArrayList<>();
	
	public void addUser(User user) {		
		users.add(user);
	}
	
	public void addUsers(User... users) {
		for(int i = 0; i < users.length; i++) {
			this.users.add(users[i]);
		}		
	}
	
	public void addAuthority(Authority authority) {
		authorities.add(authority);
	}

}
