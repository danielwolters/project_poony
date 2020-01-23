import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Pony park Slagharen" application. 
 *   
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west, omhoog, omlaag.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 'Room' Also stores if there is an Item in the room or wheter the Room is locked or not 
 * Also set the personges currently in the room
 * 
 * @author  Thomas de Bruin and Daniël Wolters
 * @version 23-01-2020
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> item;
    private Personage personage;
    private boolean slot;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        item = new ArrayList<Item>();
        exits = new HashMap<>();
        personage = null;
        slot = false;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Sets a Personage in the room
     * @param the Personage the should be added to the Room
     */
    public void setPersonage(Personage personage)
    {
        this.personage = personage;
    }
    
    /**
     * Gets the Personage the is currently in the room
     * @return return the Personage the is currently in the room
     */
    public Personage getPersonage()
    {
        return personage;
    }
    
    /**
     * Sets the slot on a room
     * @param 'true' if the Room should be locked 'false' otherwise   
     */
    public void setSlot(boolean slot) {
        this.slot=slot;
    }
    
    /**
     * Gets the slot of the current Room
     * @return return the slot of the current Room
     */
    public boolean getSlot() {
        return slot;
    }
    
    /**
     * Set an Item to the Room
     * @param The Item that should be added to the Room
     */
    public void setItem(Item item)
    {
        this.item.add(item);
    }
    
    /**
     * Gets the items that are currently in the room
     * @return an ArrayList<Item> with the Item that are in the Room
     */
    public ArrayList<Item> getRoomItem()
    {
        return item;
    }
    
    /**
     * Removes the Item that is in the Room
     */
    public void removeItem()
    {
        item.remove(0);
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return returns a Room based on the exit String given
     * @param a String with the direction
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Retouneer een string met daarin de uitgangen van de ruimte,
     * bijvoorbeeld "Exits: north west".
     * @return Een omschrijving van de aanwezige uitgangen in de
     * ruimte.
     */
    public String getExitString()
    {
        String returnString = "Doorgangen:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + "♞" + exit + "♞";
        }
        return returnString;
    }
    
    /**
     * Puts all the information of the room into a string
     * like the Room omschrijving, exits and items
     * @return the String containing the Room information
     */
    public String getLongDescription()
    {
        String kamerOmschrijving = "";
        kamerOmschrijving += "Je bent " + description + ".\n";
        if(!item.isEmpty()) {
            kamerOmschrijving += "Er ligt hier eeeeen (∩｀-´)⊃━━☆ﾟ.*･｡ﾟ";
            int index = 0;
            while (index < item.size()) {
                kamerOmschrijving += "ﾟ.*" + item.get(index).getOmschrijving() + "･｡ﾟ";
                index++;
            }
            kamerOmschrijving += "\n";
        }
        kamerOmschrijving += getExitString(); 
        return kamerOmschrijving;
    }
}
