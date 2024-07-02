package com.adbms.eventManagement.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adbms.eventManagement.entity.Image;
import com.adbms.eventManagement.repo.UploadImageRepo;

@Service
public class ImageService {
	
	@Autowired
	private UploadImageRepo imageRepo;
	
	public Image saveImage(Image im) {
		return imageRepo.save(im);
	}

	public String uploadImage(MultipartFile image) {
		if(image!=null) {
			try {
				File saveFile = new ClassPathResource("static/images").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+image.getOriginalFilename());
				System.out.println(path);
				Files.copy(image.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				return "Image Uploaded";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Error While Uploading Image";
	}
	

}
