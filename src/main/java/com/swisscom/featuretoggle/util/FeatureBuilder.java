package com.swisscom.featuretoggle.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.swisscom.featuretoggle.model.Feature;
import com.swisscom.featuretoggle.model.FeatureVO;

public class FeatureBuilder {
	protected Long id;

	protected LocalDateTime updatedOn;

	private String displayName = "Default Name";
	private String technicalName = "default-name";
	protected LocalDateTime expiresOn = null;
	private String description = "Default Decription";
	private Boolean inverted = false;
	private List<Long> customers = new ArrayList<Long>();

	public FeatureBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public FeatureBuilder updatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
		return this;
	}

	public FeatureBuilder displayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public FeatureBuilder technicalName(String technicalName) {
		this.technicalName = technicalName;
		return this;
	}

	public FeatureBuilder expiresOn(LocalDateTime expiresOn) {
		this.expiresOn = expiresOn;
		return this;
	}

	public FeatureBuilder description(String description) {
		this.description = description;
		return this;
	}

	public FeatureBuilder inverted(Boolean inverted) {
		this.inverted = inverted;
		return this;
	}

	public FeatureBuilder customers(List<Long> customers) {
		this.customers = customers;
		return this;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getTechnicalName() {
		return technicalName;
	}

	public LocalDateTime getExpiresOn() {
		return expiresOn;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getInverted() {
		return inverted;
	}

	public List<Long> getCustomers() {
		return customers;
	}

	public Feature build() {
		return new Feature(this);
	}

	public FeatureVO buildVO() {
		return new FeatureVO(this);
	}

}
