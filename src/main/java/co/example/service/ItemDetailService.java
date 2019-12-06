package co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.example.domain.Item;
import co.example.repository.ItemRepository;

@Service
@Transactional
public class ItemDetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	public Item load(Integer id) {
		Item item = itemRepository.load(id);
		return item;
	}
	
	
	
}
