/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Websocket;

/**
 *
 * @author mejla
 */
import Model.Sensor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

@ApplicationScoped
public class SensorHandler {
    //Varje sensor har sin egen "session"
    private int sensorId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Sensor> sensors = new HashSet<>();
    
     public void addSession(Session session) {
        sessions.add(session);
        for(Sensor sensor : sensors){
            JsonObject addMessage = createAddMessage(sensor);
            sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public List<Sensor> getSensor() {
        return new ArrayList<>(sensors);
    }

    public void addSensor(Sensor sensor) {
        
        sensor.setId(sensorId);
        sensors.add(sensor);       
        sensorId++;        
        JsonObject addMessage = createAddMessage(sensor);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeSensor(int id) {
          Sensor sensor = getSensorById(id);
        if (sensor != null) {
            sensors.remove(sensor);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public void toggleSensor(int id) {
        JsonProvider provider = JsonProvider.provider();
        Sensor sensor = getSensorById(id);
        if (sensor != null) {
            if ("On".equals(sensor.getStatus())) {
                sensor.setStatus("Off");
            } else {
                sensor.setStatus("On");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggle")
                    .add("id", sensor.getId())
                    .add("status", sensor.getStatus())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

    private Sensor getSensorById(int id) {
        for (Sensor sensor : sensors) {
            if (sensor.getId() == id) {
                return sensor;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Sensor sensor) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", sensor.getId())
                .add("name", sensor.getName())
                .add("type", sensor.getType())
                .add("status", sensor.getStatus())
                .add("description", sensor.getDescription())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
         for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
         try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(SensorWedSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
