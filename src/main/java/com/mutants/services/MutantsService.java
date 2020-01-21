package com.mutants.services;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import com.mutants.models.dtos.StatsDtoResponse;


public interface MutantsService {
	ResponseEntity isMutant(String[] dna) throws IOException, JSONException;
	StatsDtoResponse findAllStats();
}
