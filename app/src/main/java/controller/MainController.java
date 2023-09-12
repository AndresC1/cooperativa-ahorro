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
import view.Dashboard;

/**
 *
 * @author andre
 */
public class MainController implements ActionListener {
    private MainModel model;
    private Main view;
    private ConnectionDB conexion;
    private Dashboard dashboard;
    
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
        String name = view.loginNombre.getText();
        String password = view.loginContra.getText();
        String consulta = "SELECT * FROM `usuario` as a inner join empleado as b on a.id_empleado=b.id_empleado where b.nombre='"+name+"' AND a.clave='"+password+"';";
        List<Map<String, Object>> resultados = this.conexion.selectQuery(consulta);
        System.out.println("Consulta vacia: "+resultados.isEmpty());
        
        if(!resultados.isEmpty()){
            for (Map<String, Object> fila : resultados) {
                System.out.println();
                System.out.println("************************************************************************************");
                System.out.println("********************************** Login exitoso ***********************************");
                System.out.println("************************************************************************************");
                System.out.println();
                for (Map.Entry<String, Object> entry : fila.entrySet()) {
                    String columnaNombre = entry.getKey();
                    Object valorColumna = entry.getValue();
                    System.out.println(columnaNombre + ": " + valorColumna);
                }
                System.out.println();
                System.out.println("************************************************************************************");
                System.out.println();
                view.statusLogin.setForeground(new java.awt.Color(25, 218, 26));
                view.statusLogin.setText("Login realizado correctamente");
                this.dashboard = new Dashboard();
                this.dashboard.setTitle("Cooperativa de ahorro");
                this.dashboard.setLocationRelativeTo(null);
                this.dashboard.setVisible(true);
                view.setVisible(false);
            }
        }else{
            view.statusLogin.setText("Credenciales incorrectas");
        }
    }
}
