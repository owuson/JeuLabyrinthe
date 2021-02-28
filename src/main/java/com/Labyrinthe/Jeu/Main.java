package com.Labyrinthe.Jeu;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static Scene scene;


    public static void main(String[] args){
        // Creation de la fenêtre graphique de l'application
        JFrame fenetre = new JFrame("Jeu du labyrinthe");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1400, 720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(true);
        fenetre.setAlwaysOnTop(true);

        JPanel panneau = new JPanel();
        fenetre.setContentPane(panneau);
        fenetre.setVisible(true);
        panneau.setBackground(Color.GRAY);

        createMenuBar(fenetre);
    }

    private static void createMenuBar(JFrame frame) {
		//final JFrame frame = fenetre;

		JMenuBar bar = new JMenuBar();

		JMenu soignant = new JMenu("Soignant");
		soignant.setMnemonic(KeyEvent.VK_S);

		JMenuItem login = new JMenuItem("S'identifier");
		login.setMnemonic(KeyEvent.VK_I);
		login.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				KeyEvent.CTRL_MASK));
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//chooseMazeFile();
			}
		});

        final JDialog dialogParametre = new JDialog(frame, "Parametre de jeu", true);
		dialogParametre.setResizable(false);
		dialogParametre.setSize(450, 200);

		Border border = BorderFactory.createEmptyBorder(15, 15, 15, 15);

        JPanel difficultyPanel = new JPanel(); // Flow layout will center button.
		difficultyPanel.setBorder(border);
		difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));

        JPanel dureePanel = new JPanel();
        dureePanel.setBorder(border);
		dureePanel.setLayout(new BoxLayout(dureePanel, BoxLayout.Y_AXIS));

		JLabel difficultyLabel = new JLabel("Difficultée:", JLabel.RIGHT);
		//final JTextField widthTextfield = new JTextField("10");
		JLabel dureeLabel = new JLabel("Durée:", JLabel.RIGHT);
		//final JTextField heightTextfield = new JTextField("10");
		//final JCheckBox checkbox = new JCheckBox("Show animation");

        ButtonGroup difficultyGroup = new ButtonGroup();

        final JRadioButton  radioFacile = new JRadioButton("Facile");
        radioFacile.setSelected(true);

        difficultyGroup.add(radioFacile);

        
        final JRadioButton  radioMoyenne = new JRadioButton("Moyenne");
        difficultyGroup.add(radioMoyenne);

        final JRadioButton  radioDifficile = new JRadioButton("Difficile");
        difficultyGroup.add(radioDifficile);

        ButtonGroup dureeGroup = new ButtonGroup();

        final JRadioButton  radio1 = new JRadioButton("5 min");
        radio1.setSelected(true);
        dureeGroup.add(radio1);

        
        final JRadioButton  radio2 = new JRadioButton("10 min");
        dureeGroup.add(radio2);

        final JRadioButton  radio3 = new JRadioButton("15 min");
        dureeGroup.add(radio3);



		JButton btnAppliquer = new JButton("Appliquer");
		btnAppliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				dialogParametre.setVisible(false);
				dialogParametre.dispose();

				/*_labyrinthModel.generateLabyrinth(
						Integer.parseInt(widthTextfield.getText()),
						Integer.parseInt(heightTextfield.getText()),
						checkbox.isSelected());
                */
			}
		});

		difficultyPanel.add(difficultyLabel, BorderLayout.EAST);
		difficultyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		//difficultyPanel.add(widthTextfield, BorderLayout.WEST);
		//difficultyPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		//difficultyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		//difficultyPanel.add(heightTextfield, BorderLayout.WEST);
		
        //difficultyPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        difficultyPanel.add(radioFacile);
        difficultyPanel.add(radioMoyenne);
        difficultyPanel.add(radioDifficile);
		//difficultyPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        
        dureePanel.add(dureeLabel, BorderLayout.EAST);
        dureePanel.add(radio1);
        dureePanel.add(radio2);
        dureePanel.add(radio3);

		difficultyPanel.add(btnAppliquer);
        dialogParametre.getContentPane().setLayout(new FlowLayout());
		dialogParametre.getContentPane().add(difficultyPanel);
        dialogParametre.getContentPane().add(dureePanel);
		dialogParametre.setLocationRelativeTo(frame);

		JMenuItem parametre = new JMenuItem("Parametre de Jeu");
		parametre.setMnemonic(KeyEvent.VK_P);
		parametre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				KeyEvent.CTRL_MASK));
		parametre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                /*
                if (_labyrinthModel.isGenerating()) {
					JOptionPane.showMessageDialog(frame,
							"Wait for running generation to finish!");
					return;
				}
                */
				dialogParametre.setVisible(true);
			}
		});

        JMenuItem historique = new JMenuItem("Historique des Scores");
		historique.setMnemonic(KeyEvent.VK_H);
		historique.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				KeyEvent.CTRL_MASK));
		historique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//saveMazeFile();
			}
		});

        JMenuItem jouer = new JMenuItem("Jouer");
		jouer.setMnemonic(KeyEvent.VK_J);
		jouer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,
				KeyEvent.CTRL_MASK));
		jouer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//saveMazeFile();
			}
		});


		soignant.add(login);
		soignant.add(historique);
        soignant.add(parametre);
        soignant.add(jouer);
		bar.add(soignant);

        JMenu patient = new JMenu("Patient");
		patient.setMnemonic(KeyEvent.VK_P);

		JMenuItem ajouter = new JMenuItem("Ajouter");
		ajouter.setMnemonic(KeyEvent.VK_A);
		ajouter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				KeyEvent.CTRL_MASK));
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame,
							"Ajout d'un joueur");
					return;
			}
		});

        JMenuItem supprimer = new JMenuItem("Supprimer");
		supprimer.setMnemonic(KeyEvent.VK_S);
		supprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));
		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame,
							"Suppression d'un joueur");
					return;
            }
		});

        patient.add(ajouter);
        patient.add(supprimer);
        bar.add(patient);
        frame.setJMenuBar(bar);
    }
}