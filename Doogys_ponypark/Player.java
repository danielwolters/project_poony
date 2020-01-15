
import java.util.ArrayList;
/**
 * class Player - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Room> vorigeKamers;
    private ArrayList<Item> goodieBagga;
    
    public Player ()
    {
        vorigeKamers = new ArrayList<Room>();
        goodieBagga = new ArrayList<Item>();
    }
    
    public void addItem(Item item)
    {
        if(goodieBagga.size() >= 1)
        {
            System.out.println("U kunt niet meer dan 1 item in uw bagga stoppen.");
        } else {
            //if (currentRoom.getRoomItem().isEmpty()) {
               // System.out.println("Kill ben jij blind er ligt hier toch niks");
            //} else {
                currentRoom.removeItem();
                goodieBagga.add(item);
                System.out.println(item.getOmschrijving() + " opgepakt!");
            //}
        }
    }
    
    public String getGoodieBaggaString()
    {
        return goodieBagga.get(0).getOmschrijving();
    }
    
    public ArrayList<Item> getGoodieBagga()
    {
        return goodieBagga;
    }
    
    public boolean validGoodieBagga()
    {
        if(goodieBagga.isEmpty() == true) {
            return false;
        } else {
            return true;
        }
    }
    
    public void inhoudGoodieBagga()
    {
        if(goodieBagga.isEmpty()) {
            System.out.println("Er zit niks in je GoodieBagga");
        } else {
            System.out.println("Dit zit er in je GoodieBagga: " + goodieBagga.get(0).getOmschrijving());
        }
    }
    
    public void dropItem()
    {
        if(goodieBagga.size() >= 1) {
            if(currentRoom.getRoomItem().isEmpty()) {
                System.out.println(goodieBagga.get(0).getOmschrijving() + " weggedaan!");
                currentRoom.setItem(goodieBagga.get(0));
                goodieBagga.remove(0);
            } else {
                Item tijdelijkItem = goodieBagga.get(0);
                System.out.println(goodieBagga.get(0).getOmschrijving() + " weggedaan!");
                goodieBagga.remove(0);
                addItem(currentRoom.getRoomItem().get(0));
                //currentRoom.getRoomItem().remove(0);
                currentRoom.getRoomItem().add(tijdelijkItem);
            }
        } else {
            System.out.println("Je hebt niks om weg te gooien");
        }
    }
    
    public void vorigeKamersToevoegen()
    {
        vorigeKamers.add(currentRoom);
    }
    
    public ArrayList<Room> getVorigeKamers()
    {
        return vorigeKamers;
    }
    
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
}
