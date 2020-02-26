package br.com.salao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.salao.entity.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{
	
	Authority findByName(String name);
}
