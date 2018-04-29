/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrowHouseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author mejla
 */
public class GrowHouseDao {   
  
    public List<GrowHouse> getAllDays(){ 
      
      List<GrowHouse> GrowHouseList = new ArrayList<>();      
        
          for(int i=0;i<20;i++){
            GrowHouseList.add(new GrowHouse(0,GetRandom(0,37),GetRandom(50,95),GetRandomLumin(0,100),GetRandom(0,1),"Mondag"));  
          }        
          
          
          
      
      
      
      return GrowHouseList; 
   } 
   private float GetRandom(float high,float low){
        Random r = new Random();
        float Result = r.nextFloat() * (high - low) + low;
        
        return Result;
        }
   
   private int GetRandomLumin(int high,int low){
        Random r = new Random();
        int Result = r.nextInt() * (high - low) + low;
        
        return Result;
        }
}   

