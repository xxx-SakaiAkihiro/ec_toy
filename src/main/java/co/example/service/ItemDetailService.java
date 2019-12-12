package co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.example.domain.Item;
import co.example.domain.Topping;
import co.example.repository.ItemRepository;
import co.example.repository.ToppingRepository;

@Service
@Transactional
public class ItemDetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	public Item showItemDetail(Integer id) {
		Item item = itemRepository.load(id);
		List<Topping> toppingList = toppingRepository.findAll();
		item.setToppingList(toppingList);
		return item;
	}
	
	
	
}
