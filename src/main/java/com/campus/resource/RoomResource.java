package com.campus.resource;

import com.campus.model.Room;
import com.campus.service.DataStorage;
import com.campus.exception.RoomNotEmptyException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    @GET
    public Collection<Room> getRooms() {
        return DataStorage.rooms.values();
    }

    @POST
    public Response createRoom(Room room) {
        DataStorage.rooms.put(room.getId(), room);
        return Response.status(201).entity(room).build();
    }

    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id) {
        return DataStorage.rooms.get(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {

        Room room = DataStorage.rooms.get(id);

        if (room.getSensorIds().size() > 0) {
            throw new RoomNotEmptyException();
        }

        DataStorage.rooms.remove(id);
        return Response.noContent().build();
    }
}