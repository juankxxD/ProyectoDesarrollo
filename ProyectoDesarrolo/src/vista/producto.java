/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Daniela
 */
public class producto {
    private String Nombre;
    private String  codigo;
    private int valor;
    private int ValorDescuento;
    private String FechaVencimiento;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValorDescuento() {
        return ValorDescuento;
    }

    public void setValorDescuento(int ValorDescuento) {
        this.ValorDescuento = ValorDescuento;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public producto(String Nombre, String codigo, int valor, int ValorDescuento, String FechaVencimiento) {
        this.Nombre = Nombre;
        this.codigo = codigo;
        this.valor = valor;
        this.ValorDescuento = ValorDescuento;
        this.FechaVencimiento = FechaVencimiento;
    }
            
}
