package demineur.matrice;
import java.util.*;
import java.util.Random;

/**
 * Classe MatriceValeur
 * @author Ismail CHAF-I
 */
public class MatriceValeur extends MatriceBomb {
 
    protected int[][] matriceVoisinage;

    /**
     * Constructeur de la classe MatriceValeur
     * @param nbBombs nombre de bombes
     * @param hauteur hauteur de la matrice
     * @param largeur largeur de la matrice
     */
    public MatriceValeur(int nbBombs, int hauteur, int largeur){
            super(nbBombs, hauteur, largeur);
            this.matriceVoisinage = new int[hauteur][largeur];
            this.initMatriceVoisinage();
    }

    /**
     * Initialise la matrice de voisinage
     */
    protected void initMatriceVoisinage(){
        for(int i = 0; i < this.hauteur; i++){
            for(int j = 0; j < this.largeur; j++){
                if(!this.cases[i][j]){
                    this.matriceVoisinage[i][j] = this.getVoisinage(i, j);
                }else{
                    this.matriceVoisinage[i][j] = -1;
                }
            }
        }
    }
    

    
}
