package org.jettyrest.service;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/simple")
public class SimpleResource {
    @GET
    public String get() {
        return "OK";
    }
}
