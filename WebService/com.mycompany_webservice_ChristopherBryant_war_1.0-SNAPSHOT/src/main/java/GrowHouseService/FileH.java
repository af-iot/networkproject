package GrowHouseService;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;







public class FileH {

	public static void main(String[] args) {
		
                GrowHouseDao grow = new GrowHouseDao();
                List<GrowHouse> growList = grow.getAllDays();	
                saveSer(growList);
                saveJson(growList);
	}		

       
        
        public static void saveSer(List<GrowHouse> growList){
            //public GrowHouse(int id,int timeStamp,float temp,float humid,int lumin,float kWh
            try {
			FileOutputStream fos = new FileOutputStream("INL1.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Skriv object till fil
                        oos.writeObject("\n");
                         for (GrowHouse g: growList){
                            oos.writeObject("id: "+g.getId()+ " Time stamp: "+g.getDay() +" Temp: "+g.getTemp()+" Humid: "+g.getHumid()+" Lumin: "+g.getLumin()+" kWh: "+g.getkWh()+"\n");
                        }			
			System.out.println("Done");
			// Stäng
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
            
        }
        
         public static void saveJson(List<GrowHouse> growList){
             
                Gson gson = new Gson();
       
               try (FileWriter writer = new FileWriter("INL1.json")) {

                     gson.toJson(growList, writer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            
        }
         
             
        

}
