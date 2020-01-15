import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
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

    public void setPersonage(Personage personage)
    {
        this.personage = personage;
    }
    
    public Personage getPersonage()
    {
        return personage;
    }
    
    public void setSlot(boolean slot) {
        this.slot=slot;
    }
    
    public boolean getSlot() {
        return slot;
    }
    
    public void setItem(Item item)
    {
        this.item.add(item);
    }
    
    public ArrayList<Item> getRoomItem()
    {
        return item;
    }
    
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
