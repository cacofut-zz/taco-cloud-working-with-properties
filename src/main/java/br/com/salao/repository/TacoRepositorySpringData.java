package br.com.salao.repository;

import org.springframework.data.repository.CrudRepository;
import br.com.salao.entity.Taco;


public interface TacoRepositorySpringData extends CrudRepository<Taco, Long>{

}
