package pl.nowik.lotto.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class LottoController {

    @GET
    @Path("/lotto/stats")
    @Produces(MediaType.APPLICATION_JSON)
    public String stats() {
        return "Hello RESTEasy";
    }
}