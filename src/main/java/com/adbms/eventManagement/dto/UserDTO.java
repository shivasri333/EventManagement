package com.adbms.eventManagement.dto;

public class UserDTO {
	
	private Long id;
	
	private String userName;
	
	private String password;
	
	private RoleDTO roleDTO;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String email;
	
	private String licenseNumber;


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public RoleDTO getRoleDTO() {
		return roleDTO;
	}



	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLicenseNumber() {
		return licenseNumber;
	}



	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}



	public UserDTO(Long id, String userName, String password, RoleDTO roleDTO, String firstName, String lastName,
			String phone, String email, String licenseNumber) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.roleDTO = roleDTO;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.licenseNumber = licenseNumber;
	}
	

}
