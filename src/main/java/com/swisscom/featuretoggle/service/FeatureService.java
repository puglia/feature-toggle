package com.swisscom.featuretoggle.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.swisscom.featuretoggle.model.Feature;
import com.swisscom.featuretoggle.model.FeatureVO;
import com.swisscom.featuretoggle.repository.FeatureRepository;

@Service
public class FeatureService {

	@Inject
	FeatureRepository featureRepository;

	public FeatureVO find(Long id) {
		Feature feature =  featureRepository.findById(id).get();
		return new FeatureVO(feature);
	}

	public FeatureVO save(FeatureVO feat) {
		Feature feature =  featureRepository.save(feat.toModel());
		return new FeatureVO(feature);
	}
	
	public List<FeatureVO> list() {
		return StreamSupport.stream(featureRepository.findAll().spliterator(), false)
				.map(feature -> new FeatureVO(feature))
				.collect(Collectors.toList());
	}
}
