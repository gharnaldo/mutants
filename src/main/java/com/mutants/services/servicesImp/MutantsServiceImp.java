package com.mutants.services.servicesImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mutants.constants.Constants;
import com.mutants.models.Stats;
import com.mutants.models.dtos.StatsDtoResponse;
import com.mutants.repository.StatsRepository;
import com.mutants.services.MutantsService;

@Service
public class MutantsServiceImp implements MutantsService {
    
	@Autowired
    @Qualifier("StatsRepository")
    private StatsRepository statsRepository;


    @Override
    public ResponseEntity isMutant(String[] dna) throws IOException, JSONException {
        Pattern pattern;
        boolean horizontal = false;
        boolean verticals = false;
        boolean oblique = false;
        String ValidPattern = "";
        int lenElement = 0;
        String dnaStr = String.join(",", dna);
        String validLetters = Constants.VALID_LETTERS;
        int total = dna.length;
        System.out.println("Total elements: " + total);
        if(total > 0)
        {
    		for (int m=0; m<validLetters.length(); m++)
    		{        			        		
    			System.out.println("validLetters.charAt(m): " + validLetters.charAt(m));
    			ValidPattern += validLetters.charAt(m)+"{"+Constants.VALID_Q+"}|"; 	        	
	        }
    		ValidPattern = ValidPattern.substring(0,ValidPattern.length()-1);
	        pattern = Pattern.compile("^"+ValidPattern+"$");
	        System.out.println("letrasValidasPattern: " + ValidPattern);
	        System.out.println("dna.toString():"+dnaStr);
	        int[][] verArray = new int[total][total]; 
	        int[][] obliArray = new int[total][total]; 
	        int[][] obliArray2 = new int[total][total]; 

	        for (int i=0; i<dna.length; i++)
	        {     	        	
	        	if (dna[i].length()>0)
	        	{
		        	if (i==0)
		        	{
		        		lenElement = dna[i].length();	        		
		        	}
		        	if (dna[i].length() != lenElement)
		        	{
		        		return new ResponseEntity(Constants.DNA_INVALID_2, HttpStatus.FORBIDDEN);
		        	}        	
	        	}else{
	        		return new ResponseEntity(Constants.DNA_INVALID_1, HttpStatus.FORBIDDEN);
	        	}
	        }	        
	        
	        
	        for (int i=0; i<dna.length; i++)
	        {        	       
	        	if (pattern.matcher(dna[i]).lookingAt())
	        	{
	        		horizontal = true;
	        		break;
	        	}
	        	if (i > 0)
	        	{        		
	        		//obliArray
	        		for (int m=0; m<dna[i].length(); m++)
	        		{        			        		
	    				int valid = validLetters.indexOf(dna[i].charAt(m)); 
	    				if (valid>=0)
	    				{
	    					//vertical
	                		if (dna[i].charAt(m)==dna[i-1].charAt(m))
	                		{
	                			if (verArray[i-1][m]==0)
	                			{
	                				verArray[i][m] = verArray[i-1][m] + 2;
	                			}else {
	                				verArray[i][m] = verArray[i-1][m] + 1;
	                			}
	                			if (verArray[i][m] == 4)
	                			{
	                				verticals = true;
	                				break;
	                			}
	                		}else {
	                			verArray[i][m] = 0;
	                		}
	    					//i2d
	    					if (m>0)
	    					{
	                    		if (dna[i].charAt(m)==dna[i-1].charAt(m-1))
	                    		{
	                    			if (obliArray[i-1][m-1]==0)
	                    			{
	                    				obliArray[i][m] = obliArray[i-1][m-1] + 2;
	                    			}else {
	                    				obliArray[i][m] = obliArray[i-1][m-1] + 1;
	                    			}
	                    			if (obliArray[i][m] == 4)
	                    			{
	                    				oblique = true;
	                    				break;
	                    			}
	                    		}else {
	                    			obliArray[i][m] = 0;
	                    		}
	    					}else{
	    						obliArray[i][m] = 0;
	                		}
	    					//d2i
	    					if (m<dna[i].length()-1)
	    					{
	                    		if (dna[i].charAt(m)==dna[i-1].charAt(m+1))
	                    		{
	                    			if (obliArray2[i-1][m+1]==0)
	                    			{
	                    				obliArray2[i][m] = obliArray2[i-1][m+1] + 2;
	                    			}else {
	                    				obliArray2[i][m] = obliArray2[i-1][m+1] + 1;
	                    			}
	                    			if (obliArray2[i][m] == 4)
	                    			{
	                    				oblique = true;
	                    				break;
	                    			}
	                    		}else {
	                    			obliArray2[i][m] = 0;
	                    		}
	    					}else{
	    						obliArray2[i][m] = 0;
	                		}    					
	    				}
	            		else
	            		{
	            			obliArray[i][m] = 0;
	            			obliArray2[i][m] = 0;
	            			verArray[i][m] = 0;
	            		}
	        		}          		
	
	        	}        	
	        }
	        
			printArray(verArray);
			printArray(obliArray);
			printArray(obliArray2);
		     
	        System.out.println("****** RESULTS ******");
	        System.out.println("Horizontal: "+horizontal);
	        System.out.println("Verticals: "+verticals);
	        System.out.println("Oblique: "+oblique);
	        System.out.println("*********************");
	        
	        Stats stats = new Stats();
	        stats.setDna(dnaStr);
	        
	        if(horizontal||verticals||oblique)
	        {
	        	stats.setIsMutant(true);
	        }else{
	        	stats.setIsMutant(false);	        	
	        }	
	        if(statsRepository.findByDna(dnaStr) == null)
	        {
	        	statsRepository.save(stats);
	        }
	        return new ResponseEntity("Mutant: "+stats.getIsMutant().toString().toUpperCase(), HttpStatus.OK);
        } else {
        	return new ResponseEntity(Constants.DNA_EMPTY, HttpStatus.FORBIDDEN);
        }
    }

    
    
    
    @Override
	public StatsDtoResponse findAllStats() {
    	Long stats = statsRepository.count();
    	double ratio = (double)statsRepository.getStats() / (double)stats;
    	StatsDtoResponse statsDtoResponse = new StatsDtoResponse();
    	statsDtoResponse.setCount_human_dna(stats);
    	statsDtoResponse.setCount_mutant_dna(statsRepository.getStats());
    	statsDtoResponse.setRatio(ratio);
    	System.out.println("ratio:"+ratio);
		return statsDtoResponse;
	}




	private void printArray(int[][] array) {
		for (int h = 0; h < array.length; ++h) 
		{			
		    for(int l = 0; l < array[h].length; ++l) 
		    {
		       System.out.print(array[h][l]+" ");
		    }
		    System.out.println("");
		}
		System.out.println("");
    }
}