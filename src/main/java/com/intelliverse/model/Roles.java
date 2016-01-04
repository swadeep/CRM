package com.intelliverse.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Roles implements Serializable{

	private static final long serialVersionUID = 4105026556155634354L;

	public Roles() {
		
	}
	
	@Id
	private String id;
	private String roleName;
	private String roleLevel;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	
	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleName=" + roleName + ", roleLevel=" + roleLevel + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleLevel == null) ? 0 : roleLevel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roles other = (Roles) obj;
		if (roleLevel == null) {
			if (other.roleLevel != null)
				return false;
		} else if (!roleLevel.equals(other.roleLevel))
			return false;
		return true;
	}
	
	

}
