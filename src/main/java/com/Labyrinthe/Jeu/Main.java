package com.Labyrinthe.Jeu;
import com.Labyrinthe.labyrinth.*;

import jdk.internal.org.objectweb.asm.tree.analysis.Frame;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;

public class Main {

    private static final long serialVersionUID = -8129013583057007422L;

	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 800;


    public  Scene scene;
    private  LabyrinthModel _labyrinthModel ;
	private  LabyrinthPainter _labyrinthPainter ;
    private boolean _painting = false;
    private  int _dureeJeu= 300; // en secondes

    enum Difficulte {FACILE, MOYENNE, DIIFICILE};
    private  Difficulte _difficulte= Difficulte.FACILE;
    private  int _width, _height; // taille du labyrynthe


    public Main(String mazeFile) {
        // Creation de la fenêtre graphique de l'application
        JFrame fenetre = new JFrame("Jeu du labyrinthe");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        fenetre.setExtendedState(fenetre.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(true);
        fenetre.setAlwaysOnTop(true);

        JPanel panneau = new JPanel();
        fenetre.setContentPane(panneau);
        //fenetre.add(panneau);
        
        //panneau.setBackground(Color.GRAY);

        createMenuBar(fenetre);
       // _labyrinthModel = new LabyrinthModel(mazeFile);
        _labyrinthModel = new LabyrinthModel(mazeFile);
        createGui(panneau, mazeFile);
        
        fenetre.setVisible(true);
        
        // Timer to repaint the labyrinth when another thread is changing it.
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!_painting && _labyrinthModel.isDirty()) {
                    _painting = true;
                    _labyrinthModel.setDirty(false);
                    _labyrinthPainter.repaint();
                    _painting = false;
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 50);
    }

    private void createGui(JPanel mainPanel, String mazeFile) {
		
		//final  mainPaneJPanell = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		_labyrinthPainter = new LabyrinthPainter(_labyrinthModel);

		_labyrinthPainter.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent event) {
				_labyrinthPainter.searchPath2(event.getPoint());
			}

			@Override
			public void mouseDragged(MouseEvent event) {
			}
		});
        mainPanel.add(_labyrinthPainter);

		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		mainPanel.setBorder(border);

		//add(mainPanel);
    }

    private  void createMenuBar(JFrame frame) {
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

        JDialog dialogParametre=createParametreDialog(frame);

		JMenuItem parametre = new JMenuItem("Parametre de Jeu");
		parametre.setMnemonic(KeyEvent.VK_P);
		parametre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				KeyEvent.CTRL_MASK));
		parametre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

                if (_labyrinthModel.isGenerating()) {
					JOptionPane.showMessageDialog(frame,
							"Wait for running generation to finish!");
					return;
				}
                
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
                
                if (_difficulte == Difficulte.FACILE) {
                    _width = _height = 5;
                } else if (_difficulte == Difficulte.MOYENNE) {
                    _width = _height = 10;
                } else if (_difficulte == Difficulte.DIIFICILE) {
                    _width = _height = 15;
                }
                Boolean slow= false;
				_labyrinthModel.generateLabyrinth(_width, _height, slow);
                scene = new Scene(_dureeJeu);
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

    private  JDialog createParametreDialog(JFrame frame) {
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
		JLabel dureeLabel = new JLabel("Durée:", JLabel.RIGHT);

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
                
                if (radioFacile.isSelected()) {
                    _difficulte=Difficulte.FACILE;
                } else if (radioMoyenne.isSelected()){
                    _difficulte=Difficulte.MOYENNE;
                } else if (radioDifficile.isSelected()){
                    _difficulte=Difficulte.MOYENNE;
                }

                if (radio1.isSelected()) {
                    _dureeJeu=300;
                } else if (radio2.isSelected()){
                    _dureeJeu=600;
                } else if (radio3.isSelected()){
                    _dureeJeu=900;
                }

				/*_labyrinthModel.generateLabyrinth(
						Integer.parseInt(widthTextfield.getText()),
						Integer.parseInt(heightTextfield.getText()),
						checkbox.isSelected());
                */
			}
		});

		difficultyPanel.add(difficultyLabel, BorderLayout.EAST);
		difficultyPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		
        difficultyPanel.add(radioFacile);
        difficultyPanel.add(radioMoyenne);
        difficultyPanel.add(radioDifficile);
        
        dureePanel.add(dureeLabel, BorderLayout.EAST);
        dureePanel.add(radio1);
        dureePanel.add(radio2);
        dureePanel.add(radio3);

		difficultyPanel.add(btnAppliquer);
        dialogParametre.getContentPane().setLayout(new FlowLayout());
		dialogParametre.getContentPane().add(difficultyPanel);
        dialogParametre.getContentPane().add(dureePanel);
		dialogParametre.setLocationRelativeTo(frame);

        return dialogParametre;
    }
}