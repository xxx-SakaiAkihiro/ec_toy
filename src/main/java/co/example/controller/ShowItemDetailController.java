package co.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.example.domain.Item;
import co.example.service.ItemDetailService;

@Controller
@RequestMapping("ShowItemDetail")
public class ShowItemDetailController {

	@Autowired
	private ItemDetailService itemDetailService;

	@RequestMapping("")
	public String showItemDetail(Integer id, Model model) {
		Item item = itemDetailService.showItemDetail(id);
		model.addAttribute("item", item);
		return "item_detail";
	}

}
