package co.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.example.domain.LoginUser;
import co.example.form.OrderItemForm;
import co.example.service.AddItemService;

@Controller
@RequestMapping("/add")
public class AddItemController {

	@Autowired
	private AddItemService addItemService;
	
	@RequestMapping("/addItem")
	public String addItem(OrderItemForm orderItemForm, @AuthenticationPrincipal LoginUser loginUser) {
		addItemService.addItem(orderItemForm, loginUser);
		return "redirect:/ShowOrderItem";
	}
	
}

