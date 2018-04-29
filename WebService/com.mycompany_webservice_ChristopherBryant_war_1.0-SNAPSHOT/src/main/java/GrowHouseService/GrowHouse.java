/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrowHouseService;

/**
 *
 * @author Christopher Bryant
 */
import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "GrowHouse") 

public class GrowHouse implements Serializable {
        private static long serialVersionUID = 1L;
        private int id;     
        private float temp;
        private float humid;
        private float lumin;
        private float kWh;
        private String day;
        
        
        public GrowHouse(){}
        
        public GrowHouse(int id,float temp,float humid,int lumin,float kWh,String day){
            this.id = id;
            this.temp =temp;
            this.humid = humid;
            this.lumin =lumin;
            this.kWh = kWh;
            this.day = day;
            
        }
        

    /**
     * @return the temp
     */
    public float getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    @XmlElement
    public void setTemp(float temp) {
        this.temp = temp;
    }

    /**
     * @return the humid
     */
    public float getHumid() {
        return humid;
    }

    /**
     * @param humid the humid to set
     */
    @XmlElement
    public void setHumid(float humid) {
        this.humid = humid;
    }

    /**
     * @return the lumin
     */
    public float getLumin() {
        return lumin;
    }

    /**
     * @param lumin the lumin to set
     */
    @XmlElement
    public void setLumin(float lumin) {
        this.lumin = lumin;
    }

    /**
     * @return the kWh
     */
    public float getkWh() {
        return kWh;
    }

    /**
     * @param kWh the kWh to set
     */
    @XmlElement
    public void setkWh(float kWh) {
        this.kWh = kWh;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    @XmlElement
    public void setDay(String day) {
        this.day = day;
    }
        
}
    

