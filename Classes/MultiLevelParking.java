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
        
        int c;
        while ((c = fr.read()) != -1) {
        	bufferTextFromFile += (char)c;
        } 
        
        bufferTextFromFile = bufferTextFromFile.replace("\r", "");
        String[] strs = bufferTextFromFile.split("\n");
        if(strs[0].contains("CountLeveles")) {
        	int count = Integer.parseInt(strs[0].split(":")[1]);
        	if(parkingStages != null) {
        		parkingStages.clear();
        	}
        	parkingStages = new ArrayList<Parking<ITransport, IRollers>>(count);
        } else {
        	return false;
        }
        
        int counter = -1;
        ITransport tractor = null;
        
        for(int i = 1; i < strs.length; ++i) {
        	if(strs[i].equals("Level")) {
        		counter++;
        		parkingStages.add(new Parking<ITransport, IRollers>(countPlaces, pictureWidth, pictureHeight));
        		continue;
        	}
        	if(strs[i] == null) {
        		continue;
        	}
        	
        	if (strs[i].split(":").length > 1) {
	        	if(strs[i].split(":")[1].equals("Tractor")) {
	        		tractor = new Tractor(strs[i].split(":")[2]);
	        	} 
	        	else if(strs[i].split(":")[1].equals("ExcavatorTractor")) {
	        		tractor = new ExcavatorTractor(strs[i].split(":")[2]);
	        	}
	        	
	        	parkingStages.get(counter).setTractor(Integer.parseInt(strs[i].split(":")[0]), tractor);
        	}
        }
        
        return true;
    }
    
    public boolean LoadLevel(String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        String bufferTextFromFile = "";
        int lvl;
        
        int c;
        while ((c = fr.read()) != -1) {
        	bufferTextFromFile += (char)c;
        } 
        
        bufferTextFromFile = bufferTextFromFile.replace("\r", "");
        String[] strs = bufferTextFromFile.split("\n");
        if (strs[0].contains("Level")) {
        	lvl = Integer.parseInt(strs[0].split(":")[1]);
        } else {
        	return false;
        }
        
        int counter = -1;
        ITransport tractor = null;
        
        for(int i = 1; i < strs.length; ++i) {
        	if(strs[i] == null) {
        		continue;
        	}
        	
        	if (strs[i].split(":").length > 1) {
	        	if(strs[i].split(":")[1].equals("Tractor")) {
	        		tractor = new Tractor(strs[i].split(":")[2]);
	        	} 
	        	else if(strs[i].split(":")[1].equals("ExcavatorTractor")) {
	        		tractor = new ExcavatorTractor(strs[i].split(":")[2]);
	        	}
	        	
	        	parkingStages.get(lvl).setTractor(Integer.parseInt(strs[i].split(":")[0]), tractor);
        	}
        }
        
        return true;
    }
    
    public ITransport get(int parkingIndex, int transportIndex) {
    	return parkingStages.get(parkingIndex).get(transportIndex);
    }
}
