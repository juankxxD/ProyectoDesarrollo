/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Conexion;

/**
 *
 * @author Juan
 */
public class Administracion {
   private static int porcentajeTraba;
   private static int descuento;

    public static int getDescuento() {
        return descuento;
    }

    public static void setDescuento(int descuento) {
        Administracion.descuento = descuento;
    }

   public Administracion(){
       
   }
   public int cambiarPorcentajes(){
       String entrada= JOptionPane.showInputDialog("Ingrese el numero sin el signo de porcentaje a cambiar");
       porcentajeTraba=Integer.valueOf(entrada);
       return porcentajeTraba;
   } 

    public int getPorcentajeTraba() {
        return porcentajeTraba;
    }

    public void setPorcentajeTraba(int porcentajeTraba) {
        this.porcentajeTraba = porcentajeTraba;
    }
  public void Cambiarcomision(){
      try{
          Vendedor v = new Vendedor();
          porcentajeTraba = v.getComision() + 5000;
         String SQL = "UPDATE \"Vendedores\"\n" +
"				 SET \"Comision\" = ?\n" +
"				 WHERE \"ID\" = '" + v.getID().trim() +"'";
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        v.setComision(porcentajeTraba);
        PreparedStatement ps= con.prepareStatement(SQL);
        ps.setInt(1,porcentajeTraba);
        ps.executeUpdate();
        
       // ResultSet rs= ps.executeQuery();
       } catch(SQLException ex){
       JOptionPane.showMessageDialog(null, ex);
    }
  } 
}
