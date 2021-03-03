package com.swisscom.featuretoggle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.swisscom.featuretoggle.model.Feature;

@Repository
public interface FeatureRepository extends  PagingAndSortingRepository<Feature, Long> {
	
	@Query("select feat from Feature feat join feat.customers ct where ct = ?1")
	Page<Feature> findByCustomerId(Long customerId, Pageable paging); 
	

}
