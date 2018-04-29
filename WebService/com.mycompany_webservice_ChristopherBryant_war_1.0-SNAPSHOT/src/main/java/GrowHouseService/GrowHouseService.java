/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrowHouseService;


import java.util.*;
import java.util.List; 
import javax.ws.rs.PathParam;
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;  
@Path("/GrowHouseService") 

/**
 *
 * @author mejla
 */
public class GrowHouseService {
  

   GrowHouseConnectionDB grow = new GrowHouseConnectionDB();
   List<GrowHouse> statList = grow.getFromDB("*","inl1");
   
   @GET 
   @Path("/Days") 
   @Produces(MediaType.APPLICATION_XML)   
   public List<GrowHouse> getDays(){ 
      return grow.getFromDB("*","inl1");
   }
     
   @GET 
   @Path("/Day/tempNow") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getTempNow(){    
       
      return grow.getString("*","inl1 WHERE  Id=(SELECT MAX(Id) FROM inl1)","Temp");
   } 
   
   @GET 
   @Path("/Day/tempAll") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getTempAll(){    
       
      return grow.getMap("*","inl1","Temp");
   } 
   
   
   @GET 
   @Path("/Day/humidNow") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getHumidNow(){    
       
      return grow.getString("*","inl1 WHERE  Id=(SELECT MAX(Id) FROM inl1)","Humid");
   } 
   
   @GET 
   @Path("/Day/humidAll") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getHumidAll(){    
       
      return grow.getMap("*","inl1","Humid");
   } 
   
   @GET 
   @Path("/Day/luminNow") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getLuminNow(){    
       
      return grow.getString("*","inl1 WHERE  Id=(SELECT MAX(Id) FROM inl1)","Lumin");
   } 
   
   @GET 
   @Path("/Day/luminAll") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getluminAll(){    
       
      return grow.getMap("*","inl1","Lumin");
   } 
   
   @GET 
   @Path("/Day/kwhNow") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getKwhNow(){    
       
      return grow.getString("*","inl1 WHERE  Id=(SELECT MAX(Id) FROM inl1)","KWH");
   } 
   
   @POST 
   @Path("/Day/kwhAll/{value}") 
   @Produces(MediaType.TEXT_PLAIN) 
   public String getKwhCost(@PathParam("value") int val){        
       String res = grow.getKwhCost("*", "inl1", val);   
       
      return res;
   } 
   
   
   

   @POST 
   @Path("/Day/Humid/{value}") 
   @Produces(MediaType.APPLICATION_XML) 
   public GrowHouse upDateHumid(@PathParam("value") int val){ 
       int id = grow.getlastid();
       grow.Update("Humid",id,val);
       GrowHouse res = new GrowHouse();
       for (GrowHouse g : grow.getFromDB("*","inl1")){
           if ( g.getId()== id){
               res = g;
           }
       }
      return res;
   } 
   
   @POST 
   @Path("/Day/Temp/{value}") 
   @Produces(MediaType.APPLICATION_XML) 
   public GrowHouse upDateTemp(@PathParam("value") int val){ 
       int id = grow.getlastid();
       grow.Update("Temp",id,val);
       GrowHouse res = new GrowHouse();
       for (GrowHouse g : grow.getFromDB("*","inl1")){
           if ( g.getId()== id){
               res = g;
           }
       }
      return res;
   } 
   
   @POST 
   @Path("/Day/Lumin/{value}") 
   @Produces(MediaType.APPLICATION_XML) 
   public GrowHouse upDateLumi(@PathParam("value") int val){ 
       int id = grow.getlastid();
       grow.Update("Lumi",id,val);
       GrowHouse res = new GrowHouse();
       for (GrowHouse g : grow.getFromDB("*","inl1")){
           if ( g.getId()== id){
               res = g;
           }
       }
      return res;
   } 
   
   @GET 
   @Path("/DayJSON/{id}") 
   @Produces(MediaType.APPLICATION_JSON) 
   public GrowHouse getDayByIdJSON(@PathParam("id") int id){ 
       GrowHouse res = new GrowHouse();
       for (GrowHouse g : grow.getFromDB("*","inl1")){
           if ( g.getId()== id){
               res = g;
           }
       }
      return res;
   } 
   
      
  
}


