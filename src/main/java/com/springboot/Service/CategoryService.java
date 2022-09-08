package com.springboot.Service;

import java.util.List;

import com.springboot.PayLoads.CategoryDto;

public interface CategoryService {
	
	
	
	public CategoryDto UpdateCategory(CategoryDto catdto,int id);
	
	public void deleteCategory(int categoryId);
	
	public CategoryDto getSingleCategory(int categoryId);
	
	public List<CategoryDto> getCategory();

	public CategoryDto createCategory(CategoryDto catdto);

}
