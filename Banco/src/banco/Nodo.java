/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

/**
 *
 * @author luisy
 */
public class Nodo {
    
    boolean cliente = true;
    String nombre = "";
    int tx =0;
    boolean prioridad = false;

    

    Nodo(boolean b, String nombre, int i, boolean b0) {
        
        this.cliente = b;
        this.nombre = nombre;
        this.tx = i;
        this.prioridad = b0;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTx() {
        return tx;
    }

    public void setTx(int tx) {
        this.tx = tx;
    }

    public boolean isPrioridad() {
        return prioridad;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }
    
    
    
}

