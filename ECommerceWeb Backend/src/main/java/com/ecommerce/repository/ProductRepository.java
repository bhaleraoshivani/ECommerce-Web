package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entitiy.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAllByNameContaining(String title);
	
	List<Product> findByCategoryId(Long categoryId);
	
	
	


}
