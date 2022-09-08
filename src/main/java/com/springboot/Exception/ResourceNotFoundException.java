package com.springboot.Exception;

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	int fieldValue;
	String fieldValue1;
	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s not found with %s : %d", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue1) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue1));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue1;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(int fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	

}
