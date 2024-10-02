package com.ecommerce.services.admin.faq;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.FAQDto;
import com.ecommerce.entitiy.FAQ;
import com.ecommerce.entitiy.Product;
import com.ecommerce.repository.FAQRepository;
import com.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl  implements FAQService{
	
	@Autowired
	private FAQRepository faqRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public FAQDto postFAQ(Long productId, FAQDto faqDto)
	{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if(optionalProduct.isPresent()) {
			
			FAQ faq = new FAQ();
			
			faq.setQuestion(faqDto.getQuestion());
			faq.setAnswer(faqDto.getAnswer());
			faq.setProduct(optionalProduct.get());
			
			return faqRepository.save(faq).getFAQDto();
			
		}
		return null;
	}

}
