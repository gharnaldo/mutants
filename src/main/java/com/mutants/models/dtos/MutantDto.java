package com.mutants.models.dtos;

import java.util.Arrays;

public class MutantDto {

	String[] dna;

	public MutantDto(String[] dna) {
		super();
		this.dna = dna;
	}

	public MutantDto() {
		super();
	}

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

	@Override
	public String toString() {
		return "MutantDto [dna=" + Arrays.toString(dna) + "]";
	}
	
}
