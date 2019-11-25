package Classes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Interfaces.IRollers;
import Interfaces.ITransport;

public class MultiLevelParking {
	ArrayList<Parking<ITransport, IRollers>> parkingStages;
    private final int countPlaces = 20;
    
    int pictureWidth;
    int pictureHeight;
    
    public MultiLevelParking(int countStages, int pw, int ph)
    {
    	pictureWidth = pw;
    	pictureHeight = ph;
    	
        parkingStages = new ArrayList<Parking<ITransport, IRollers>>();
        for (int i = 0; i < countStages; ++i)
        {
            parkingStages.add(new Parking<ITransport, IRollers>(countPlaces, pictureWidth, pictureHeight));
        }
    }

    public Parking<ITransport, IRollers> level(int index)
    {
        if (index > -1 && index < parkingStages.size())
        {
            return parkingStages.get(index);
        }
        return null;
    }
    
    public boolean SaveData(String filename) throws IOException
    {
    	FileWriter fw = new FileWriter(filename);
        WriteToFile("CountLeveles:"+ parkingStages.size() + "\n", fw);
        for (Parking<ITransport, IRollers> level : parkingStages)
        {
            WriteToFile("Level" + "\n", fw);
            for (int i = 0; i < countPlaces; i++)
            {
                ITransport tractor = level.get(i);
                if (tractor != null)
                {
                    if (tractor.getClass().getName() == "Classes.Tractor")
                    {
                        WriteToFile(i + ":Tractor:", fw);
                    }
                    if (tractor.getClass().getName() == "Classes.ExcavatorTractor")
                    {
                        WriteToFile(i + ":ExcavatorTractor:", fw);
                    }
                    WriteToFile(tractor.ToString() + "\n", fw);
                }
            }
        }
        
        fw.flush();
        
        return true;
    }
    
    public boolean SaveLevel(String filename, int lvl) throws IOException
    {
    	FileWriter fw = new FileWriter(filename);
        WriteToFile("Level:"+ lvl + "\n", fw);
        Parking<ITransport, IRollers> level = parkingStages.get(lvl);
        for (int i = 0; i < countPlaces; i++)
        {
            ITransport tractor = level.get(i);
            if (tractor != null)
            {
                if (tractor.getClass().getName() == "Classes.Tractor")
                {
                    WriteToFile(i + ":Tractor:", fw);
                }
                if (tractor.getClass().getName() == "Classes.ExcavatorTractor")
                {
                    WriteToFile(i + ":ExcavatorTractor:", fw);
                }
                WriteToFile(tractor.ToString() + "\n", fw);
            }
        }
        
        fw.flush();
        
        return true;
    }

    private void WriteToFile(String text, FileWriter fw)
    {
        try {
			fw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public boolean LoadData(String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        String bufferTextFromFile = "";
        int counter = -1;
        
        int c;
        while ((char)(c = fr.read()) != '\n') {
        	bufferTextFromFile += (char)c;
        }
        
        if(bufferTextFromFile.contains("CountLeveles")) {
        	int count = Integer.parseInt(bufferTextFromFile.split(":")[1]);
        	if(parkingStages != null) {
        		parkingStages.clear();
        	}
        	parkingStages = new ArrayList<Parking<ITransport, IRollers>>(count);
            bufferTextFromFile = "";
        } else {
        	return false;
        }
        
        while ((c = fr.read()) != -1) {
        	if ((char)c == '\n') {        		
        		ITransport tractor = null;
                
                if(bufferTextFromFile.equals("Level")) {
                	counter++;
                	parkingStages.add(new Parking<ITransport, IRollers>(countPlaces, pictureWidth, pictureHeight));
                    bufferTextFromFile = "";
                	continue;
                }
                
                if (bufferTextFromFile.split(":").length > 1) {
        	       	if(bufferTextFromFile.split(":")[1].equals("Tractor")) {
        	       		tractor = new Tractor(bufferTextFromFile.split(":")[2]);
        	       	} 
        	       	else if(bufferTextFromFile.split(":")[1].equals("ExcavatorTractor")) {
        	       		tractor = new ExcavatorTractor(bufferTextFromFile.split(":")[2]);
        	       	}
        	       	
        	       	parkingStages.get(counter).setTractor(Integer.parseInt(bufferTextFromFile.split(":")[0]), tractor);
                }
                
                bufferTextFromFile = "";
            } else {
            	bufferTextFromFile += (char)c;
            }
        }
        
        return true;
    }
    
    public boolean LoadLevel(String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        String bufferTextFromFile = "";
        int lvl = 0;
        
        int c;
        while ((char)(c = fr.read()) != '\n') {
        	bufferTextFromFile += (char)c;
        }
        
        if (bufferTextFromFile.contains("Level")) {
           	lvl = Integer.parseInt(bufferTextFromFile.split(":")[1]);
           	bufferTextFromFile = "";
        } else {
        	return false;
        }
        
        if (parkingStages.size() < lvl) {
        	return false;
        }

    	parkingStages.set(lvl, new Parking<ITransport, IRollers>(countPlaces, pictureWidth, pictureHeight));
        
        while ((c = fr.read()) != -1) {
        	if ((char)c == '\n') {                
                ITransport tractor = null;
                
                if(bufferTextFromFile == null) {
                	continue;
                }
                
                if (bufferTextFromFile.split(":").length > 2) {
        	       	if(bufferTextFromFile.split(":")[1].equals("Tractor")) {
        	       		tractor = new Tractor(bufferTextFromFile.split(":")[2]);
        	       	} 
        	       	else if(bufferTextFromFile.split(":")[1].equals("ExcavatorTractor")) {
        	       		tractor = new ExcavatorTractor(bufferTextFromFile.split(":")[2]);
        	       	}
        	       	
        	       	parkingStages.get(lvl).setTractor(Integer.parseInt(bufferTextFromFile.split(":")[0]), tractor);
                }
                
                bufferTextFromFile = "";
            } else {
            	bufferTextFromFile += (char)c;
            }
        }
        
        return true;
    }
    
    public ITransport get(int parkingIndex, int transportIndex) {
    	return parkingStages.get(parkingIndex).get(transportIndex);
    }
}
