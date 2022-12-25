package demineur.matrice;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Random;
import java.awt.Point;

/**
 * Classe MatriceBomb
 * @author Ismail CHAF-I
 */
public class MatriceBomb{

    protected int hauteur;
    protected int largeur;
    protected int nbBombs;
    protected int nbBombsTrouvees;
    protected int z;
    protected final Random RANDOM = new Random();

    protected boolean[][] cases;
    protected Set<Point> visibleCases;
    protected Point[][] points;
 
    /**
     * Constructeur de la classe MatriceBomb
     * @param hauteur hauteur de la matrice
     * @param largeur largeur de la matrice
     * @param nbBombs nombre de bombes
     */
    public MatriceBomb(int nbBombs, int hauteur, int largeur){
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.nbBombs = nbBombs;    
        this.nbBombsTrouvees = 0;
        this.cases = new boolean[hauteur][largeur];
        this.visibleCases = new LinkedHashSet<Point>();
        this.points = new Point[hauteur][largeur];
        // initialisation du board
        this.initialise();    
    }

    /**
     * Initialise le board
     */
    public void initialise(){
        
        for(int x=0; x < cases.length; x++){
            for(int y=0; y < cases[0].length; y++){
                this.cases[x][y] = false;
                this.points[x][y] = new Point(x,y);
            }
        }
        this.putBombs();        
    }

    /**
     * Place les bombes sur le board
     */
    private void putBombs(){
        for(int i=0; i < this.nbBombs; i++){ 
            this.cases[this.RANDOM.nextInt(this.hauteur)][this.RANDOM.nextInt(this.largeur)]=true;
        }
    } 

    /**
     * Getter pour l'hauteur
     * @return l'hateur
     */
    public int getHauteur(){
        return this.hauteur;
    }
    /**
     * Getter pour la largeur
     * @return largeur
     */
    public int getLargeur(){
        return this.largeur;
    }
    /**
     * Getter pour le nb de bombes
     * @return nombre de bombes
     */
    public int getNbBombs(){
        return this.nbBombs;
    }
    
   
    /**
     * Getter pour la case
     * @param x abscisse
     * @param y ordonnée
     * @return la case
     */
    public boolean getCase(int x, int y){
        return this.cases[x][y];
    }
    /**
     * Prédicat pour savoir si la case est valide
     * @param x abscisse
     * @param y ordonnée
     * @return true si la case est valide
     */
    protected boolean isCase(int i, int j) {
        return i < this.hauteur &&  i >= 0 && j < this.largeur && j >= 0;
    }


    /**
     * Affiche la case de coordonnées (x,y)
     * @param x abscisse
     * @param y ordonnée
     */

    public void displayCase(int x,int y){
        System.out.print(this.cases[x][y]+ " ");
    }

    /**
     * Renvoi le nombre de bombes autour de la case (x,y)
     * @param x abscisse
     * @param y ordonnée
     * @return nombre de bombes autour de la case (x,y)
     */
    public int getVoisinage(int i, int j){

        int nbVoisins = 0;
        int xy_voisins[][] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        int[] cords = {0,0};
        for (int k = 0; k < 8;k++){ // 8 voisins possibles
            cords = xy_voisins[k];
            if(this.isCase(i+cords[0],j+cords[1]))
                if(this.cases[i+cords[0]][j+cords[1]]) nbVoisins++;
            
        }
        return nbVoisins;

    }

    /**
     * Affiche le board
     */
    public void display(){
        for(int i=0; i < this.hauteur; i++){
            for(int j=0; j < this.largeur; j++){
                this.displayCase(i, j);
            }
            System.out.println();
        }
    }
    

    /**
     * Getter pour le nombre de bombes trouvées
     * @return nombre de bombes trouvées
     */
    public int getNbBombsTrouvees(){
        return this.nbBombsTrouvees;
    }


    /**
     * Getter pour les cases visibles
     * @return array list des cases visibles
     */
    public Set<Point> getVisibleCases(){
        return this.visibleCases;
    }

    /**
     * Resets les cases visibles
     */
    public void cleanVisibleCases(){
        this.visibleCases.clear();
    }

}
    
