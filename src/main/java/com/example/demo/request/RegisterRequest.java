package com.example.demo.request;

import javax.validation.constraints.NotBlank;

public class RegisterRequest {
	@NotBlank(message = "Email cannot be blank")
	private String email;

	@NotBlank(message = "Password cannot be blank")
	private String password;

	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	@NotBlank(message = "Type cannot be blank")
	private String type;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
