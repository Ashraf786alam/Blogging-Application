package com.springboot.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.PayLoads.ApiResponse;
import com.springboot.PayLoads.CategoryDto;
import com.springboot.Service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> CreateCategory(@Valid @RequestBody CategoryDto catdto){
		
		CategoryDto categorydto=this.categoryService.createCategory(catdto);
		return new ResponseEntity<CategoryDto>(categorydto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> UpdateCategory(@Valid @RequestBody CategoryDto catdto,@PathVariable int categoryId){
		
		CategoryDto updatedcategorydto=this.categoryService.UpdateCategory(catdto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedcategorydto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> DeleteCategory(@PathVariable int categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Category with Id "+categoryId+" Deleted Successfully",true),HttpStatus.OK);
	}
	  
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId){
		
		CategoryDto catdto=this.categoryService.getSingleCategory(categoryId);
		return new ResponseEntity<CategoryDto>(catdto,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> categorydtos=this.categoryService.getCategory();
		return new ResponseEntity<List<CategoryDto>>(categorydtos,HttpStatus.OK);
	}


}
