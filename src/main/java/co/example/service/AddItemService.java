package co.example.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.example.domain.LoginUser;
import co.example.domain.Order;
import co.example.domain.OrderItem;
import co.example.domain.OrderTopping;
import co.example.form.OrderItemForm;
import co.example.repository.OrderItemRepository;
import co.example.repository.OrderRepository;
import co.example.repository.OrderToppingRepository;

@Service
@Transactional
public class AddItemService {

	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	public OrderItemRepository orderItemRepository;

	@Autowired
	public OrderToppingRepository orderToppingRepository;

	@Autowired
	public HttpSession session;

	/**
	 * 注文された商品を挿入する.
	 * 
	 * @param orderItem 注文した商品
	 */
	public void addItem(OrderItemForm orderItemForm, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		// 登録してあるID
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
			// セッションに入っているID
		} else if (session.getAttribute("userId") != null) {
			userId = (Integer) session.getAttribute("userId");
			// 自動採番のID
		} else {
			String source = session.getId();
			userId = source.hashCode();
			session.setAttribute("userId", userId);
		}

		int status = 0;
		Order order = null;

		List<Order> searchOrderList = orderRepository.findByUserIdAndStatus(userId, status);
		List<Integer> orderToppingList = orderItemForm.getOrderToppingList();
		if (searchOrderList.isEmpty()) {
			order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);
			// orderをインサート
			order = orderRepository.insert(order);

			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(Integer.parseInt(orderItemForm.getItemId()));
			orderItem.setOrderId(order.getId());
			orderItem.setQuantity(Integer.parseInt(orderItemForm.getQuantity()));
			orderItem.setSize(orderItemForm.getSize());
			// orderIteｍをインサート
			orderItem = orderItemRepository.insert(orderItem);

			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setOrderItemId(orderItem.getId());
			if (orderToppingList != null) {
				for (Integer toppingId : orderToppingList) {
					orderTopping.setToppingId(toppingId);
					// orderToppingをインサート
					orderToppingRepository.insert(orderTopping);
				}
			}
		} else {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(Integer.parseInt(orderItemForm.getItemId()));
			Order existOrder = searchOrderList.get(0);
			Integer existOrderId = existOrder.getId();
			orderItem.setOrderId(existOrderId);
			orderItem.setQuantity(Integer.parseInt(orderItemForm.getQuantity()));
			orderItem.setSize(orderItemForm.getSize());
			// orderIteｍをインサート
			orderItem = orderItemRepository.insert(orderItem);

			OrderTopping orderTopping = new OrderTopping();
			if (orderToppingList != null) {
				for (Integer toppingId : orderToppingList) {
					orderTopping.setOrderItemId(orderItem.getId());
					orderTopping.setToppingId(toppingId);
					// orderToppingをインサート
					orderToppingRepository.insert(orderTopping);

				}
			}
		}
	}

}
