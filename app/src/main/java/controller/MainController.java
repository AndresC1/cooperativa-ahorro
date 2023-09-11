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
import java.util.List;
import java.util.Map;

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
        String consulta = "SELECT * FROM users";
        List<Map<String, Object>> resultados = this.conexion.selectQuery(consulta);

        for (Map<String, Object> fila : resultados) {
            Object nombre = fila.get("name");
            System.out.println("nombre: " + nombre);
//            for (Map.Entry<String, Object> entry : fila.entrySet()) {
//                String columnaNombre = entry.getKey();
//                Object valorColumna = entry.getValue();
//                System.out.println(columnaNombre + ": " + valorColumna);
//            }
            System.out.println();
            System.out.println("************************************************************************************");
            System.out.println();
        }
        view.labelStatusConnection.setText("consulta ejecutada correctamente...");
    }
}
