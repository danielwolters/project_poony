import java.util.ArrayList;
import java.util.HashMap;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room /*beginruimte*/ zolder, plein, doogy_dinner, doogy_kamer, stal, reuzeplein, straat, huis1, huis2, huis3;

        // create the rooms
        plein =  new Room("op het plijn");
        doogy_dinner = new Room("in de ordinaire vreetschuur");
        doogy_kamer = new Room("in de kamer van Doogy");
        stal = new Room("in de stal met paarden");
        reuzeplein = new Room("op het plein met een reuzenrad");
        straat = new Room("in een straat met 2 huizen");
        zolder = new Room("zolder het ruikt alof er hier iets verborgen ligt");
        huis2 = new Room("in huis 2 met een erg mooi schilderij aan de muur");
        huis3 = new Room("in huis 3, een erg dubieus huis hmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm???");

        // initialise room exits and item
        //plein
        plein.setExits("oost", reuzeplein);
        plein.setExits("zuid", stal);
        plein.setExits("west", doogy_dinner);

        //doogy_dinner
        doogy_dinner.setExits("oost", plein);
        doogy_dinner.setExits("zuid", doogy_kamer);

        //doogy_kamer
        doogy_kamer.setExits("noord", doogy_dinner);

        //stal
        stal.setExits("noord", plein);
        stal.setItem(new Item("zadel"));
        stal.setSlot(true);

        //reuzeplein
        reuzeplein.setExits("noord", straat);
        reuzeplein.setExits("west", plein);

        //straat
        straat.setExits("noord", huis2);
        straat.setExits("oost", huis3);
        straat.setExits("zuid", reuzeplein);

        //zolder
        zolder.setExits("omlaag", huis2);
        zolder.setItem(new Item("sleutel"));

        //huis2
        huis2.setExits("zuid", straat);
        huis2.setExits("omhoog", zolder);

        //huis3
        huis3.setExits("west", straat);
        huis3.setItem(new Item("mes"));

        //beginruimte
        player.setCurrentRoom(plein);
    }

    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void terug()
    {
        if(player.getVorigeKamers().isEmpty()) {
            System.out.println("Je kunt niet verder terug");
        } else {
            player.setCurrentRoom(player.getVorigeKamers().get(player.getVorigeKamers().size()-1));
            player.getVorigeKamers().remove(player.getVorigeKamers().size()-1);
            printLocationInfo();
        }
    }

    private void geef()
    {
        System.out.println("Je hebt niks gegeven want je bezit niks #skeer");
    }

    private void pak()
    {
        if(player.getCurrentRoom().getRoomItem().isEmpty()) {
            System.out.println("Kill ben je blind er ligt hier toch niks");
        } else {
            player.addItem(player.getCurrentRoom().getRoomItem().get(0));
        }
    }

    private void wegdoen()
    {
        player.dropItem();
    }

    private void inhoud()
    {
        player.inhoudGoodieBagga();
    }

    private void gebruik()
    {
        if(player.validGoodieBagga() == false) {
            System.out.println("Je hebt niks om te gebruiken");
        } else {
            if(player.getCurrentRoom().getDescription().equals("op het plijn") && player.getGoodieBaggaString().equals("sleutel")) {
                Room nextRoom = player.getCurrentRoom().getExit("zuid");
                nextRoom.setSlot(false);
                System.out.println("De kamer is geopend");
                player.getGoodieBagga().remove(0);
            } else {
                System.out.println("niet hallp");
            }
        }
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    public void printLocationInfo()
    {

        //System.out.println("Je bent " + currentRoom.getDescription()); 
        System.out.print(player.getCurrentRoom().getLongDescription());
        System.out.println();     
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welkom in PonyPark Slagharen!");
        System.out.println("Probeer de uitgang te vinden om het spel te winnen");
        System.out.println("Typ 'help' als je hulp nodig hebt");
        System.out.println();
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Dit is geen command, probeer wat anders.");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("ga")) {
            player.vorigeKamersToevoegen();
            goRoom(command);
        }
        else if (commandWord.equals("stop")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("kijk")) {
            look();
        }
        else if (commandWord.equals("geef")) {
            geef();
        } 
        else if (commandWord.equals("terug")) {
            terug();
        }
        else if (commandWord.equals("pak")) {
            pak();
        }
        else if (commandWord.equals("wegdoen")) {
            wegdoen();
        }
        else if (commandWord.equals("inhoud")) {
            inhoud();
        } else if (commandWord.equals("gebruik")) {
            gebruik();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println();
        System.out.println("Dit is ponypark slagharen mijn broeder");
        System.out.println("Go ride or die");
        System.out.println();
        System.out.println("De woorden die je kunt gebruiken zijn:");
        System.out.println(parser.commandString());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Waarheen?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        /*Room nextRoom = null;
        if(direction.equals("north")) {
        nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")) {
        nextRoom = currentRoom.getExit("east");;
        }
        if(direction.equals("south")) {
        nextRoom = currentRoom.getExit("south");;
        }
        if(direction.equals("west")) {
        nextRoom = currentRoom.getExit("west");;
        }*/
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("Er is hier geen doorgang!");
        } else if(nextRoom.getSlot() == true) {
            System.out.println("Deze kamer zit opslot sorry pikkie");
            return;
        }
        else{
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
