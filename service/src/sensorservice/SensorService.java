/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorservice;

import SensorService.MySQLClient;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isa
 */
public class SensorService {
    
    public static void main(String[] args) throws SQLException{
        System.out.println("showing our table: ");
        MySQLClient sqlClient = new MySQLClient(); 
        List<Sensor> sensorL = new ArrayList<>();
        sensorL = sqlClient.getAllData(); 
        for (Sensor sens : sensorL){
            System.out.println("type: " + sens.getType()); 
            System.out.println("id: " + sens.getId()); 
            System.out.println("data: " + sens.getData()); 
            System.out.println("timestamp: " + sens.getTimeStamp());
        }
    }
}

