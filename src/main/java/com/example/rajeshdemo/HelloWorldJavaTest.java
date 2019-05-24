package com.example.rajeshdemo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class HelloWorldJavaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		
		
	  String  date = 	dateTimeFormatter.format(LocalDate.now(ZoneId.of("US/Eastern")).minusDays(180));

	  System.out.println(" ===========================  "+date);
	  
	}

}
