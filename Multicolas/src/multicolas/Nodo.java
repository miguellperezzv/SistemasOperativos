/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolas;

/**
 *
 * @author luisy
 */
public class Nodo {
    String nombre = "";
    int tx =0;
    int prioridad = 0;
    Nodo siguiente; 
    boolean bloqueado = false;
    int rafagaOriginal=0;
    int llegadaOriginal=0;
    
    //Prueba nombre por uso
    int uso=0;
    
    //Referente al algoritmo de planificaciÃ³n
    int llegada=0;
    int tcomienzo=0;
    int tfinal =0;
    int tretorno=0;
    int tespera=0;
    
    
    public Nodo(String nombre, int llegada, int rafaga){
        this.nombre = nombre;
        this.tx = rafaga;
        this.prioridad = 0;
        this.rafagaOriginal=rafaga;
        this.llegada =llegada;
        this.llegadaOriginal=llegada;
        siguiente = this;
    }
    
    /*
    public Nodo(String nombre, int i, int prioridad){
        this.nombre = nombre;
        this.tx = i;
        this.prioridad = prioridad;
        this.rafagaOriginal=i;
        siguiente = this;
        
    }*/
    
    public Nodo(String nombre,int llegada,int i, int prioridad){
        
        this.nombre = nombre;
        this.tx = i;
        this.llegadaOriginal = llegada;
        this.llegada = llegada;
        this.prioridad = prioridad;
        this.rafagaOriginal=i;
        siguiente = this;
        
    }
    
    public Nodo(String nombre,int llegadaOriginal,int i, int prioridad, int llegada){
        
        this.nombre = nombre;
        this.tx = i;
        this.llegadaOriginal = llegadaOriginal;
        this.llegada  =llegada;
        this.prioridad = prioridad;
        this.rafagaOriginal=i;
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
