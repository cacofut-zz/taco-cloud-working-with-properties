package br.com.salao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.salao.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}
