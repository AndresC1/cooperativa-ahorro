/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionListener;
import view.Main;
import model.MainModel;
import java.awt.event.ActionEvent;
import connection.ConnectionDB;

/**
 *
 * @author andre
 */
public class MainController implements ActionListener {
    private MainModel model;
    private Main view;
    private ConnectionDB conexion;
    
    public MainController(MainModel model, Main view){
        this.conexion = new ConnectionDB();
        this.model = model;
        this.view = view;
        this.view.btnConnection.addActionListener(this);
    }
    
    public void init(){
        view.setTitle("Cooperativa de ahorro");
        view.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e){
        view.labelStatusConnection.setText(this.conexion.statusConnection());
    }
}
