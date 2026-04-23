package com.campus.resource;

import com.campus.model.Sensor;
import com.campus.model.SensorReading;
import com.campus.service.DataStorage;
import com.campus.exception.SensorUnavailableException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    @GET
    public List<SensorReading> getReadings(@PathParam("id") String sensorId) {
        return DataStorage.readings.getOrDefault(sensorId, new ArrayList<>());
    }

    @POST
    public Response addReading(@PathParam("id") String sensorId, SensorReading reading) {

        Sensor sensor = DataStorage.sensors.get(sensorId);

        // Check sensor exists
        if (sensor == null) {
            return Response.status(404)
                    .entity("Sensor not found")
                    .build();
        }

        // Check maintenance
        if ("MAINTENANCE".equals(sensor.getStatus())) {
            throw new SensorUnavailableException();
        }

        // Add reading
        DataStorage.readings
                .computeIfAbsent(sensorId, k -> new ArrayList<>())
                .add(reading);

        // Update sensor value
        sensor.setCurrentValue(reading.getValue());

        return Response.status(201)
                .entity(reading)
                .build();
    }
}