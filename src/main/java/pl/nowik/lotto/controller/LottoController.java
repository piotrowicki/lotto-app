package pl.nowik.lotto.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.nowik.lotto.service.LottoStatisticsService;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class LottoController {

    @Inject
    LottoStatisticsService service;

    @GET
    @Path("/lotto/stats")
    public Response stats() {
        return Response.ok(service.calculateStats()).build();
    }
}