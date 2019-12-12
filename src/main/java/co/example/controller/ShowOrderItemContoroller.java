package co.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import co.example.domain.LoginUser;
import co.example.domain.Order;
import co.example.service.ShowOrderItemService;

/**
 * ショッピングカート画面を表示するコントローラ.
 * 
 * @author sakai
 *
 */
@Controller
@RequestMapping("/ShowOrderItem")
public class ShowOrderItemContoroller {

	@Autowired
	private ShowOrderItemService showOrderItemService;

	@Autowired
	private HttpSession session;

	/**
	 * ショッピングカートの中身を表示する.
	 * 
	 * @param model     モデル
	 * @param loginUser ユーザーID
	 * @return 「ショッピングカート画面」を表示
	 */
	@RequestMapping("")
	public String showOrderItem(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if (loginUser == null) {
			userId = (Integer) session.getAttribute("userId");
		} else {
			userId = loginUser.getUser().getId();
		}
		List<Order> orderList = showOrderItemService.findByUserIdAndStatus(userId);
		// ショッピングカートの中身が0件の場合
		if (orderList.size() == 0 || CollectionUtils.isEmpty(orderList.get(0).getOrderItemList())) {
			String str = "カートに商品がありません";
			model.addAttribute("str", str);
			// ショッピングカートの中身が1件以上の場合
		} else {
			model.addAttribute("order", orderList.get(0));
		}
		model.addAttribute("orderList", orderList);

		return "cart_list";
	}

}
