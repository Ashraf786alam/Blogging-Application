package com.springboot.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
    public String UploadImage(String path,MultipartFile file) throws IOException;
	
	public InputStream ServeFile(String path,String fileName) throws FileNotFoundException;


}
