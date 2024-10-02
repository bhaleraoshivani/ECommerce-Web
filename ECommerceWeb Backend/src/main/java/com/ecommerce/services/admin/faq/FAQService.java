package com.ecommerce.services.admin.faq;

import com.ecommerce.dto.FAQDto;

public interface FAQService {
	
	FAQDto postFAQ(Long productId, FAQDto faqDto);

}
