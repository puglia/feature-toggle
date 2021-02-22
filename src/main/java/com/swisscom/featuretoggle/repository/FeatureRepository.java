package com.swisscom.featuretoggle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swisscom.featuretoggle.model.Feature;

@Repository
public interface FeatureRepository extends  CrudRepository<Feature, Long> {
	
	@Query("select feat from Feature feat join feat.customers ct where ct = ?1")
	List<Feature> findByCustomerId(Long customerId); 
	

}
