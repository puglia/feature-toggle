package com.swisscom.featuretoggle.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swisscom.featuretoggle.model.Feature;

@Repository
public interface FeatureRepository extends  CrudRepository<Feature, Long> {
	

}
