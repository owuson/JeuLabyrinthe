package com.Labyrinthe.modele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Soignant extends Users {

    private String email;
    private String password;
    private JTextField textMail;
    private JTextField textPassword;
    private JButton saveButton;
    private JTable soignantList;
    private JButton updateButton;
    private JButton deleteButton;
    private JPanel Registration;
    private JScrollPane table_1;
    private Object DbUtils;


    public Soignant(){



    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Soignant");
        frame.setContentPane(new Soignant().Registration);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection conn;
    PreparedStatement pst;

    public void connect() throws SQLException {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Labyrinthes", "root", "16*uH2aTQn^9UJUWahZn");
    }

    void table_load(){
        try{
            pst = conn.prepareStatement("SELECT * from User");
            ResultSet rs = pst.executeQuery();
            soignantList.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (SQLException e){

        }
    }

    public Soignant(String email, String password) throws SQLException {

        connect();
        table_load();
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               String email = textMail.getText();
               String password = textPassword.getText();

               try {

                   pst = conn.prepareStatement("INSERT INTO User(email, password)VALUES (?,?) ");
                   pst.setString(1, email);
                   pst.setString(2, password);
                   pst.executeUpdate();
                   JOptionPane.showMessageDialog(null, "Soignant enregistré !");
                   //table_load();
                   textMail.setText("");
                   textPassword.setText("");
                   textMail.requestFocus();

               }catch (SQLException e1){


               }


            }

        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = textMail.getText();
                String password = textPassword.getText();

                try{
                    pst = conn.prepareStatement("UPDATE User SET email = ?, password = ?");
                    pst.setString(1, email);
                    pst.setString(2, password);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Soignant modifié !");
                    table_load();
                    textMail.setText("");
                    textPassword.setText("");
                    textMail.requestFocus();

                }catch (SQLException e1){

                    e1.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uid;
                Label textId = null;
                assert false;
                uid = textId.getText();
                try{
                    pst = conn.prepareStatement("DELETE FROM User WHERE idUser = ?");
                    pst.setString(1, uid);

                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });


    }






    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
