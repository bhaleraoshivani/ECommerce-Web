package com.ecommerce.entitiy;

import com.ecommerce.dto.UserDto;
import com.ecommerce.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
    private String password;
    private String mobile;
    private String gender;
	private UserRole role;
	
	@Lob
	@Column(columnDefinition = "Longblob")
	private byte[]img;

	

	public User(Long id, String email, String name, String password, String mobile,String gender, UserRole role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.gender = gender;
		this.role = role;
		
	}
	
	
	public UserDto getDto()
	{
		UserDto userDto = new UserDto();
		userDto.setId(id);
		userDto.setName(name);
		userDto.setEmail(email);
		userDto.setMobile(mobile);
		userDto.setGender(gender);
		return userDto;
		
	}
	
	

	
	public User() {
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
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


	public UserRole getRole() {
		return role;
	}



	public void setRole(UserRole role) {
		this.role = role;
	}


	public byte[] getImg() {
		return img;
	}


	public void setImg(byte[] img) {
		this.img = img;
	}



	


	
	
	
	
}