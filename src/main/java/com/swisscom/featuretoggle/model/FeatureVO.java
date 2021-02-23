package com.swisscom.featuretoggle.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.swisscom.featuretoggle.util.FeatureBuilder;

public class FeatureVO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long id;

	protected LocalDateTime updatedOn;

	private String displayName;

	private String technicalName;

	protected LocalDateTime expiresOn;

	private String description;

	private Boolean inverted;

	private List<Long> customers;

	public FeatureVO() {
		super();
	}

	//Easy constructor to convert JPA model to VO
	public FeatureVO(Feature model) {
		this.id = model.getId();
		this.updatedOn = model.getUpdatedOn();
		this.displayName = model.getDisplayName();
		this.technicalName = model.getTechnicalName();
		this.expiresOn = model.getExpiresOn();
		this.description = model.getDescription();
		this.inverted = model.getInverted();
		this.customers = model.getCustomers();
	}
	
	//Convert Vo to JPA model
	public FeatureVO(FeatureBuilder builder) {
		this.id = builder.getId();
		this.updatedOn = builder.getUpdatedOn();
		this.displayName = builder.getDisplayName();
		this.technicalName = builder.getTechnicalName();
		this.expiresOn = builder.getExpiresOn();
		this.description = builder.getDescription();
		this.inverted = builder.getInverted();
		this.customers = builder.getCustomers();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getTechnicalName() {
		return technicalName;
	}

	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
	}

	public LocalDateTime getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(LocalDateTime expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getInverted() {
		return inverted;
	}

	public void setInverted(Boolean inverted) {
		this.inverted = inverted;
	}

	public List<Long> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Long> customers) {
		this.customers = customers;
	}

	public Feature toModel() {
		Feature model = new Feature();
		model.setId(this.id);
		model.setUpdatedOn(this.updatedOn);
		model.setDisplayName(this.displayName);
		model.setTechnicalName(this.technicalName);
		model.setExpiresOn(this.expiresOn);
		model.setDescription(this.description);
		model.setInverted(this.inverted);
		model.setCustomers(this.customers);
		return model;
	}

}
