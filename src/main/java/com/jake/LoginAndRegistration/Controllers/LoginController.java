package com.jake.LoginAndRegistration.Controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jake.LoginAndRegistration.Models.LoginUser;
import com.jake.LoginAndRegistration.Models.User;
import com.jake.LoginAndRegistration.Services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userServ;
	
	@GetMapping("")
	public String login(Model model) {
		model.addAttribute("user",new User());
		model.addAttribute("loginUser",new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userServ.isValid(user, result);
		if (result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "login.jsp";
		}
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		User newUser = userServ.create(user);
		session.setAttribute("userId", newUser.getId());
		return "redirect:/login/welcome";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
		User user = userServ.login(loginUser, result);
		if (user == null) {
			model.addAttribute("user", new User());
			return "login.jsp";
		}
		session.setAttribute("userId", user.getId());
		return"redirect:/login/welcome";
	}
	
	@GetMapping("/welcome")
	public String home(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/login";
		}
			
		model.addAttribute("user", userServ.findById((Long) session.getAttribute("userId")));
		return "home.jsp";
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeValue("userId");
		return "redirect:/login";
	}
}
