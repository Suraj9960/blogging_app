package io.javabrains.springsecurityjwt.serviceImpl;

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

import io.javabrains.springsecurityjwt.serviceDao.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// get the name of file 
		
		String filename = file.getOriginalFilename();
		
		//random name generate file
		
		String randomUUID = UUID.randomUUID().toString();
		String fileName = randomUUID.concat(filename.substring(filename.lastIndexOf(".")));
		
		String filePath = path + File.separator + fileName ;
		
		// Create a folder if not created
		
		File file2 = new File(path);
		
		if(!file2.exists()) {
			file2.mkdir();
		}
		
		// copy the file
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return filename;
	}
	
	

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		
		String fullPath = path + File.separator + filename ;
		
		InputStream iStream  = new FileInputStream(fullPath);
		
		return iStream;
	}

}
