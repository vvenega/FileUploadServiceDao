package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadServiceDaoController {
	
	private static final String PATH="/users/victorvenegas/desktop/testupload/";
	
	@GetMapping("/FileLoadSetFileRecord/{username}/{namefile}/{outputfile}/{status}")
	public boolean setFileLoadRecord(@PathVariable String username,@PathVariable String namefile,@PathVariable String outputfile,@PathVariable String status) {
		boolean result= false;
		
		try {
			CassandraAdapter.setFileLoadRecord(username,namefile, outputfile, "procesando",PATH);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping("/FileLoadUpdateFileRecord/{username}/{namefile}/{status}/{outputfile}")
	public boolean updateFileLoadRecord(@PathVariable String username,@PathVariable String namefile,@PathVariable String status,@PathVariable String outputfile) {
		
		boolean result=false;
		
		try {
			CassandraAdapter.updateFileLoadRecord(namefile, username,status,outputfile);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping("/GetFileLoadsDao/{username}")
	public List<FileLoadDao> getFileLoads(@PathVariable String username){
		
		List<FileLoadDao> lst;
		
		try {
			lst =CassandraAdapter.getFileLoads(username);
		}catch(Exception e) {
			e.printStackTrace();
			lst = new ArrayList<FileLoadDao>();
		}
		
		return lst;
	}

}
