package com.intelliverse.controller.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intelliverse.controller.IUserRestController;
import com.intelliverse.model.Users;
import com.intelliverse.repository.UsersRepository;

@RestController
@RequestMapping("/users")
public class UserRestController implements IUserRestController {

	@Autowired
	private UsersRepository repo;

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/createUsers")
	public Users createUsers(@RequestBody Users users) {
		return repo.save(users);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/updateUsers/{id}")
	public Users updateUsers(@RequestParam String id, @RequestBody Users users) {
		Users updateUsers = repo.findOne(id);
		updateUsers.setFirstName(users.getFirstName());
		updateUsers.setLastName(users.getLastName());
		updateUsers.setPhoneNumber(users.getPhoneNumber());
		updateUsers.setEmail(users.getEmail());
		updateUsers.setCompany(users.getCompany());
		updateUsers.setCountry(users.getCountry());
		updateUsers.setState(users.getState());
		updateUsers.setCity(users.getCity());
		return repo.save(updateUsers);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/getUserDetails/{id}")
	public Users getUserDetails(@PathVariable("id") String id) {
		return repo.findOne(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword/{id}")
	public Users updatePassword(@PathVariable("id") String id, @RequestBody Users users) {
		Users updateUsers = repo.findOne(id);
		updateUsers.setPassword(users.getPassword());
		return repo.save(updateUsers);
	}

}
