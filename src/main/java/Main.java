import javax.swing.*;

public class Main {

    public static Scene scene;

    // Creation de la fenêtre graphique de l'application
    public static void main(String[] args){
        JFrame fenetre = new JFrame("Jeu du labyrinthe");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(700, 360);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setAlwaysOnTop(true);


        scene = new Scene();

        fenetre.setContentPane(scene); // La fenêtre est associé à la scene du jeu
        fenetre.setVisible(true);
    }
}
