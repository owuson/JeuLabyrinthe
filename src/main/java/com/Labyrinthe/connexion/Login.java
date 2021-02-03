package com.Labyrinthe.connexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Login implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Connexion");
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JLabel userlabel = new JLabel("Pseudo : ");
    JLabel passwordlabel = new JLabel("Mot de passe : ");
    HashMap<String, String> logininfo = new HashMap<String, String>();

    Login(HashMap<String, String> loginInfoOriginal){

        logininfo = loginInfoOriginal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
