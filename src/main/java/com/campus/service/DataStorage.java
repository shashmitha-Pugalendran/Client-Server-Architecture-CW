package com.campus.service;

import com.campus.model.*;
import java.util.*;

public class DataStorage {
    public static Map<String, Room> rooms = new HashMap<>();
    public static Map<String, Sensor> sensors = new HashMap<>();
    public static Map<String, List<SensorReading>> readings = new HashMap<>();
}