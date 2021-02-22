package com.swisscom.featuretoggle.api;

import static com.swisscom.featuretoggle.util.ResponseFactory.badRequest;
import static com.swisscom.featuretoggle.util.ResponseFactory.notFound;
import static com.swisscom.featuretoggle.util.ResponseFactory.ok;
import static com.swisscom.featuretoggle.util.ResponseFactory.serverError;

import java.util.NoSuchElementException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	public Response find(@PathParam(value = "id") Long id) {
		try {
			return ok(featureService.find(id));
		} catch (NoSuchElementException ex) {
			return notFound(ex.getMessage());
		} catch (Exception ex) {
			return serverError(ex.getMessage());
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(FeatureVO feature) {
		try {
			return ok(featureService.save(feature));
		} catch (IllegalArgumentException ex) {
			return badRequest(ex.getMessage());
		} catch (Exception ex) {
			return serverError(ex.getMessage());
		}
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll(@PathParam(value = "customer_id") Long customerId) {
		try {
			return ok(featureService.list());
		} catch (Exception ex) {
			return serverError(ex.getMessage());
		}
	}

	@GET
	@Path("/list/{customer_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@PathParam(value = "customer_id") Long customerId) {
		try {
			return ok(featureService.list(customerId));
		} catch (Exception ex) {
			return serverError(ex.getMessage());
		}
	}

}
