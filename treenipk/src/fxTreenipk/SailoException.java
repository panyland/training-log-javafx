package fxTreenipk;

/**
 * @author Paavo
 * @version 29.10.2022
 *
 */
public class SailoException extends Exception{
    private static final long serialVersionUID = 1L;
    
    /**
     * Muodostaja poikkeukselle.
     * @param viesti Näytettävä viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }

}
