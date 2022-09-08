package com.springboot.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Category;
import com.springboot.Exception.ResourceNotFoundException;
import com.springboot.PayLoads.CategoryDto;
import com.springboot.Repository.CategoryRepository;
import com.springboot.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	  @Autowired
	  private CategoryRepository categoryRepo;
	  
	  @Autowired
	  private ModelMapper modelMapper;
	  
	
	@Override
	public CategoryDto createCategory(CategoryDto catdto) {
		Category category=this.modelMapper.map(catdto,Category.class);
		Category updatedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto UpdateCategory(CategoryDto catdto, int categoryId) {

         Category category=this.categoryRepo.findById(categoryId).
        		 orElseThrow(()-> new ResourceNotFoundException("Category"," Id ",categoryId));
		 category.setCategoryId(categoryId);
		 category.setCategoryTitle(catdto.getCategoryTitle());
		 category.setCategoryDescription(catdto.getCategoryDescription());
		 Category updatedcat=this.categoryRepo.save(category);
         return this.modelMapper.map(updatedcat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {

       Category category=this.categoryRepo.findById(categoryId).
    		   orElseThrow(()-> new ResourceNotFoundException("Category"," Id ",categoryId));
       this.categoryRepo.delete(category);
		
	}

	@Override
	public CategoryDto getSingleCategory(int categoryId) {

		Category category=this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category"," Id ",categoryId));

		return this.modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategory() {
		
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> categorydtos=categories.stream().map((category)->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
		return categorydtos;
	}
	
	

}
