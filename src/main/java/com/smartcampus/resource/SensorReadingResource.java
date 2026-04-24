import java.util.*;

public class SensorReadingResource {

    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    public List<SensorReading> getAll() {
        return DataStore.readings.getOrDefault(sensorId, new ArrayList<>());
    }

    @POST
    public SensorReading add(SensorReading reading) {

        Sensor sensor = DataStore.sensors.get(sensorId);

        if (sensor.getStatus().equals("MAINTENANCE")) {
            throw new SensorUnavailableException("Sensor unavailable");
        }

        DataStore.readings
            .computeIfAbsent(sensorId, k -> new ArrayList<>())
            .add(reading);

        sensor.setCurrentValue(reading.getValue());

        return reading;
    }
}
