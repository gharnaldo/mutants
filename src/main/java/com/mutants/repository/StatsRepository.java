package com.mutants.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mutants.models.Stats;

@Repository("StatsRepository")
public interface StatsRepository extends CrudRepository<Stats,Long> {

	Stats findByDna(String dna);
	
	@Query(value = "SELECT count(*) FROM stats WHERE is_mutant = true",nativeQuery = true)
	Long getStats();
	
}
