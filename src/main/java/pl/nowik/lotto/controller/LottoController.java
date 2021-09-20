package pl.nowik.lotto.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.nowik.lotto.service.LottoService;

@Path("/api")
public class LottoController {

    @Inject
    LottoService service;

    @GET
    @Path("/lotto/stats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stats() {
        return Response.ok(service.calculateStats()).build();
    }
}