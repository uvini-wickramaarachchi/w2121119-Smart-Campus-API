package com.smartcampus.filter;
import java.util.*;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger logger = Logger.getLogger("API");

    public void filter(ContainerRequestContext req) {
        logger.info(req.getMethod() + " " + req.getUriInfo().getPath());
    }

    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        logger.info("Response: " + res.getStatus());
    }
}
