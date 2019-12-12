package co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.example.domain.Order;
import co.example.repository.OrderRepository;

/**
 * ショッピングカート画面を表示するサービス.
 * 
 * @author sakai
 *
 */
@Service
@Transactional
public class ShowOrderItemService {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * ショッピングカートの中身を表示する.
	 * 
	 * @param userId ユーザーID
	 * @return 
	 */
	public List<Order> findByUserIdAndStatus(Integer userId) {
		return orderRepository.findByUserIdAndStatus(userId, 0);
	}
	
}