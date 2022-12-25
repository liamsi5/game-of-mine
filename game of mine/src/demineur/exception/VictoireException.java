package demineur.exception;

/**
 * Exception levée lorsque le joueur a gagné
 * @author Ismail CHAF-I
 */
public class VictoireException extends Exception{

    /**
     * Constructeur de la classe VictoireException
     * @param msg le message à afficher
     */
    public VictoireException(String msg){
        super(msg);
    } 

    /**
     * Constructeur de la classe VictoireException
     */
    public VictoireException(){
        super();
    } 
}
