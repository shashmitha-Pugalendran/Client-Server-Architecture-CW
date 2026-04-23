package com.campus.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/")
@Produces(MediaType.APPLICATION_JSON) 
public class DiscoveryResource {

    @GET
    public Map<String, Object> getInfo() {
        Map<String, Object> data = new HashMap<>();

        data.put("version", "v1");
        data.put("contact", "admin@campus.com");

        Map<String, String> links = new HashMap<>();
        links.put("rooms", "/api/v1/rooms");
        links.put("sensors", "/api/v1/sensors");

        data.put("resources", links);

        return data;
    }
}