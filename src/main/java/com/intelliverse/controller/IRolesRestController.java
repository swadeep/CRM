package com.intelliverse.controller;

import com.intelliverse.model.Roles;

public interface IRolesRestController {
	
	public Roles createRoles(Roles roles);
	
	public Roles updateRoles(String id, Roles roles);

}
