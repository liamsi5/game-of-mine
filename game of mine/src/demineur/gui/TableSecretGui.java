
package demineur.gui;

import demineur.matrice.*;
import demineur.exception.*;

import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;


/**
 * Class representing the gui
 * @author Ismail CHAF-I
 * */
public class TableSecretGui extends JFrame {

    private MatriceSecret matrice; // Matrice logique
    private JButton[][]  matriceButtons; // Matrice graphique
    private boolean[][]  flags; // Matrice des drapeaux
    private final int CASE_SIZE = 32; // Taille en px d'une case
    private GridLayout layout; // Layout de la matrice graphique
    private JPanel gamePanel; // Panel de la matrice graphique
    private Image bombImg; // Image de la bombe
    private Image flagImg; // Image du drapeau
    private final Color[] COLORS = {Color.BLACK,Color.CYAN,Color.BLUE,Color.GREEN,Color.YELLOW,Color.MAGENTA,Color.RED,Color.GRAY}; // Couleurs des cases

    /**
     * Constructor
     * @param nbBombs number of bombs
     * @param hauteur height of the board
     * @param largeur width of the board
    */
    public TableSecretGui(int nbBombs, int hauteur,int largeur){
        super("Demineur");
        if(nbBombs == -1){
            nbBombs = (hauteur*largeur)/10;
            String nbBombsBis = JOptionPane.showInputDialog(this, "Enter number of bombs :");
            this.matrice = new MatriceSecret(Integer.parseInt(nbBombsBis), hauteur, largeur);
        }
        else{
            this.matrice = new MatriceSecret(nbBombs, hauteur, largeur);
        }
        this.flags = new boolean[hauteur][largeur];
        this.matriceButtons = new JButton[hauteur][largeur];
        this.layout  = new GridLayout(hauteur, largeur);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(this.CASE_SIZE*hauteur+100, this.CASE_SIZE*largeur+100));
        this.setMinimumSize(new Dimension(this.CASE_SIZE*hauteur+100, this.CASE_SIZE*largeur+100));
        try{
            File file = new File("../assets/bomb.png");
            this.bombImg = ImageIO.read(file);
            this.bombImg = this.bombImg.getScaledInstance(this.CASE_SIZE, this.CASE_SIZE, Image.SCALE_SMOOTH);
            file = new File("../assets/flag.png");
            this.flagImg = ImageIO.read(file);
            this.flagImg = this.flagImg.getScaledInstance(this.CASE_SIZE, this.CASE_SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e){
            System.out.println("Erreur lors de l'ouverture de l'image");
        }

        this.gamePanel   = new JPanel();
        this.gamePanel.setLayout(this.layout);

        this.add(this.gamePanel, BorderLayout.CENTER);

        this.setLayout(this.layout);
        this.initializeButtons();
        this.setContentPane(this.gamePanel);

        this.gamePanel.revalidate();
        this.gamePanel.repaint();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
        JOptionPane.showMessageDialog(null, "Welcome ! \nLeft-Click to reveal a case.\nRigh-Click to put/remove a flag","Instructions", JOptionPane.INFORMATION_MESSAGE);
    }
    

    

    /**
     * Initialize the buttons
     * */
    private void initializeButtons(){
        
        for (int i = 0; i < this.matrice.getHauteur(); i++){
            for (int j = 0; j < this.matrice.getLargeur(); j++){
                this.matriceButtons[i][j] = new JButton();
                this.matriceButtons[i][j].setFont(new Font("Arial",Font.PLAIN, 10));
                this.matriceButtons[i][j].setPreferredSize(new Dimension(this.CASE_SIZE, this.CASE_SIZE));
                this.matriceButtons[i][j].setMinimumSize(new Dimension(this.CASE_SIZE, this.CASE_SIZE));
                this.flags[i][j] = false;
                int x = i; // impossible d'utiliser i dans la fonction anonyme
                int y = j; // impossible d'utiliser j dans la fonction anonyme
                this.matriceButtons[i][j].addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                        boolean flag = flags[x][y];
                        if(!flag){
                            matriceButtons[x][y].setIcon(new ImageIcon(flagImg));
                        } else {
                            matriceButtons[x][y].setIcon(null);
                        }
                        flags[x][y] = !flag;
                    }
                }
                public void mousePressed(MouseEvent e) { // Ignorer cette ligne 
                }

                public void mouseReleased(MouseEvent e) { // Ignorer cette ligne 
                }

                public void mouseEntered(MouseEvent e) { // Ignorer cette ligne 
                }

                public void mouseExited(MouseEvent e) { // Ignorer cette ligne 
                }
                });
                this.matriceButtons[i][j].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean over = false;
                    try {
                        matrice.testCase(x, y);

                    } catch (DefaiteException e1) {
                            over = true;
                            gameOver(false);
                    } catch (VictoireException e2) {
                            System.out.println("Vous avez gagné");
                            over = true;
                            gameOver(true);

                    }
                    if(!over)
                        neighbordsEffect();

                }
                });
                this.gamePanel.add(this.matriceButtons[i][j]);
            }
        }

    }

    /**
     * Update the buttons
     * */
    private void neighbordsEffect(){
    
        for (Point p : this.matrice.getVisibleCases()){
            int x = (int) p.getX();
            int y = (int) p.getY();
            int voisinage = matrice.getVoisinage(x, y);
            this.matriceButtons[x][y].setText(String.valueOf(voisinage));
            this.matriceButtons[x][y].setForeground(COLORS[voisinage]);
            this.matriceButtons[x][y].setOpaque(true);
        }

    }
   
    /**
     * Game over
     * @param win true if the player won, false otherwise
     * */
    public void gameOver(boolean win){

        Color color = win ? Color.GREEN : Color.RED;
        for (int i = 0; i < this.matrice.getHauteur(); i++){
            for (int j = 0; j < this.matrice.getLargeur(); j++){
                if (!this.matrice.getCase(i,j)){
                    this.matriceButtons[i][j].setText(String.valueOf(matrice.getVoisinage(i, j)));
                    this.matriceButtons[i][j].setBackground(color);
                    this.matriceButtons[i][j].setOpaque(true);
                }else{
                    this.matriceButtons[i][j].setIcon(new ImageIcon(this.bombImg));
                }
                this.matriceButtons[i][j].setEnabled(false);
            }
        }
        if(win){
            JOptionPane.showMessageDialog(null, "YOU WIN","Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "YOU LOSE","Game Over", JOptionPane.ERROR_MESSAGE);
        }
    }

}
