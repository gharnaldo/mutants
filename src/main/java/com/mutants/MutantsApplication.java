package com.mutants;



import java.net.URISyntaxException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.mutants.MutantsApplication;




@SpringBootApplication
@EnableTransactionManagement
public class MutantsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MutantsApplication.class, args);
			
	}
	
	public void run(String... args) throws Exception, URISyntaxException {


	
	

}

}