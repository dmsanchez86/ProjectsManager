package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * @author Daniel M. Sanchez
 * @siteweb http://dmsanchez86.github.io
 */
public class Conexion {
    protected Connection conection;
    protected ResultSet data;
    protected PreparedStatement query;
    
    public boolean conectToDatabase(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conection = DriverManager.getConnection("jdbc:mysql://localhost/prueba", "root", "");
            System.out.println("Conected to Database!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    public boolean insertProject(String id, String nombre, String objetivo, String alcance, String tiempo, String area, String costo){
        int affectedRows = 0;
        
        try {
            query = conection.prepareStatement("INSERT INTO proyecto VALUES(?,?,?,?,?,?,?, 'propuestos')");
            query.setString(1, id);
            query.setString(2, nombre);
            query.setString(3, objetivo);
            query.setString(4, alcance);
            query.setString(5, tiempo);
            query.setString(6, area);
            query.setString(7, costo);
            
            affectedRows = query.executeUpdate();
            
            if(affectedRows > 0){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public boolean insertFase(String idProyecto, String nombre, String fechaInicio, String fechaFin){
        int affectedRows = 0;
        
        try {
            query = conection.prepareStatement("INSERT INTO fase VALUES(?,?,?,?,?)");
            query.setString(1, null);
            query.setString(2, idProyecto);
            query.setString(3, nombre);
            query.setString(4, fechaInicio);
            query.setString(5, fechaFin);
            
            affectedRows = query.executeUpdate();
            
            if(affectedRows > 0){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
}
