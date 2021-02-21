package com.swisscom.featuretoggle.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RestController;

import com.swisscom.featuretoggle.model.FeatureVO;
import com.swisscom.featuretoggle.service.FeatureService;

@Path("/feature")
@RestController
public class FeatureServiceREST {
	
	@Inject
	FeatureService featureService;
	
	public FeatureServiceREST() {
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam(value = "id") Long id) throws Exception {
		try {
			return Response.ok().entity(featureService.find(id)).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(FeatureVO feature) throws Exception {
		try {
			return Response.ok().entity(featureService.save(feature)).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@QueryParam(value = "customer_id") Long customerId) throws Exception {
		try {
			return Response.ok().entity(featureService.list()).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

}
