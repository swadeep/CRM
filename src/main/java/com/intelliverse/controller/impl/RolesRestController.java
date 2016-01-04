package com.intelliverse.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intelliverse.controller.IRolesRestController;
import com.intelliverse.model.Roles;
import com.intelliverse.repository.RolesRepository;

@RestController
@RequestMapping("/roles")
public class RolesRestController implements IRolesRestController {
	
	@Autowired
	private RolesRepository repo;

	@Override
	@RequestMapping(method = RequestMethod.POST, value="/createRoles")
	public Roles createRoles(@RequestBody Roles roles) {
		return repo.save(roles);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value="/updateRoles/{id}")
	public Roles updateRoles(@PathVariable("id") String id, @RequestBody Roles roles) {
		Roles updateRoles = repo.findOne(id);
		updateRoles.setRoleName(roles.getRoleName());
		updateRoles.setRoleLevel(roles.getRoleLevel());
		return repo.save(updateRoles);
	}

}
