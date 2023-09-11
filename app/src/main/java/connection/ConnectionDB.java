/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import connection.CredentialDB;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andre
 */
public class ConnectionDB {
    private String url;
    private String user;
    private String password;
    private Connection conexion;
    
    public ConnectionDB(){
        CredentialDB credenciales = new CredentialDB();
        this.url = credenciales.getUrl();
        this.user = credenciales.getUser();
        this.password = credenciales.getPassword();
    }
    
    public Connection openConnection(){
        try {
            if (conexion == null || conexion.isClosed()) {
                // Si la conexión es nula o está cerrada, la abrimos nuevamente
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión exitosa a la base de datos");
            }
            return conexion;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos");
        }
    }
    
    public void closeConnection(){
        if(conexion != null){
            try{
                conexion.close();
                System.out.println("Conexion cerrada");
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    public List<Map<String, Object>> selectQuery(String consulta){
        List<Map<String, Object>> resultados = new ArrayList<>();

        try {
            this.openConnection();
            Statement statement = this.conexion.createStatement();
            ResultSet result = statement.executeQuery(consulta);

            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (result.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnaNombre = metaData.getColumnName(i);
                    Object valorColumna = result.getObject(i);
                    fila.put(columnaNombre, valorColumna);
                }
                resultados.add(fila);
            }
            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }
    
    public void insertQuery(String consulta) {
        try {
            this.openConnection();
            Statement statement = this.conexion.createStatement();
            statement.executeUpdate(consulta);
            this.closeConnection();
            System.out.println("Datos insertados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar datos: " + e.getMessage());
        }
    }
}
