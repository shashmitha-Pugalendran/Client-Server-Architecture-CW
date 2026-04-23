package com.campus.mapper;

import com.campus.exception.SensorUnavailableException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

@Provider
public class SensorUnavailableMapper implements ExceptionMapper<SensorUnavailableException> {
    public Response toResponse(SensorUnavailableException ex) {
        return Response.status(403)
                .entity("Sensor under maintenance")
                .build();
    }
}