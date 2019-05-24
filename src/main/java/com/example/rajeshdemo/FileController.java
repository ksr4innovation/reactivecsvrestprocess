package com.example.rajeshdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.reactivex.Observable;

@RestController
public class FileController {

	
	@Autowired
	FileParseService fileParseService;
	
	@Autowired
	PhoneService phoneService;
	
	@PostMapping(value="/uploadFile",consumes="multipart/form-data")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
		
		InputStream in;
		try {
			in = file.getInputStream();
			Observable<InputRecord> employee =	 fileParseService.feed(in);

		      List<InputRecord>  list = new ArrayList<>();
			employee.subscribe(emp -> {phoneService.saveRecord(emp);
			list.add(emp);}, emp ->  {
				System.out.println("  From On Error ");
			}, () ->  {
				System.out.println(" from on complete ");
				phoneService.createCSVFile(list);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
       
    }
}
