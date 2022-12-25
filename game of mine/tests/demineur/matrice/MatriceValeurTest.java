package demineur.matrice;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Classe MatriceValeurTest
 * @author Ismail CHAF-I
 */
public class MatriceValeurTest{

    private  MatriceValeur instance;

    @Before 
    public void init(){
        this.instance = new MatriceValeur(2, 5,5);
    }
    
    @Test
    public void testGetNbBombs() {   
        int result = this.instance.getNbBombs();
        assertEquals(2, result);
    }

    @Test
    public void testGetHauteur() {
        int result = this.instance.getHauteur();
        assertEquals(5, result);
    }

    @Test
    public void testGetLargeur() {
        int result = this.instance.getLargeur();
        assertEquals(5, result);
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(
            demineur.matrice.MatriceValeurTest.class
        );
    }
}

