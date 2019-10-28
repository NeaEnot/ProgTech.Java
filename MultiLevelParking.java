import java.util.ArrayList;

public class MultiLevelParking {
	ArrayList<Parking<ITransport, IRollers>> parkingStages;
    private final int countPlaces = 20;
    
    public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight)
    {
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
    
    public ITransport get(int parkingIndex, int transportIndex) {
    	return parkingStages.get(parkingIndex).get(transportIndex);
    }
}
