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
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    public ResultSet getProjects(){
        try {
            query = conection.prepareStatement("SELECT p.id, p.nombre,COUNT(f.idProyecto) as fases FROM proyecto p LEFT JOIN fase f ON p.id = f.idProyecto GROUP BY p.id ORDER BY p.nombre");
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultSet getFasesByProject(String idProject){
        try {
            query = conection.prepareStatement("SELECT * FROM fase WHERE idProyecto = ?");
            query.setString(1, idProject);
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultSet getTeamByFase(String idFase){
        try {
            query = conection.prepareStatement("SELECT * FROM responsables WHERE idFase = ?");
            query.setString(1, idFase);
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultSet getEntregableByFase(String idFase){
        try {
            query = conection.prepareStatement("SELECT * FROM entregables WHERE idFase = ?");
            query.setString(1, idFase);
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
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
    
    public boolean insertFase(String idProyecto, String nombre, String responsable, String fechaInicio, String fechaFin){
        int affectedRows = 0;
        
        try {
            query = conection.prepareStatement("INSERT INTO fase VALUES(?,?,?,?,?,?)");
            query.setString(1, null);
            query.setString(2, idProyecto);
            query.setString(3, nombre);
            query.setString(4, responsable);
            query.setString(5, fechaInicio);
            query.setString(6, fechaFin);
            
            affectedRows = query.executeUpdate();
            
            if(affectedRows > 0){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
 
    public boolean insertMemberTeam(String idFase, String nombre, String rol){
        int affectedRows = 0;
        
        try {
            query = conection.prepareStatement("INSERT INTO responsables VALUES(?,?,?)");
            query.setString(1, idFase);
            query.setString(2, nombre);
            query.setString(3, rol);
            
            affectedRows = query.executeUpdate();
            
            if(affectedRows > 0){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    public boolean insertEntregable(String idFase, String nombre, String tipo, String fechaInicio, String fechaFin){
        int affectedRows = 0;
        
        try {
            query = conection.prepareStatement("INSERT INTO entregables VALUES(?,?,?,?,?)");
            query.setString(1, idFase);
            query.setString(2, nombre);
            query.setString(3, tipo);
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
