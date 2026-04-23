package com.campus.mapper;

import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    public Response toResponse(Throwable ex) {
        return Response.status(500)
                .entity("Internal Server Error")
                .build();
    }
}