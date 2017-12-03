package com.crxmarket.sysvol.rest;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.crxmarket.sysvol.service.VolumeService;

@Path("/rest/volume")
@Produces("application/json")
public class VolumeEndpoint {

	@Inject
    VolumeService volumeService;
	
	@GET
	@Path("/")
	public Response calcVolume(@QueryParam("ids") List<Integer> ids) {
		return Response.ok(volumeService.calcVolume(ids)).build();
	}
}