package co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.example.domain.User;
import co.example.repository.UserRepository;

@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * ユーザ情報を登録する.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		//パスワードをハッシュ化する
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		userRepository.insert(user);
	}
	
	/**
	 * メールアドレスからユーザ情報を取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザ情報
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
