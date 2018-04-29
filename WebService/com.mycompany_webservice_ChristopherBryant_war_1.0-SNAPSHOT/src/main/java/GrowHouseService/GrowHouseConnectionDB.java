/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrowHouseService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mejla
 */
public class GrowHouseConnectionDB {

    Connection con;
    Statement stmt;

    public GrowHouseConnectionDB() {
        connect();
    }

    /**
     *
     */

    public void connect() {

        try {
            try {
                //Properties p = new Properties();
                //C:\Users\mejla\Desktop\PoppeSynk\YH\IOT\Systeminegration\INL1\INL1_ChristopherBryant\INL1.properties
                //p.load(new FileInputStream("INL1.properties"));
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GrowHouseConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            }

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inl1db_christopherb", "root", "Jason2009");
            stmt = con.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<GrowHouse> getFromDB(String select, String From) {

        String query = "select ";
        query += select;
        query += " from ";
        query += From;

        List<GrowHouse> GrowHouseList = new ArrayList<>();

        try (
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                float temp = rs.getFloat("Temp");
                float humid = rs.getFloat("Humid");
                int lumin = rs.getInt("Lumin");
                float kwh = rs.getFloat("KWH");
                String day = rs.getString("Day");
                GrowHouseList.add(new GrowHouse(id, temp, humid, lumin, kwh, day));

            }

        } catch (SQLException e) {
        } finally {
            return GrowHouseList;
        }

    }

    public List<GrowHouse> getFromDBTempLast(String select, String From) {

        String query = "select ";
        query += select;
        query += " from ";
        query += From;

        List<GrowHouse> GrowHouseList = new ArrayList<>();

        try (
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                float temp = rs.getFloat("Temp");
                float humid = rs.getFloat("Humid");
                int lumin = rs.getInt("Lumin");
                float kwh = rs.getFloat("KWH");
                String day = rs.getString("Day");
                GrowHouseList.add(new GrowHouse(id, temp, humid, lumin, kwh, day));

            }

        } catch (SQLException e) {
        } finally {

            return GrowHouseList;
        }

    }

    public void Update(String type, int id, int val) {

        //UPDATE `inl1db_christopherb`.`inl1` SET `Humid`='80' WHERE `Id`='1';
        String query = "UPDATE `inl1db_christopherb`.`inl1` SET `";
        query += type;
        query += "`='";
        query += val;
        query += "' WHERE `Id`='";
        query += id;
        query += "'";

        try {

            String sql = query;
            con.prepareStatement(sql);
            int rowsUpdated = stmt.executeUpdate(sql);
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }

        } catch (SQLException e) {
        }

    }

    public int getlastid() {

        String query = "SELECT * FROM inl1 WHERE  Id=(SELECT MAX(Id) FROM inl1)";

        try (
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                float temp = rs.getFloat("Temp");
                float humid = rs.getFloat("Humid");
                int lumin = rs.getInt("Lumin");
                float kwh = rs.getFloat("KWH");
                String day = rs.getString("Day");
                return id;
            }

        } catch (SQLException e) {
        }
        return 0;

    }
         public String getMap(String select, String From, String Type){ 
         int counter=0;
         int avrage =0;
         int sum=0;
         String res="";
      Map<String,Float> tempList = new HashMap<>(); 
       String query = "select ";
        query += select;
        query += " from ";
        query += From;

        try (
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                float temp = rs.getFloat("Temp");
                float humid = rs.getFloat("Humid");
                int lumin = rs.getInt("Lumin");
                float kwh = rs.getFloat("KWH");
                String day = rs.getString("Day");
                switch (Type) {
                    case "Temp":
                        tempList.put(day, temp);continue;
                    case "Humid":
                        tempList.put(day, humid);continue;
                    case "Lumin":
                        tempList.put(day, (float)lumin);continue;
                    
                }
                
            }
            for (Map.Entry<String, Float> entry : tempList.entrySet()) {
            res += entry.getKey() + ":" + entry.getValue()+"\n";
            counter++;
            avrage += entry.getValue();
            }
            sum = avrage/counter;
            res +="\n Snitt "+sum;
            
            return res;

        } catch (SQLException e) {
        }    
     
      
      return res; 
   } 
     public String getKwhCost(String select, String From, int price){ 
         int counter=0;
         int avrage =0;
         int sum=0;
         String res="";
      Map<String,Float> tempList = new HashMap<>(); 
       String query = "select ";
        query += select;
        query += " from ";
        query += From;

        try (
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                float temp = rs.getFloat("Temp");
                float humid = rs.getFloat("Humid");
                int lumin = rs.getInt("Lumin");
                float kwh = rs.getFloat("KWH");
                String day = rs.getString("Day");                
                tempList.put(day, kwh);continue;
                    
                
                
            }
            for (Map.Entry<String, Float> entry : tempList.entrySet()) {
            res += entry.getKey() + ":" + entry.getValue()+"\n";
            counter++;
            avrage += entry.getValue();
            }
            sum = avrage/counter;
            res +="\nSnitt "+sum;
            res +="\nKostnad för veckan = "+sum*price;
            
            return res;

        } catch (SQLException e) {
        }    
     
      
      return res; 
   } 
     
   
       

    public String getString(String select, String From, String Type) {

        String query = "select ";
        query += select;
        query += " from ";
        query += From;

        try (
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                float temp = rs.getFloat("Temp");
                float humid = rs.getFloat("Humid");
                int lumin = rs.getInt("Lumin");
                float kwh = rs.getFloat("KWH");
                String day = rs.getString("Day");
                switch (Type) {
                    case "Temp":
                        return Float.toString(temp);
                    case "Humid":
                        return Float.toString(humid);
                    case "Lumin":
                        return Float.toString(lumin);
                    case "KWH":
                        return Float.toString(kwh);
                }

            }

        } catch (SQLException e) {
        }
        return "Något gick fel";
    }

    /*public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
        
        String result = getFromTable();
        con = connect();
        
        
     }*/
}
