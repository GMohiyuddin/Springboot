package com.boot.ToDoProject.Hello;

import org.springframework.stereotype.Service;

@Service
public class loginlogic {
	public boolean loginlogic(String user, String password) {
		
		boolean isValidUserName = user.equalsIgnoreCase("munif");
		boolean ValidPassword = password.equalsIgnoreCase("dummy");
		
		return isValidUserName && ValidPassword;
	}
}
