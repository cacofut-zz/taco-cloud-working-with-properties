package br.com.salao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.salao.entity.Order;

public interface OrderRepositorySpringData extends CrudRepository<Order, Long>{

}
