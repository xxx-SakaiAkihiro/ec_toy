package co.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.example.domain.User;
import co.example.form.RegisterUserForm;
import co.example.service.RegisterUserService;

@Controller
@RequestMapping("/toInsert")
public class RegisterUserController {

	@Autowired
	private RegisterUserService registerUserService;
	
	@ModelAttribute
	public RegisterUserForm setUpForm() {
		return new RegisterUserForm();
	}
	
	@RequestMapping("")
	public String index(Model model) {
		return "register_user";
	}

	/**
	 * ユーザ情報を登録する.
	 * 
	 * @param userForm ユーザ情報
	 * @return 「ログイン画面」にフォワード
	 */
	@RequestMapping("/insert")
	public String insert(@Validated RegisterUserForm registerUserForm, BindingResult result, Model model) {
		User user = new User();

		// メールアドレスの重複チェック
		User userEmail = registerUserService.findByEmail(registerUserForm.getEmail());
		if (userEmail != null) {
			result.rejectValue("email", "null", "そのメールアドレスはすでに使われています");
		}

		// パスワードと確認用パスワードのチェック
		if (!(registerUserForm.getPassword().equals(registerUserForm.getConfirmationPassword()))) {
			result.rejectValue("confirmationPassword", "null", "パスワードと確認用パスワードが異なります");
		}
		// エラーが一つでもあれば入力画面に戻る
		if (result.hasErrors()) {
			return index(model);
		}
		BeanUtils.copyProperties(registerUserForm, user);
		System.out.println("ユーザー情報は"+user);
		registerUserService.insert(user);
		return "login";
	}

}
