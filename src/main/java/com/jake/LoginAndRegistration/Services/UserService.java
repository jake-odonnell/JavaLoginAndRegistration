package com.jake.LoginAndRegistration.Services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jake.LoginAndRegistration.Models.LoginUser;
import com.jake.LoginAndRegistration.Models.User;
import com.jake.LoginAndRegistration.Repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	//Extra validations
	public void isValid(User user, BindingResult result) {
		
		//Confirm only letters in username
		char[] chars = user.getUsername().toCharArray();
		boolean isAlpha = true;
		for (char c : chars) {
			if(!Character.isLetter(c)) {
				isAlpha = false;
			}
		}
		if (!isAlpha) {				
			result.rejectValue("username", "Alpha", "Username can only contain letters");
		}
		
		//Check for repeat email
		Optional<User> potentialUser = userRepo.findByEmail(user.getEmail());
		if (potentialUser.isPresent()){
			result.rejectValue("email", "Duplicate", "Must use unique email");
		}
		
		//Confirm the two passwords match
		if (!user.getPassword().equals(user.getConfPassword())) {
			result.rejectValue("confPassword", "Match", "Passwords must match");
		}
		return;
	}
	
	//Save new user
	public User create(User user) {
		return userRepo.save(user);
	}
	
	
	public User login(LoginUser loginUser, BindingResult result) {
		Optional<User> potentialUser = userRepo.findByEmail(loginUser.getEmail());
		if (potentialUser.isPresent()) {
			User user = potentialUser.get();
			if (BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
				return user;
			} else {
				result.rejectValue("email", "Match", "Incorrect username or password");
				return null;
			}
		} else {
			result.rejectValue("password", "Match", "Incorrect username or password");
			return null;
		}
	}
	
	public User findById(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			return potentialUser.get();
		} else {
			return null;
		}
	}
}
	
