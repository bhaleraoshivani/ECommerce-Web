package com.ecommerce.entitiy;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecommerce.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="product")
public class Product 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private Long price;
	
	@Lob
	private String description;
	
	@Lob
	@Column(columnDefinition = "Longblob")
	private byte[]img;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "category_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Category category;
	
	
	public ProductDto getDto()
	{
		ProductDto productDto = new ProductDto();
		productDto.setId(id);
		productDto.setName(name);
		productDto.setPrice(price);
		productDto.setDescription(description);
		productDto.setByteImg(img);
		productDto.setCategoryId(category.getId());
		productDto.setCategoryName(category.getName());
		return productDto;
		
	}


	public Product(Long id, String name, Long price, String description, byte[] img, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.img = img;
		this.category = category;
	}


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getPrice() {
		return price;
	}


	public void setPrice(Long price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public byte[] getImg() {
		return img;
	}


	public void setImg(byte[] img) {
		this.img = img;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	
	
	
	
	
	
	
}
