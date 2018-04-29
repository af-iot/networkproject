/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//klass för att koppla upp oss mot mySQL 
public class MySQLClient {
    Connection con; 
    Statement stmt;
    
    public MySQLClient(){
        connectToDB();
    }
    
    //connect to database
    public void connectToDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://groupproject.cvkvpqimwsoj.eu-central-1.rds.amazonaws.com:3306/sensordata", "masteruser", "Jason2009");
            stmt = con.createStatement();
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    public List<Sensor> getAllData() throws SQLException{
        List<Sensor> sensorList = new ArrayList<>();
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery("SELECT * FROM data");
        
        while(rs.next()){
            String time = rs.getString("time");
            
            String type = rs.getString("type");
            Double data = rs.getDouble("data");
            
            String id = rs.getString("id"); 
            sensorList.add(new Sensor(data, type,time, id));
        } 
        return sensorList;
    }
    
}
