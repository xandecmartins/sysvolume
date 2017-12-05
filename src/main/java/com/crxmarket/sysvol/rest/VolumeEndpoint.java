package com.crxmarket.sysvol.rest;


import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.crxmarket.sysvol.service.VolumeService;

@Path("/rest")
public class VolumeEndpoint {

	@Inject
    private VolumeService volumeService;
	
	@GET
	@Path("/volume")
	@Produces("application/json")
	public Response calcVolume(@QueryParam("ids") String ids) {
		return Response.ok(volumeService.calcVolume(ids)).build();
	}
}