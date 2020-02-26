package br.com.salao.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.salao.entity.Order;
import br.com.salao.entity.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc)
			.withTableName("Taco_Order")
			.usingGeneratedKeyColumns("id");
		
		this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
			.withTableName("Taco_Order_Tacos");
		
		this.objectMapper = new ObjectMapper();
	}
	
	
	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		Long orderId = saveOrderDetails(order);
		order.setId(orderId);		
		List<Taco> tacos = order.getTacos();
		for (Taco taco : tacos) {
			saveTacoToOrder(taco, orderId);
		}
		return order;
		
	}

	/**
	 * 
	 * @param taco
	 * @param orderId
	 */
	private void saveTacoToOrder(Taco taco, Long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put("order_id", orderId);
		values.put("tacos_id", taco.getId());
		orderTacoInserter.execute(values);
	}


	/**
	 * 
	 * @param order
	 * @return
	 */
	private Long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		System.out.println(order);
		values.put("placed_at", order.getPlacedAt());
		values.put("cc_number", order.getCcNumber());
		values.put("cc_expiration", order.getCcExpiration());
		Long orderId = orderInserter.executeAndReturnKey(values).longValue();		
		return orderId;
	}

}
