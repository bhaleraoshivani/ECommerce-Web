package com.ecommerce.services.admin.category;

import java.util.List;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.entitiy.Category;

public interface CategoryService 
{
	
	Category createCategory(CategoryDto  categoryDto);
	
	
	List<Category> getAllCategories();

}
