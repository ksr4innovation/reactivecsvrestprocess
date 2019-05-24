package com.example.rajeshdemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class PhoneService {

	@Autowired
	RestTemplate restTemplate;
		
	
	public void saveRecord(InputRecord input) {
		
	
	
		  System.out.println(" ======saveRecord================  ");
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
       MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
       map.add("processId", "1");
       map.add("campaignId", "1");
       map.add("phoneNumber", input.getPhoneNumber());
       map.add("callType", input.getCallType());
       map.add("dncType", "0");
       
        HttpEntity<MultiValueMap<String, String>> requestEntity=          new HttpEntity<MultiValueMap<String, String>>(map, headers);
      
        try{
        	
        	
        	// Query parameters
        	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
        	        // Add query parameter
        	        .queryParam("processId", "1").queryParam("campaignId", "1").queryParam("phoneNumber", input.getPhoneNumber())
        	        .queryParam("callType", input.getCallType()).queryParam("dncType", "0");

        	System.out.println(builder.buildAndExpand().toUri());
            ResponseEntity<String> response = restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.POST,  null,String.class);
            
            System.out.println("  saveRecord =======================  "+response.getBody());
            input.setInsertId(new Long(1));
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	
	    }
	}
	
	
	void createCSVFile(List<InputRecord> input) {	
		  
		  List<InputRecord>  fail = new ArrayList<>();
		  
		  input.forEach( record ->{
			  if (Objects.isNull(record.getInsertId())) {
				  fail.add(record);
			  }
		  });
		  
		  try {
			  File file = File.createTempFile("fail", ".csv");
			  
			  Writer writer  = new FileWriter(file);

			  StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
				       .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
				       .build();

			  sbc.write(fail);
			    writer.close();
	            
	         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		
	}
}
