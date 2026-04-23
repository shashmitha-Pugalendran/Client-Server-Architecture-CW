package com.campus.resource;

import com.campus.model.Sensor;
import com.campus.service.DataStorage;
import com.campus.exception.LinkedResourceNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    @POST
    public Response createSensor(Sensor sensor) {

        if (!DataStorage.rooms.containsKey(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException();
        }

        DataStorage.sensors.put(sensor.getId(), sensor);
        DataStorage.rooms.get(sensor.getRoomId()).getSensorIds().add(sensor.getId());

        return Response.status(201).entity(sensor).build();
    }

    @GET
    public List<Sensor> getSensors(@QueryParam("type") String type) {
        return DataStorage.sensors.values().stream()
                .filter(s -> type == null || s.getType().equalsIgnoreCase(type))
                .toList();
    }

    @Path("/{id}/readings")
    public SensorReadingResource getReadings() {
        return new SensorReadingResource();
    }
}