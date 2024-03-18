package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;
@Service
public class UserServiceimpl implements UserService{

	@Autowired
	UserRepository userRepository;

	public String addUsers(User user) {
		userRepository.save(user);
		return "User Added Successfully!";
	}

	//logic to check the dublicate enteries 
	public boolean emailExists(String email) {
		if(userRepository.findByEmail(email)!=null) {
			return true;
		}else {
			return false;
		}

	}

//	public boolean passwordExists(String password) {
//		if(userRepository.findByPassword(password)!=null) {
//			return true;
//		}else {
//			return false;
//		}
//	}

	public boolean validateuser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
		    // Call methods on the user object here
			String dbpwd = user.getPassword();
			if(password.equals(dbpwd)) {
				return true;
			}else {
				return false;
			}
		} else {
		   System.out.println("user not present");
		   return false;
		}
		
	}

	public String getRole(String email) {
		User user = userRepository.findByEmail(email);
		 return user.getRole();
		
		
	}

	public User getUser(String email) {
		 User userbyEmail = userRepository.findByEmail(email);
		 return userbyEmail;
	}
	public void updateUser(User user) {
		userRepository.save(user);
	}
}
