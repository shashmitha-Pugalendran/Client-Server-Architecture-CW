package com.campus.mapper;

import com.campus.exception.LinkedResourceNotFoundException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

@Provider
public class LinkedResourceMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    public Response toResponse(LinkedResourceNotFoundException ex) {
        return Response.status(422)
                .entity("Invalid room reference")
                .build();
    }
}