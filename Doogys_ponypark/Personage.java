import java.util.HashMap;
import java.util.ArrayList;
/**
 * class Characters - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Personage
{
    private String naam;
    private String omschrijving;
    private HashMap<String, String> antwoorden;

    public Personage(String naam, String omschrijving)
    {
        this.naam = naam;
        this.omschrijving = omschrijving;
        antwoorden = new HashMap<String, String>();
        antwoorden.put("A", "antwoord A");
        antwoorden.put("B", "antwoord B");
        antwoorden.put("C", "antwoord C");
    }

    public String getNaam()
    {
        return naam;
    }

    public String getOmschrijving()
    {
        return omschrijving;
    }

    public void fillAntwoorden(String antwoord){
        if (antwoord.equals("antwoord A")) {
            antwoorden.put("A", "Dit werkt met A");
            antwoorden.put("B", "Dit werkt met A");
            antwoorden.put("C", "Dit werkt met A");
        } else if (antwoord.equals("antwoord B")) {
            antwoorden.put("A", "Dit werkt met B");
            antwoorden.put("B", "Dit werkt met B");
            antwoorden.put("C", "Dit werkt met B");
        } else if (antwoord.equals("antwoord C")) {
            antwoorden.put("A", "Dit werkt met C");
            antwoorden.put("B", "Dit werkt met C");
            antwoorden.put("C", "Dit werkt met C");

        }
    }

    public HashMap<String,String> getAntwoorden()
    {
        return antwoorden;
    }
}
