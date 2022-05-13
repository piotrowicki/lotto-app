package pl.nowik.lotto.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.nowik.lotto.service.LottoService;
import pl.nowik.lotto.service.LottoStatisticsService;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class LottoController {

    @Inject
    LottoService service;

    @Inject
    LottoStatisticsService statsService;

    @GET
    @Path("/lotto/stats")
    public Response stats() {
        return Response.ok(statsService.calculateStats()).build();
    }

    @GET
    @Path("/lotto/stats/last")
    public Response getLastDraw() {
        return Response.ok(service.getLastByDrawDate()).build();
    }

    @GET
    @Path("/lotto/stats/most-common")
    public Response getMostCommon() {
        return Response.ok(statsService.getMostCommon()).build();
    }

    @GET
    @Path("/lotto/stats/least-common")
    public Response getLeastCommon() {
        return Response.ok(statsService.getLeastCommon()).build();
    }
}