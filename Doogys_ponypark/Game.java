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
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
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
        //beginruimte = new Room("Hier begin je");
        plein = new Room("op het plijn");
        doogy_dinner = new Room("in de ordinaire vreetschuur");
        doogy_kamer = new Room("in de kamer van Doogy");
        stal = new Room("in de stal met paarden");
        reuzeplein = new Room("op het plein met een reuzenrad");
        straat = new Room("in een straat met 2 huizen");
        zolder = new Room("zolder het ruikt alof er hier iets verborgen ligt");
        huis2 = new Room("in huis 2 met een erg mooi schilderij aan de muur");
        huis3 = new Room("in huis 3, een erg dubieus huis hmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm???");
        
        // initialise room exits
        //beginruimte.setExits(null, straat, plein, null);
        //plein.setExits(null, reuzeplein, stal, doogy_dinner);
        plein.setExits("oost", reuzeplein);
        plein.setExits("zuid", stal);
        plein.setExits("west", doogy_dinner);
        //doogy_dinner.setExits(null, plein, doogy_kamer, null);
        doogy_dinner.setExits("oost", plein);
        doogy_dinner.setExits("zuid", doogy_kamer);
        //doogy_kamer.setExits(doogy_dinner, null, null, null);
        doogy_kamer.setExits("noord", doogy_dinner);
        //stal.setExits(plein, null, null, null);
        stal.setExits("noord", plein);
        //reuzeplein.setExits(straat, null, null, plein);
        reuzeplein.setExits("noord", straat);
        reuzeplein.setExits("west", plein);
        //straat.setExits(huis2, huis3, reuzeplein, null);
        straat.setExits("noord", huis2);
        straat.setExits("oost", huis3);
        straat.setExits("zuid", reuzeplein);
        //huis1.setExits(null, huis2, null, null);
        zolder.setExits("omlaag", huis2);
        //huis2.setExits(null, null, straat, huis1);
        huis2.setExits("zuid", straat);
        huis2.setExits("omhoog", zolder);
        //huis3.setExits(null, null, null, straat);
        huis3.setExits("west", straat);
        

        currentRoom = plein;  // start game in beginruimte
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
     
       System.out.println("Je bent " + currentRoom.getDescription()); 
       System.out.print(currentRoom.getExitString());
       System.out.println();     
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
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
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("ga")) {
            goRoom(command);
        }
        else if (commandWord.equals("stop")) {
            wantToQuit = quit(command);
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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
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
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Er is hier geen doorgang!");
        }
        else {
            currentRoom = nextRoom;
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
