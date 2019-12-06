package co.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.example.domain.Item;
import co.example.service.ItemService;

@Controller
@RequestMapping("")
public class ShowItemListController {

	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("")
	public String findAllItems(Model model) {
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
	
	
}
