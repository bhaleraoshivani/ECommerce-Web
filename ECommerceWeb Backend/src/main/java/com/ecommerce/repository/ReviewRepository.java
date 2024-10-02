package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entitiy.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	List<Review> findAllByProductId(Long productId);

}
