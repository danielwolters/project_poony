import java.util.HashMap;
import java.util.ArrayList;
/**
 * class Characters - Creates a Personage with a naam, omschrijving a 
 * HashMap with answers the Personage can currently give to the player
 * and a onderwerp for the conversation
 *
 * @author Thomas de Bruin and Daniël Wolters
 * @version 23-01-2019
 */
public class Personage
{
    private String naam;
    private String omschrijving;
    private HashMap<String, String> antwoorden;
    private String onderwerp;

    /**
     * Creates a new Personage with the naam and omschrijving of that Personage
     * sets the onderwerp of the Personage to an empty String
     * Creates an empty HashMap for answers
     * @param a String with the naam of the Personage
     * @parm a String with the omschrijving of the Personage
     */
    public Personage(String naam, String omschrijving)
    {
        this.naam = naam;
        this.omschrijving = omschrijving;
        antwoorden = new HashMap<String, String>();
        onderwerp = "";
    }

    /**
     * Sets the name of the Personage
     * @param a String with the name of the Personage
     */
    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    /**
     * Sets the onderwerp of the Personage
     * @param a String with the onderwerp for the Personage
     */
    public void setOnderwerp(String veld)
    {
        onderwerp = veld;
    }

    /**
     * Gets the onderwerp of the Personage
     * @return return a String with the onderwerp of the Personage
     */
    public String getOnderwerp()
    {
        return onderwerp;
    }

    /**
     * Gets the name of the Personage
     * @return return a String with the name of the Personage
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     * Gets the omschrijving of a Personage
     * @return returns a String with the omschrijving of the Personage
     */
    public String getOmschrijving()
    {
        return omschrijving;
    }
    
/**
 * Fills the HashMap with answers of the Personage based on the input it gets
 * @param a String with the input 
 */
    public void fillAntwoorden(String antwoord){
        if (antwoord.equals("Ik ben Claudia de brij en zit ook vast in dit pretpark. Kan jij me helpen om de uitgang te vinden?") || antwoord.equals("Ik heb je hulp wel nodig !")) {
            onderwerp = "Ik wil je wel helpen om de uitgang te zoeken, maar dan moet je eerst een vraag goed beantwoorden. Hoe planten egels zich voort? ";
            antwoorden.put("A", "Je loopt met veel gesnuffel en gehijg in cirkels om je vrouwtje ");
            antwoorden.put("B", "Je springt er gewoon boven op");
            antwoorden.put("C", "Ik zet mijn stekels op om haar te imponeren en lok haar naar mijn hol");
        } else if (antwoord.equals("Gast wat lul jij. Egels kunnen helemaal niet praten")) {
            onderwerp = "Je hebt gelijk ik hou mn bek wel even dicht. Maar N00bs als jij zouden ook niet mogen praten, maar toch hoor ik je nogsteeds ..."; 
            antwoorden.put("A", "Dan ga ik maar weer ");
            antwoorden.put("B", "Hou je bek kut egel");
            antwoorden.put("C", "Ik ben geen n00b…");
        } else if (antwoord.equals("Ik ben geen n00b…")) {
            onderwerp = "Als je geen n00b bent heb je mijn hulp toch niet ook nodig ";
            antwoorden.put("A", "Ik heb je hulp wel nodig !");
            antwoorden.put("B", "Ik ga wel egels roosteren");
            antwoorden.put("C", "Ik heb je hulp niet nodig ");
        } else if (antwoord.equals("Ja! Kan je me vertellen of hier een uitgang is?")) {
            onderwerp = "Er is hier een uitgang achter mij. Heb je een zadel zodat we samen weg kunnen gaan?";
            antwoorden.put("A", "Ja");
            antwoorden.put("B", "Nee nog niet");
            antwoorden.put("C", "Mischien...");
        } else if (antwoord.equals("Ja")||antwoord.equals("Mischien...")){
            antwoorden.put("A", "checkzadel");
        }
        else if (antwoord.equals("Nee maar ik lust wel een lekker snackie")){
            antwoorden.put("A", "snackie");
        }
        else if (antwoord.equals("Ja ik denk het wel")) {
            antwoorden.put("A", "checkoffer");
        }
        else if (antwoord.equals("Nee, kan je me een hintje geven?")){
            antwoorden.put("A", "hintje");
        }

        
        else if(antwoord.equals("Dat zeg ik lekker niet") || antwoord.equals("Ik ga wel egels roosteren")  || antwoord.equals("Hou je bek kut egel")
        || antwoord.equals("Dan ga ik maar weer ") || antwoord.equals("Ik heb je hulp niet nodig ") || antwoord.equals("Je springt er gewoon boven op")
        || antwoord.equals("Gast wat lul jij. Paarden kunnen helemaal niet praten") || antwoord.equals("Je stinkt")  || antwoord.equals("Nee nog niet")) {
            antwoorden.put("A", "Gefaald");
        } else if (antwoord.equals("Ik zet mijn stekels op om haar te imponeren en lok haar naar mijn hol")) {
            onderwerp = "En wat denk je dat daarna gebeurt?";
            antwoorden.put("A", "Dan komt jouw grootste stekel naar buiten");
            antwoorden.put("B", "Dan gaan jullie lekker eten");
            antwoorden.put("C", "Daar ga ik niet over nadenken");
        } else if (antwoord.equals("Dan komt jouw grootste stekel naar buiten") || antwoord.equals("Dan gaan jullie lekker eten") 
        || antwoord.equals("Daar ga ik niet over nadenken")
        || antwoord.equals("Je loopt met veel gesnuffel en gehijg in cirkels om je vrouwtje ")) {
            antwoorden.put("A", "Succes");
        }
    }

    /**
     * Gets the HashMap with answer of the Personage
     * @return return a HashMap with answers of the Personage
     */
    public HashMap<String,String> getAntwoorden()
    {
        return antwoorden;
    }
}
