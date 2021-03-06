/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;

/**
 *
 * @author luisy
 */
public class Nodo {
    
    String nombre = "";
    int tx =0;
    boolean prioridad = false;
    Nodo siguiente; 
    
    //Referente al algoritmo de planificación
    int llegada=0;
    int tcomienzo=0;
    int tfinal =0;
    int tretorno=0;
    int tespera=0;
    
    public Nodo(String nombre, int i, boolean b0){
        this.nombre = nombre;
        this.tx = i;
        this.prioridad = b0;
        siguiente = this;
        
    }
    
    public Nodo(String nombre,int llegada,int i){
        
        this.nombre = nombre;
        this.tx = i;
        this.llegada = llegada;
        siguiente = this;
        
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

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public int getLlegada() {
        return llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }
}
