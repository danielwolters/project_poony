
/**
 * class Item - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Item
{
    private String omschrijving;
    //private int gewicht
    
    public Item(String omschrijving)
    {
        this.omschrijving = omschrijving;
        //this.gewicht = gewicht;
    }
    
    public String getOmschrijving() 
    {
        return omschrijving;
    }
    
    /*public int getGewicht()
    {
        return gewicht;
    }*/
}
