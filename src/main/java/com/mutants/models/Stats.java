package com.mutants.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name="stats",indexes = @Index(name="idx_dna", columnList = "dna", unique = true))
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="dna",columnDefinition="TEXT")
	String dna;
    @Column(name="is_mutant")
	Boolean isMutant;
    
	public Stats() {
		super();
	}

	public Stats(long id, String dna, Boolean isMutant) {
		super();
		this.id = id;
		this.dna = dna;
		this.isMutant = isMutant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public Boolean getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(Boolean isMutant) {
		this.isMutant = isMutant;
	}

	@Override
	public String toString() {
		return "Stats [id=" + id + ", dna=" + dna + ", isMutant=" + isMutant + "]";
	}

	
}

