
/**
 * class Item - Creates an Item with an omschrijving
 *
 * @author Thomas de Bruin and Daniël Wolters
 * @version 23-01-2019
 */
public class Item
{
    private String omschrijving;
    //private int gewicht
    /**
     * Create a new Item with the omschrijving of that Item
     * @param a String with the omschrijving of that Item
     */
    public Item(String omschrijving)
    {
        this.omschrijving = omschrijving;
        //this.gewicht = gewicht;
    }
    
    /**
     * Gets the omschrijving of an Item
     * @return returns a String with the omschrijving of that Item
     */
    public String getOmschrijving() 
    {
        return omschrijving;
    }
    
    /*public int getGewicht()
    {
        return gewicht;
    }*/
}
