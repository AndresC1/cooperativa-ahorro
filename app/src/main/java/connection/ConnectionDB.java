/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import connection.CredentialDB;

/**
 *
 * @author andre
 */
public class ConnectionDB {
    private String url;
    private String user;
    private String password;
    
    public ConnectionDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Controlador cargado correctamente");
        } catch(ClassNotFoundException e){
            System.out.println("Error al cargar el controlador");
        }
        CredentialDB credenciales = new CredentialDB();
        this.url = credenciales.getUrl();
        this.user = credenciales.getUser();
        this.password = credenciales.getPassword();
    }
    
    public String statusConnection(){
        try{
            Connection conexion = DriverManager.getConnection(url, user, password);
            
            conexion.close();
            return "Conexion establecida correctamente...";
        } catch(SQLException e){
            return "Error al conectar la base de datos: " + e.getMessage();
        }
    }
}
