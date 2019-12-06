package co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.example.domain.User;
import co.example.repository.UserRepository;

@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserRepository userRepository;
	

	public void insert(User user) {
		userRepository.insert(user);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
