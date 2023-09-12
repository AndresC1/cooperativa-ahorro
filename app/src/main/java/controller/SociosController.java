/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import connection.ConnectionDB;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andre
 */
public class SociosController {
    public JTable table; 
    
    public SociosController(JTable table){
        this.table = table;
    }
    
    public void getSocios(){
        DefaultTableModel modelo = (DefaultTableModel) this.table.getModel();
        ConnectionDB conexion = new ConnectionDB();
        List<Map<String, Object>> listSocios = conexion.selectQuery("select * from socio");
            this.table.getModel().addTableModelListener(table);
        for (Map<String, Object> fila : listSocios) {
            Object id = fila.get("id_socio");
            Object identificacion = fila.get("Identificacion");
            Object nombre = fila.get("nombre");
            modelo.addRow(new Object[]{id, identificacion, nombre, ""});
        }
        
    }    
}
