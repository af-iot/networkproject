/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorservice;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Isa
 */
@XmlRootElement(name = "sensor")

//klass för ett simpelt sensor-objekt
public class Sensor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private double data; //tempraturdata
    private String type; //typ av sensor
    private String timeStamp; //tidsstämpel
    private String id; //unikt id
    
    Sensor(double argData, String argType, String argTimeStamp, String argId){
        data = argData; 
        type = argType; 
        timeStamp = argTimeStamp; 
        id = argId; 
    }

    /**
     * @return the data
     */
    public double getData() {
        return data;
    }

    /**
     * @param temp the temp to set
     */
    public void setData(double data) {
        this.data = data;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
}
