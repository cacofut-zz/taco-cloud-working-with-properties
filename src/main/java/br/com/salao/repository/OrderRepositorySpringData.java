package br.com.salao.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.com.salao.entity.Order;
import br.com.salao.entity.User;

public interface OrderRepositorySpringData extends CrudRepository<Order, Long>{

	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
