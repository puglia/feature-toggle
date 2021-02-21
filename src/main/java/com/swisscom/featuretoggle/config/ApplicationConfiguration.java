package com.swisscom.featuretoggle.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.swisscom.featuretoggle.api.FeatureServiceREST;
import com.swisscom.featuretoggle.service.FeatureService;

@Configuration
public class ApplicationConfiguration extends ResourceConfig {

	public ApplicationConfiguration() {
		register(FeatureServiceREST.class);
		register(FeatureService.class);
	}
}