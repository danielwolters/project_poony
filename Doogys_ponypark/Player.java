
import java.util.ArrayList;
/**
 * class Player - Creates a player and keeps track of the Room the Player is currently in
 * Also keeps track of all the Rooms the player was previously in
 * Also keeps track of all the Items the player gathered
 * 
 *
 * @author Thomas de Bruin and Daniël Wolters
 * @version 23-01-2020
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Room> vorigeKamers;
    private ArrayList<Item> goodieBagga;
    
    /**
     * Creates a new Player
     * and creates a new ArrayList that can be filled with Rooms
     * and creates a new Arraylist that can be filled with Items
     */
    public Player ()
    {
        vorigeKamers = new ArrayList<Room>();
        goodieBagga = new ArrayList<Item>();
    }
    
    /**
     * Adds an Item to the ArrayList<Item>
     * If there already is an Item in the ArrayList<Item>
     * prints: 'U kunt niet meer dan 1 item in uw bagga stoppen'
     * @param the Item the should be added
     */
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
    
    /**
     * Gets a String with the omschrijving of the Item currently in the ArrayList<Item>
     * @return return a String with the omschrijving of the Item Currently in ArrayList<Item>
     */
    public String getGoodieBaggaString()
    {
        return goodieBagga.get(0).getOmschrijving();
    }
    
    /**
     * Gets the ArrayList<Item>
     * @return returns an ArrayList<Item> with Items
     */
    public ArrayList<Item> getGoodieBagga()
    {
        return goodieBagga;
    }
    
    /**
     * Check if the ArrayList<Item> is empty or not
     * @return return false if the ArrayList<Item> is meaning its not valid else return true meaning there is a
     * Item in the ArrayList<Item> thus being valid
     */
    public boolean validGoodieBagga()
    {
        if(goodieBagga.isEmpty() == true) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Checks if there is something inside goodieBagga
     * prints 'er zit niks in je goodieBagga ' if the ArrayList is empty
     * prints 'Dit zit er in je goodieBagga' + the item omschrijving from the Item in the goodieBagga
     */
    public void inhoudGoodieBagga()
    {
        if(goodieBagga.isEmpty()) {
            System.out.println("Er zit niks in je GoodieBagga");
        } else {
            System.out.println("Dit zit er in je GoodieBagga: " + goodieBagga.get(0).getOmschrijving());
        }
    }
    
    /**
     * Drops the Item currently in goodieBagga
     * if the goodieBagga contains one or more Items
     * check if the currentRoom already has an Item
     * if so switch the Item of the CurrentRoom and the Item currently in GoodieBagga
     * and prints and omschrijving of what you picked up and dropped
     * if the currentRoom contains no Items drop the Item and prints the omschrijving of the Item dropped
     * if you have no Item print'Je hebt niks om weg te gooien ' 
     */
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
    
    /**
     * Add the currentRoom to the ArrayList<Room> vorigeKamers
     */
    public void vorigeKamersToevoegen()
    {
        vorigeKamers.add(currentRoom);
    }
    
    /**
     * gets the ArrayList<Room> vorigeKamers containing the rooms the player was previously in
     * @return the ArrayList<Room> containing the rooms the player was previously in
     */
    public ArrayList<Room> getVorigeKamers()
    {
        return vorigeKamers;
    }
    
    /**
     * Set the currentRoom of the Player to the room that is given
     * @param the Room to which the currentRoom should be changed
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * Gets the Room the player is currently in
     * @return return currentRoom containing the Room the player is currently in
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
}
