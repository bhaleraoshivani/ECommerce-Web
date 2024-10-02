package com.ecommerce.dto;


import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.enums.UserRole;

import lombok.Data;

@Data

public class UserDto 
{
	
	private Long id;
	private String email;
	private String name;
	private String mobile;
	private String gender;
	private UserRole userRole;
	private byte[] byteImg;
	private MultipartFile img;
	


	public UserDto(Long id, String email, String name, String mobile,String gender, byte[] byteImg) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.mobile = mobile;
		this.gender = gender;
		this.byteImg = byteImg;
	}



	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}




	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public UserRole getUserRole() {
		return userRole;
	}



	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}



	public byte[] getByteImg() {
		return byteImg;
	}



	public void setByteImg(byte[] byteImg) {
		this.byteImg = byteImg;
	}



	public MultipartFile getImg() {
		return img;
	}



	public void setImg(MultipartFile img) {
		this.img = img;
	}



	


	


	



	

	

}
