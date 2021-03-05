package com.swisscom.featuretoggle.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.swisscom.featuretoggle.model.Feature;
import com.swisscom.featuretoggle.model.FeatureRequest;
import com.swisscom.featuretoggle.model.FeatureResponse;
import com.swisscom.featuretoggle.model.FeatureVO;
import com.swisscom.featuretoggle.model.PagingInfo;
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
	
	public Page<FeatureVO> list(PagingInfo info) {
		Pageable pageable = info.toPageable();
		Page<Feature> pagedResult = featureRepository.findAll(pageable);
		List<FeatureVO> listVo = StreamSupport.stream(pagedResult.spliterator(), false)
				.map(feature -> new FeatureVO(feature))
				.collect(Collectors.toList());
		return new PageImpl<>(listVo, pageable,pagedResult.getTotalElements());
	}
	
	public Page<FeatureVO> list(Long customerId,PagingInfo info) {
		Pageable pageable = info.toPageable();
		Page<Feature> pagedResult = featureRepository.findByCustomerId(customerId,pageable);
		List<FeatureVO> listVo = StreamSupport.stream(pagedResult.spliterator(), false)
				.map(feature -> new FeatureVO(feature))
				.collect(Collectors.toList());
		return new PageImpl<>(listVo, pageable,pagedResult.getTotalElements());
	}
	
	public List<FeatureResponse> queryFeatures(FeatureRequest request) {
		List<String> names = request.getFeatures().parallelStream().map(FeatureResponse::getName)
							.collect(Collectors.toList());
		List<Feature> result = featureRepository.findByFeatureTechnicalNameIn(names);
		List<FeatureResponse> featureResponses = new ArrayList<>();
		for(Feature feature: result) {
			FeatureResponse response = new FeatureResponse();
			response.setActive(feature.getCustomers().contains(request.getCustomerId()));
			response.setExpired(feature.getExpiresOn() != null
								?feature.getExpiresOn().isBefore(LocalDateTime.now())
								:false);
			response.setInverted(feature.getInverted());
			response.setName(feature.getTechnicalName());
			featureResponses.add(response);
		}
		
		return featureResponses;
	}
}
