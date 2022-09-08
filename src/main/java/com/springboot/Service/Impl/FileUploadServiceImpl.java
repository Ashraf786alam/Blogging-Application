package com.springboot.Service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {

           //File name
		String name=file.getOriginalFilename();
		
		//FullPath
		String randomID=UUID.randomUUID().toString();
		String name1=randomID.concat(name.substring(name.lastIndexOf('.')));
		
		String filePath=path+File.separator+name1;
		
		
		
		
		//create folder if not created
		
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		//file copy
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		return name1;
	}

	@Override
	public InputStream ServeFile(String path, String fileName) throws FileNotFoundException {

    String fullPath=path+File.separator+fileName;
    InputStream is=new FileInputStream(fullPath);
		return is;
	}

}
