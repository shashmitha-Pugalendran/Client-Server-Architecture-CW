package com.campus.mapper;

import com.campus.exception.RoomNotEmptyException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

@Provider
public class RoomNotEmptyMapper implements ExceptionMapper<RoomNotEmptyException> {
    public Response toResponse(RoomNotEmptyException ex) {
        return Response.status(409)
                .entity("Room has active sensors")
                .build();
    }
}
