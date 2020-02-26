package br.com.salao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.salao.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {

	
}
