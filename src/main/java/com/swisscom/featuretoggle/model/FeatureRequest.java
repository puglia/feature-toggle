package com.swisscom.featuretoggle.model;

import java.util.List;

public class FeatureRequest {

	private Long customerId;
	private List<FeatureResponse> features;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<FeatureResponse> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureResponse> features) {
		this.features = features;
	}

}
