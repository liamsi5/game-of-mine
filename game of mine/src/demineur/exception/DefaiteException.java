package demineur.exception;

/**
 * Exception levée lorsque le joueur perd la partie
 * @author Ismail CHAF-I
 * */
public class DefaiteException extends Exception{

    /**
     * Constructeur de la classe DefaiteException
     * @param msg message à afficher
     * */
    public DefaiteException(String msg){
        super(msg);
    } 

    /**
     * Constructeur de la classe DefaiteException
     * */
    public DefaiteException(){
        super();
    } 
}
