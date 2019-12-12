package co.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.example.domain.LoginUser;
import co.example.domain.Order;
import co.example.service.ShowOrderedItemService;

/**
 * 注文確認画面を表示するコントローラ.
 * 
 * @author sakai
 *
 */
@Controller
@RequestMapping("/ShowOrderedItem")
public class ShowOrderedItemContoroller {

	@Autowired
	private ShowOrderedItemService showOrderedItemService;

	/**
	 * 注文画面の中身を表示する.
	 * 
	 * @param model     モデル
	 * @param loginUser ユーザーID
	 * @return 「注文確認画面」を表示
	 */
	@RequestMapping("")
	public String showOrderedItem(Model model, @AuthenticationPrincipal LoginUser loginuser) {
		List<Order> orderList = showOrderedItemService.showOrderedItem(loginuser.getUser().getId());
		model.addAttribute("order", orderList.get(0));
		model.addAttribute("orderList", orderList);

		model.addAttribute("name", loginuser.getUser().getName());
		model.addAttribute("email", loginuser.getUser().getEmail());
		model.addAttribute("userZipcode", loginuser.getUser().getZipcode());
		model.addAttribute("userAddress", loginuser.getUser().getAddress());
		model.addAttribute("tel", loginuser.getUser().getTelephone());
		return "order_confirm";

	}

}
