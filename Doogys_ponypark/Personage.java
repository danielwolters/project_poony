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
    private String onderwerp;

    public Personage(String naam, String omschrijving)
    {
        this.naam = naam;
        this.omschrijving = omschrijving;
        antwoorden = new HashMap<String, String>();
        onderwerp = "";
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }
    
    public void setOnderwerp(String veld)
    {
        onderwerp = veld;
    }

    public String getOnderwerp()
    {
        return onderwerp;
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
        } else if(antwoord.equals("Dat zeg ik lekker niet") || antwoord.equals("Ik ga wel egels roosteren")  || antwoord.equals("Hou je bek kut egel")
        || antwoord.equals("Dan ga ik maar weer ") || antwoord.equals("Ik heb je hulp niet nodig ") || antwoord.equals("Je springt er gewoon boven op")) {
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

    public HashMap<String,String> getAntwoorden()
    {
        return antwoorden;
    }
}
