package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entitiy.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {
	
	List<FAQ> findAllByProductId(Long productId);

}
