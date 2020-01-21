package com.mutants.models.dtos;

public class StatsDtoResponse {
	
	Long count_mutant_dna;
	Long count_human_dna;
	double ratio;
	
	public StatsDtoResponse(Long count_mutant_dna, Long count_human_dna, double ratio) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = ratio;
	}

	public StatsDtoResponse() {
		super();
	}

	public Long getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(Long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}

	public Long getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(Long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return "StatsDtoResponse [count_mutant_dna=" + count_mutant_dna + ", count_human_dna=" + count_human_dna
				+ ", ratio=" + ratio + "]";
	}
	
	
}
