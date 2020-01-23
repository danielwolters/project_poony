import java.util.ArrayList;
import java.util.HashMap;
/**
 *  This class is the main class of the "Ponypark Slagharen" application. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Thomas de Bruin, Daniël Wolters
 * @version 23-01-2020
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private boolean uitgespeeld;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
        uitgespeeld = false;
    }

    /**
     * Create all the rooms and link their exits together and set the items and set locks on rooms and Personage.
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
        zolder = new Room("op zolder, het ruikt alsof er hier iets verborgen ligt");
        huis2 = new Room("in huis 2 met een erg mooi schilderij aan de muur");
        huis3 = new Room("in huis 3, een erg dubieus huis hmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm???");

        // initialise room exits and items and personages and locks
        //plein
        plein.setExits("oost", reuzeplein);
        plein.setExits("zuid", stal);
        plein.setExits("west", doogy_dinner);

        //doogy_dinner
        doogy_dinner.setExits("oost", plein);
        doogy_dinner.setExits("zuid", doogy_kamer);

        //doogy_kamer
        doogy_kamer.setExits("noord", doogy_dinner);
        doogy_kamer.setPersonage(new Personage("Doogy", "leuke egel"));

        //stal
        stal.setExits("noord", plein);
        //stal.setItem(new Item("zadel"));
        stal.setSlot(true);
        stal.setPersonage(new Personage("Henry", "paard dat doodgaat"));

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

    /**
     * Prints the exits and items of the room the player is currently in
     */
    private void look()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Goes back to the room the player was previously in
     * If there are no rooms to go back to
     * prints: 'je kunt niet verder terug' 
     */
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

    /**
     * Add the item that currently is in the room to the goodieBagga of the player
     * If there are no items in the room print: 'kill ben je blind er ligt hier toch niks'  
     */
    private void pak()
    {
        if(player.getCurrentRoom().getRoomItem().isEmpty()) {
            System.out.println("Kill ben je blind er ligt hier toch niks");
        } else {
            player.addItem(player.getCurrentRoom().getRoomItem().get(0));
        }
    }

    /**
     * Drops the item that the player currently has in his goodieBagga to the room the
     * player is currently in
     */
    private void wegdoen()
    {
        player.dropItem();
    }

    /**
     * Print the item that is currently in the goodieBagga of the player
     */
    private void inhoud()
    {
        player.inhoudGoodieBagga();
    }

    /**
     * Uses the item that the player currently has in his goodieBagga
     * If there are no items in the goodieBagga print: 'je hebt niks om te gebruiken'
     * If the player is 'op het plein' and has the item 'sleutel' unlocks stal
     * If the player is in 'in de stal met paarden' en has the item 'mes' the item mes gets destroyed
     * and the player recieves the item 'paardenvlees' in return
     * Else print 'Er is hier niks om een actie uit te voeren'  
     */
    private void gebruik()
    {
        if(player.validGoodieBagga() == false) {
            System.out.println("Je hebt niks om te gebruiken");
        } else {
            if(player.getCurrentRoom().getDescription().equals("op het plijn") && player.getGoodieBaggaString().equals("sleutel")) {
                Room nextRoom = player.getCurrentRoom().getExit("zuid");
                nextRoom.setSlot(false);
                System.out.println("De stal is geopend");
                player.getGoodieBagga().remove(0);
            } else if(player.getCurrentRoom().getDescription().equals("in de stal met paarden") && player.getGoodieBaggaString().equals("mes")) {
                player.getGoodieBagga().remove(0);
                player.getGoodieBagga().add(new Item("paardenvlees"));
                System.out.println("Je zwaait je mes in het rond en raakt de slagader van een trouwe viervoeter naast je");
                System.out.println("Het arme beestje hinnikt nog een paar keer voor het zijn laatste stapjes zet en valt dan ten gronde");
                System.out.println("Al kijkend in de ogen van het beestje zie je zijn ziel langzaam heengaan");
                System.out.println("Alle andere paarden kijken je geschokt aan terwijl je alleen maar aan het offer kan denken");
                System.out.println("De slag is geslagen, het beest is geveld...");
                System.out.println("Langzaam, maar zeker van je zaak snij je een stuk vlees uit het inmiddels levenloze lichaam");
                System.out.println("Henry lacht op de achtergrond en zegt:'Dat was toch altijd al een kut beest'");
                System.out.println();
                System.out.println();
                System.out.println("*paardenvlees opgepakt*");
            } else {
                System.out.println("Er is hier niks om een actie uit te voeren");
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
            finished = uitgespeeld;
        }
        System.out.println("Bedankt voor het spelen, tot snel!");
    }

/**
 * Prints the exits of the room the player is currently in and the items if there are any
 * also adds a whitespace at the last line
 */
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
        System.out.println("*******************************************************************************");
        System.out.println("*Welkom in PonyPark Slagharen!                                                *");
        System.out.println("*Enkele eeuwen geleden vond hier een grote oorlog plaats.                     *");
        System.out.println("*De Romeinen die het PonyPark Slagharen bezetten waren onder aanval           *");
        System.out.println("*van de Grieken. PonyPark Slagharen was in die tijd een erg gewilde vestiging *");
        System.out.println("*met buitengewone kwaliteiten. Helaas is door de wrede oorlog het PonyPark    *");
        System.out.println("*grotendeels verwoest. Toch gaan er geruchten dat er nog leven is in het park.*");
        System.out.println("*Wellicht kunnen deze levenden hulp bieden, of misschien toch niet hmmmm?     *");
        System.out.println("*Je vindt jezelf op het plein in het Ponypark.                                *");
        System.out.println("*Probeer de uitgang te vinden om het spel te winnen                           *");
        System.out.println("*Typ 'help' als je hulp nodig m.b.t. de commands(alleen aangeraden voor n00bs)*");
        System.out.println("*******************************************************************************");
        printLocationInfo();
        System.out.println();
    }

    /**
     * Creates a conversation with a Personage and checks if the conversastion is at its end and reacts based on that
     * else proceeds the conversation
     */
    private void gesprek() {
        boolean finished = false;
        while (! finished) {
            if(player.getCurrentRoom().getPersonage().getAntwoorden().get("A").equals("Succes")) {
                System.out.println("Heel fijn dat je er zo over denkt");
                System.out.println("Kom mee naar het reuzenrad dat laat ik je mijn verrassing zien!");
                System.out.println("*Jullie lopen naar het plein met het reuzenrad*");

                player.getCurrentRoom().setPersonage(null);
                player.setCurrentRoom(player.getCurrentRoom().getExit("noord").getExit("oost").getExit("oost"));

                System.out.println();
                System.out.println("*Jullie stappen in het reuzenrad*");
                System.out.println();

                System.out.println("Laat me jou eens wat vertellen:");
                System.out.println("Tijdens de slag om het welbefaamde ponypark, hebben de Romeinen de paardenstal ten zuide van het plijn erg veel gebruikt.");
                System.out.println("Men zegt dat daarginds een extra uitgang in het leven is geroepen om van de Grieken te ontsnappen.");
                System.out.println();
                System.out.println("Maar denk niet dat deze uitgang zomaar te betreden is. Deze uitgang is slechts alleen");
                System.out.println("met een paard te begaan. Maar voor u zo'n trouwe viervoeter kunt overmeesteren moet");
                System.out.println("u deze eerst bekleden met bijpassend gerij. Voordat u dit gerij kunt bemachtigen");
                System.out.println("zult u mij eerst een offer moeten brengen.");
                System.out.println();

                System.out.println("*Jullie stappen uit het reuzenrad*");

                System.out.println();
                System.out.println("Mocht u dit offer toe-eigenen dan kunt u mij dit toe leveren in de snackbar\n Paardenvlees is mijn favoriet(*knipoog*)");
                System.out.println("*Doogy loopt naar de ordinaire vreetschuur*");

                System.out.println();
                printLocationInfo();
                player.getCurrentRoom().getExit("west").getExit("west").setPersonage(new Personage("Doogy", "leuke egel"));
                player.getVorigeKamers().clear();
                finished = true;
            } else if (player.getCurrentRoom().getPersonage().getAntwoorden().get("A").equals("Gefaald")) {
                System.out.println("*" + player.getCurrentRoom().getPersonage().getNaam() + " heeft je er uitgezet omdat je niet het gewenste antwoord gaf*");

                player.setCurrentRoom(player.getCurrentRoom().getExit("noord"));
                printLocationInfo();
                finished = true;
            } else if (player.getCurrentRoom().getPersonage().getAntwoorden().get("A").equals("checkzadel")){

                if(!player.getGoodieBagga().isEmpty()&&player.getGoodieBaggaString().equals("zadel")){
                    System.out.println("Henry en jij rijden samen naar de uitgang\nVoor jullie ligt niks anders dan een uitgestrekte horizon");
                    System.out.println("Verdwaald in je gedachten denk je na over de avonturen die je hebt beleefd");
                    System.out.println("Al achterom kijkend wordt Ponypark Slagharen steeds kleiner");
                    System.out.println("Tranen sijpelen langzaam naar beneden als je nog  één keer terug denkt aan arme Doogy.");
                    System.out.println("Er is geen tijd om na te denken over het verleden. Je richt je blik naar voren en zegt tegen jezelf");
                    System.out.println("'het is tijd om na te gaan denken over de toekomst'");
                    System.out.println();
                    System.out.println("Plots schrik je wakker en ruikt de geur van vergebakken broodjes.");
                    System.out.println("Je moeder staat in je kamer en vraagt of je zin hebt in ontbijt");
                    System.out.println("Je realiseert je dat het allemaal een droom was en je loopt naar beneden om te gaan ontbijten");
                    System.out.println("Waneer je gaat zitten aan de keuekntafel zegt je moeder : 'Ik heb je favoriete eten klaargemaakt, paardenvlees!'");
                    System.out.println();
                    System.out.println("Gefeliciteerd broeder of zuster(jaja overal aan gedacht) u heeft het spel uitgespeeld!");
                    finished = true;
                    uitgespeeld = true;

                }
                else {
                    System.out.println("Vind eerst het zadel en kom dan terug.");
                    finished = true;
                }

            } else if (player.getCurrentRoom().getPersonage().getAntwoorden().get("A").equals("snackie")){
                System.out.println("* Je verlaat de kamer met een rond buikje en een snackie *");
                player.setCurrentRoom(player.getCurrentRoom().getExit("oost"));
                printLocationInfo();
                finished = true;
            }
            else if (player.getCurrentRoom().getPersonage().getAntwoorden().get("A").equals("checkoffer")){
                if(!player.getGoodieBagga().isEmpty()&&player.getGoodieBaggaString().equals("paardenvlees")){
                    System.out.println("Bedankt voor dit malse offer, ik ga er wat lekkers van maken");
                    System.out.println("Als dank voor dit offer krijg je van mij dit speciale gerij");
                    System.out.println("*paardenvlees gegeven*");
                    player.getGoodieBagga().remove(0);
                    System.out.println("*zadel ontvangen*");
                    player.getGoodieBagga().add(new Item("zadel"));
                    finished = true;
                } else {

                    System.out.println("*Doogy is boos aangezien je het offer niet hebt en heeft je de kamer uitgezet*");
                    player.setCurrentRoom(player.getCurrentRoom().getExit("oost"));
                    printLocationInfo();
                    finished = true;
                } 
            } else if (player.getCurrentRoom().getPersonage().getAntwoorden().get("A").equals("hintje")) {
                System.out.println("De snack van de dag is gemaakt van paard\nFijne dag verder");

                finished = true;
            }
            else {
                Command command = parser.getCommand();
                finished = processGesprek(command);
            }
        }
    }

    /**
     * Process the conversation with a Personage
     * Fills the answer of the the character based on what the player said and prints them
     * If the player uses a command other the 'gebruik' continue the conversation
     * If the player uses the command 'gebruik' check if its appropriate
     * If the player is at the end of a conversation of uses the command 'gebruik'
     * appropriate end the conversation
     * @param command the Command the player types
     * @return a boolean to check if the conversation should continue or not
     */
    private boolean processGesprek(Command command)
    {
        boolean want_to_quit = false;
        Personage poppetje = player.getCurrentRoom().getPersonage();
        if(command.isUnknown()) {
            System.out.println("Beantwoord mijn vraag broeder");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("A")) {
            //Personage doogy = player.getCurrentRoom().getPersonage();
            poppetje.fillAntwoorden(poppetje.getAntwoorden().get("A"));
        }
        else if (commandWord.equals("B")) {
            //Personage doogy = player.getCurrentRoom().getPersonage();
            poppetje.fillAntwoorden(poppetje.getAntwoorden().get("B"));
        }
        else if (commandWord.equals("C")) {
            //Personage doogy = player.getCurrentRoom().getPersonage();
            poppetje.fillAntwoorden(poppetje.getAntwoorden().get("C"));
        }
        else if (commandWord.equals("ga") || commandWord.equals("stop") || commandWord.equals("help") || commandWord.equals("kijk") || commandWord.equals("terug") ||
        commandWord.equals("pak") || commandWord.equals("wegdoen") || commandWord.equals("inhoud")){
            System.out.println("Beantwoord mijn vraag broeder");
            return false;
        } else if (commandWord.equals("gebruik")) {
            if(player.getCurrentRoom().getDescription().equals("in de stal met paarden")) {
                if(player.validGoodieBagga()) {
                    if(player.getGoodieBagga().get(0).getOmschrijving().equals("mes")) {
                        gebruik();
                        return true;
                    } else {
                        System.out.println("Beantwoord mijn vraag broeder");
                        return false;
                    }
                } else {
                    System.out.println("Beantwoord mijn vraag broeder");
                    return false;
                }
            } else {
                System.out.println("Beantwoord mijn vraag broeder");
                return false;
            }
        }

        if(poppetje.getAntwoorden().get("A").equals("Succes") || poppetje.getAntwoorden().get("A").equals("Gefaald") || poppetje.getAntwoorden().get("A").equals("checkzadel")||
        poppetje.getAntwoorden().get("A").equals("checkoffer")||poppetje.getAntwoorden().get("A").equals("hintje")||poppetje.getAntwoorden().get("A").equals("snackie"))  {
            return false;
        } else { 
            printAntwoorden();
        }
        return want_to_quit;
    }

    /**
     * Prints all the answers of the character 
     */
    public void printAntwoorden() {
        System.out.println(player.getCurrentRoom().getPersonage().getOnderwerp() + "\n");
        System.out.print("A: " + player.getCurrentRoom().getPersonage().getAntwoorden().get("A") + "\n");
        System.out.print("B: " +player.getCurrentRoom().getPersonage().getAntwoorden().get("B") + "\n");
        System.out.print("C: " +player.getCurrentRoom().getPersonage().getAntwoorden().get("C") + "\n");
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
        else if (commandWord.equals("terug")) {
            terug();
        }
        else if (commandWord.equals("pak")) {
            if(command.hasSecondWord()) {
                System.out.println("Alleen pak is voldoende kill");
            } else {
                pak();
            }
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
        //System.out.println("Go ride or die");
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
        } else if(nextRoom.getPersonage() != null) {
            if(nextRoom.getPersonage().getNaam().equals("Doogy")&& nextRoom.getDescription().equals("in de kamer van Doogy")) {
                player.setCurrentRoom(nextRoom);
                printLocationInfo();
                System.out.println();
                //System.out.println("Hallo ik ben " + nextRoom.getPersonage().getNaam() + " en ik ben een" + nextRoom.getPersonage().getOmschrijving());
                System.out.println("Oh, Hallo...\nIk ben Doogy de egel. \nBen jij een verdwaalde bezoeker? Ik heb hier in jaren al niemand meer gezien.");
                nextRoom.getPersonage().setOnderwerp("Inmiddels zit ik hier al zo lang vast dat ik mijn eigen snackbar ben begonnen waar je veel verschillende soorten vlees kan eten." + 
                    "\nJe bent er al langs gekomen, hij heet de ordinaire vreetschuur. \nEn aan wie heb ik dit bezoek te danken?");
                nextRoom.getPersonage().getAntwoorden().put("A", "Ik ben Claudia de brij en zit ook vast in dit pretpark. Kan jij me helpen om de uitgang te vinden?");
                nextRoom.getPersonage().getAntwoorden().put("B", "Dat zeg ik lekker niet");
                nextRoom.getPersonage().getAntwoorden().put("C", "Gast wat lul jij. Egels kunnen helemaal niet praten");
                printAntwoorden();
                gesprek();
            }
            else if (nextRoom.getPersonage().getNaam().equals("Doogy")&& nextRoom.getDescription().equals("in de ordinaire vreetschuur")){
                player.setCurrentRoom(nextRoom);
                printLocationInfo();
                System.out.println();
                System.out.println("Oh, daar ben je weer!\nWat ben ik blij om jou te zien!");
                nextRoom.getPersonage().setOnderwerp("Heb je toevallig waar ik om heb gevraagd?");
                nextRoom.getPersonage().getAntwoorden().put("A", "Nee maar ik lust wel een lekker snackie");
                nextRoom.getPersonage().getAntwoorden().put("B", "Ja ik denk het wel");
                nextRoom.getPersonage().getAntwoorden().put("C", "Nee, kan je me een hintje geven?");
                printAntwoorden();
                gesprek();
            }
            else if(nextRoom.getPersonage().getNaam().equals("Henry")) {
                player.setCurrentRoom(nextRoom);
                printLocationInfo();
                System.out.println();
                System.out.println("*Hinnik* *Hinnik*");
                nextRoom.getPersonage().setOnderwerp("Hallo daar vreemdeling. Ik ben Henry het pratende paard.\nKan ik jou misschien ergens mee helpen?");
                nextRoom.getPersonage().getAntwoorden().put("A", "Ja! Kan je me vertellen of hier een uitgang is?");
                nextRoom.getPersonage().getAntwoorden().put("B", "Je stinkt");
                nextRoom.getPersonage().getAntwoorden().put("C", "Gast wat lul jij. Paarden kunnen helemaal niet praten");
                printAntwoorden();
                gesprek();
            }
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
            System.out.println("Wat wil je stoppen?");
            return false;
        }
        else {
            return uitgespeeld = true;  // signal that we want to quit

        }
    }
}
