package com.springboot.PayLoads;

public class RoleDto {
	
	private int roleId;
	private String roleName;
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleDto(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
