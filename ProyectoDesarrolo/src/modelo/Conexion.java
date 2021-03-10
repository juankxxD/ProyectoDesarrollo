/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Juan
 */
public class Conexion {
    
    
        public static String BD = "jdbc:postgresql://localhost:5432/Proyecto";
        public static String Usuario = "postgres";
        public static String contraseña = "vegeta55";
         
        public static Connection getConexion(){
            Connection conectar = null;
         try {
            conectar = DriverManager.getConnection(BD, Usuario, contraseña);
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error my so" + e);
        }
         return conectar;
       }
        
        public ResultSet Algo(String sql){
            ResultSet rs = null;
            try {
           
             PreparedStatement pst= getConexion().prepareStatement(sql);
             rs = pst.executeQuery();
             //if(rs.next()){
              //rs.getString("ID");
              

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error my so" + e);
        }
            return rs;
        }
        
        
       public static void main(String[] args)
    {
       // String sql = "SELECT P1.\"ID\" FROM public.\"Usuario\" P1";
        String sql = "SELECT * FROM \"Usuario\"";
        try{
           
             PreparedStatement pst= getConexion().prepareStatement(sql);
             
             ResultSet rs = pst.executeQuery();
             if(rs.next()){
              System.out.println(rs.getString("ID"));
              System.out.println(rs.getString("Nombre"));
              System.out.println(rs.getString("Apellido"));
              System.out.println(rs.getString("Telefono"));
              System.out.println(rs.getString("Direccion"));
              
             }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error my so" + e);
        }
    }
    
}
