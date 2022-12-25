package demineur.matrice;

import demineur.exception.*;

import java.awt.Point;
import java.util.Set;
import java.util.LinkedHashSet;


/**
 * Classe MatriceSecret
 * @author Ismail CHAF-I
 */
public class MatriceSecret extends  MatriceValeur{

 
    

    /**
     * Constructeur de la classe MatriceSecret
     * @param nbBombs nombre de bombes
     * @param hauteur hauteur de la matrice
     * @param largeur largeur de la matrice
     */
    public MatriceSecret(int nbBombs, int hauteur, int largeur){
        super(nbBombs,hauteur,largeur);


    }

    /**
     * Teste si la case est une bombe
     * @param i ligne de la case
     * @param j colonne de la case
     * @throws DefaiteException si la case est une bombe
     * @throws VictoireException si la case n'est pas une bombe
     */
    public void testCase(int i, int j) throws DefaiteException, VictoireException{
        
        if(this.cases[i][j])
            throw new DefaiteException();

        this.visibleCases.add(this.points[i][j]);

        if (this.visibleCases.size() == (this.hauteur*this.largeur) - this.nbBombs)
            throw new VictoireException();

        if(this.getVoisinage(i, j) == 0){
            int xy_voisins[][] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
            for(int k=0;k<xy_voisins.length;k++){
                int x = i + xy_voisins[k][0];
                int y = j + xy_voisins[k][1];
                if(this.isCase(x,y) && !this.visibleCases.contains(this.points[x][y])){
                    this.testCase(x,y);
                }
            }
        }
    
    }


}
