package demineur;

import demineur.gui.TableSecretGui;

/**
 * Classe principale du jeu
 * @author Ismail CHAF-I
 * */
public class Main {

    public static void main(String[] args){
        int nbBombs = -1;
        int hauteur = 12;
        int largeur = 12;
        
        TableSecretGui gui = new TableSecretGui(nbBombs, hauteur, largeur);

    }
}


