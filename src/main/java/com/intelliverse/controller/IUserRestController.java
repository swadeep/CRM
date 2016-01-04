package com.intelliverse.controller;

import com.intelliverse.model.Users;

public interface IUserRestController {
	
	public Users createUsers(Users users);
	
	public Users updateUsers(String id, Users users);
	
	public Users getUserDetails(String id);
	
	public Users updatePassword(String id, Users users);
	
}
