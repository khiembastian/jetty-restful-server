package org.jettyrest.api.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

@Service
@Path("/")
public class TimeTrackingResource {
    private Logger log = LoggerFactory.getLogger(TimeTrackingResource.class);
}

