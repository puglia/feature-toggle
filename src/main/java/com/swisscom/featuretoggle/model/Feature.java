package com.swisscom.featuretoggle.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.swisscom.featuretoggle.util.FeatureBuilder;

@Entity(name = "Feature")
@Table(name = "feature")
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false, columnDefinition = "SERIAL")
	protected Long id;

	@UpdateTimestamp
	@Column(name = "updated_on", nullable = false)
	protected LocalDateTime updatedOn;

	@Column(name = "display_name", nullable = false)
	private String displayName;

	@Column(name = "technical_name", nullable = false)
	private String technicalName;

	@Column(name = "expires_on", nullable = true)
	protected LocalDateTime expiresOn;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "inverted", nullable = false)
	private Boolean inverted;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "customer_feature")
	@Column(name = "customer_id")
	private List<Long> customers;

	public Feature() {
		super();
	}

	public Feature(FeatureBuilder builder) {
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

}
