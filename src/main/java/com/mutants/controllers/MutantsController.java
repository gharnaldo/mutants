package com.mutants.controllers;


import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mutants.exceptions.CustomException;
import com.mutants.models.dtos.MutantDto;
import com.mutants.models.dtos.StatsDtoResponse;
import com.mutants.services.MutantsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
public class MutantsController {
	
	@Autowired
    MutantsService mutantsService;

	@ApiOperation(value = "Returns if a DNA belongs to a no-mutant or mutant")
    @PostMapping(value="/isMutant", produces = "application/json")
    ResponseEntity isMutant(@RequestBody MutantDto mutantDto) throws CustomException, IOException, JSONException {
		ResponseEntity response;
		try {
			response = mutantsService.isMutant(mutantDto.getDna());
        }catch(Exception e){
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
		return new ResponseEntity<>(response, HttpStatus.OK);  
    }	
	
	@ApiOperation(value = "Returns stats of differents DNA registered")
    @GetMapping(value="/stats", produces = "application/json")
	ResponseEntity<StatsDtoResponse> stats() throws CustomException, IOException, JSONException {
		StatsDtoResponse statsDtoResponse;
		try {
			statsDtoResponse = mutantsService.findAllStats();
        }catch(Exception e){
        	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
		return new ResponseEntity<StatsDtoResponse>(statsDtoResponse, HttpStatus.OK);  
    }	
}
