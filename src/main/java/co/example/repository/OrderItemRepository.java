package co.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import co.example.domain.OrderItem;

/**
 * 商品を注文するリポジトリ.
 * 
 * @author sakai
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert =
		new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 商品を挿入する.
	 * 
	 * @param orderItem 注文された商品
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		Number key = insert.executeAndReturnKey(param);
		orderItem.setId(key.intValue());
		return orderItem;
	}
	
	/**
	 * 商品とトッピングを削除.
	 * 
	 * @param id ID
	 */
	public void deleteById(Integer id) {
		String sql = "WITH deleted AS (DELETE FROM order_items WHERE id=:id RETURNING id) DELETE FROM order_toppings WHERE id IN (SELECT order_item_id FROM deleted);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	public void update(Integer temporalOrderId,Integer loginUserOrderId){
		String sql = "UPDATE order_items SET order_id = :loginUserOrderId WHERE order_id = :temporalOrderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("temporalOrderId", temporalOrderId).addValue("loginUserOrderId", loginUserOrderId);
		template.update(sql, param);
	}
	
}
