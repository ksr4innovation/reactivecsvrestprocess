package com.example.rajeshdemo;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

@Service
public class FileParseService {
	
	
	public  Observable<InputRecord> feed(InputStream file) {
		System.out.println("   From   ObservableEmitter feed======= ");
		
		return Observable.create(emitter -> parseFile(emitter,file));
	}
	public  void parseFile(ObservableEmitter<InputRecord> emitter,InputStream file) {
		System.out.println("   From   ObservableEmitter method======= ");
		Scanner rowScanner = new Scanner(file);
		
		int i = 0;
		
           while(rowScanner.hasNext()) {
            
        	 
            String value  = rowScanner.next();
            
            if (i > 0) {
        
            
            String [] columns = value.split(",");
            
            InputRecord record = new InputRecord(columns[0],columns[3],columns[4]);
            System.out.println("   From Obserable feed method======= "+record);
        	emitter.onNext(record);
        	   }
        	   i++;
           }
        	   emitter.onComplete();
           
            
        
		
		
	}
}
