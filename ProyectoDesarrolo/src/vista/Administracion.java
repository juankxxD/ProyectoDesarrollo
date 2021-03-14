/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JOptionPane;

/**
 *
 * @author Juan
 */
public class Administracion {
   private int porcentajeTraba;
   private String usuario = "adm";

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
    public String getUsuario() {
        return usuario;
    }
}
