/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;

import java.util.ArrayList;

/**
 *
 * @author luisy
 */
public class Fila {

    int tamaño;
    Nodo ultimo;
    String[] nombres = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    

    public Fila() {
        ultimo = null;
        tamaño=0;
    }

    public boolean vacio() {
        if (ultimo == null) {
            return true;
        } else {
            return false;
        }
    }

    public Fila insertar(String nombre, int tx, boolean prioridad) {
        Nodo nuevo = new Nodo(nombre, tx, prioridad);
        if (ultimo != null) {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        return this;
    }
    
    public Fila insertar(int llegada, int rafaga){
        
        String nombre = this.nombres[tamaño];
        Nodo nuevo = new Nodo(nombre,llegada, rafaga);
        if (ultimo != null) {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        tamaño++;
        return this;
    }
    
    public Fila insertar(Nodo n){
        
        //String nombre = this.nombres[tamaño];
        //Nodo nuevo = new Nodo(nombre,llegada, rafaga);
        if (ultimo != null) {
            n.siguiente = ultimo.siguiente;
            ultimo.siguiente = n;
        }
        ultimo = n;
        tamaño++;
        return this;
    }

    public String mostrarLista() {
        Nodo aux = ultimo.siguiente;
        String cadena = "";

        if (vacio()) {
            return "No hay más clientes en la fila";
        } else {
            do {

                if (aux != null) {
                    cadena = cadena + aux.nombre + "\t" + aux.getTx() + "\n";
                    aux = aux.siguiente;
                }
                else{
                    cadena +="No hay más clientes en la fila";
                }

            } while (aux != ultimo.siguiente);

            return cadena;
        }

    }
    
    public String listar(){
        Nodo aux = ultimo.siguiente;
        String cadena = "";

        if (vacio()) {
            return "No hay más clientes en la fila";
        } else {
            cadena = "Proceso \t T Llegada \t Ráfaga \t Comienzo \t t Final \t t Retorno \t t Espera \n";
            do {

                if (aux != null) {
                    cadena = cadena + aux.nombre + "\t" + aux.getLlegada() + "\t"+ aux.getTx()+ "\t"+ aux.tcomienzo+"\t"+aux.tfinal+"\t" +aux.tretorno +"\t"+aux.tespera +"\n";
                    aux = aux.siguiente;
                }
                else{
                    cadena +="No hay más clientes en la fila";
                }

            } while (aux != ultimo.siguiente);

            return cadena;
        }
    }

    public Fila ponerAdelante(Nodo n) {
        System.out.println("entrando a poner adelante");
        Fila temp = new Fila();

        temp.insertar(n.nombre, n.getTx(), n.prioridad);
        Nodo aux = ultimo.siguiente;

        do {
            temp.insertar(aux.getNombre(), aux.getTx(), aux.prioridad);
            aux = aux.siguiente;
        } while (aux != ultimo.siguiente);
        return temp;
    }

    public Fila ponerDetras() {
        System.out.println("entrando a poner atrás");
        Fila temp = new Fila();

        Nodo aux = ultimo.siguiente;

        do {
            aux = aux.siguiente;
            temp.insertar(aux.getNombre(), aux.getTx(), aux.prioridad);
            //aux=aux.siguiente;
        } while (aux != ultimo.siguiente);
        //temp.insertar(n.nombre, n.getTx(), n.prioridad);
        return temp;
    }

    public Fila atender(String info) {
        Fila temp = new Fila();

        if (ultimo.siguiente == null) {
            return temp;
        } else {
            if (ultimo.siguiente.getTx() > 3) {
                ultimo.siguiente.tx -= 3;
                temp = this.ponerDetras();

            } else {
                if (ultimo.siguiente == ultimo.siguiente.siguiente) {
                    ultimo.siguiente = null;
                } else {
                    ultimo.siguiente = ultimo.siguiente.siguiente;
                }

                temp = this;
            }
            return temp;
        }

    }

    private int getLength() {
        int size=0;
        if(this.vacio()){
            return size;
        }
        else{
            Nodo temp = this.ultimo.siguiente;
            size++;
            while(temp!=null){
                size++;
            }
            return size;
        }
        
    }

    void calcularTiempos() {
        
        int tiempoTotal=ultimo.siguiente.llegada;
        Nodo temp = this.ultimo.siguiente;
        int cantProcesos =0;
        //Fila filaTemp = new Fila();
        
        do{
           
           temp.tcomienzo = tiempoTotal;
           temp.tfinal=temp.tx+temp.tcomienzo;
           temp.tretorno = temp.tfinal-temp.llegada;
           temp.tespera = temp.tretorno-temp.getTx();
           //this.ultimo.siguiente = temp;
           tiempoTotal = temp.tfinal;
           temp = temp.siguiente;
           cantProcesos++;
           
        }while (temp != ultimo.siguiente);
        
       System.out.println("PROCESOS: " + cantProcesos);
        
    }

    
    Fila calcularTiemposSJF() {
        Fila newFila = new Fila();
        Nodo temp = this.ultimo.siguiente;
        int cant =0;
        int tiempoTotal=ultimo.siguiente.llegada;
        //Fila filaTemp = new Fila();
        
        do{
           temp = temp.siguiente;
           cant++;
           
        }while (temp != ultimo.siguiente);
        
       System.out.println("PROCESOS: " + cant);
        
       if(this.IgualTLlegada(0).size()==1){
           Nodo n = this.IgualTLlegada(0).get(0);
           System.out.println("Hay un nodo con t de llegada en 0 ");
           newFila = newFila.insertar(n.nombre, n.llegada, n.tx );
           //this.retirar(this.ultimo.siguiente);
           System.out.println("newFila con cero al inicio \n"+ newFila.mostrarLista());
       }
       
       
       ArrayList<Nodo> arrayTemp = this.ordenPorRafaga(this.toArray());
       for(int i =0 ; i<arrayTemp.size(); i++){
           
           if(arrayTemp.get(i).llegada==0 && this.IgualTLlegada(0).size()==1){
               
           }
           else{
               Nodo n = arrayTemp.get(i);
               newFila = newFila.insertar(n.nombre, n.llegada, n.tx);
           }
           
       }
       System.out.println("newFila los q faltan \n"+ newFila.mostrarLista());
       /*
       if(this.IgualTLlegada(0).size()==1){
          newFila.insertar(this.IgualTLlegada(0).get(0));
       }
       else if (this.IgualTLlegada(0).size()>1){
           ArrayList<Nodo> nodos = this.ordenPorRafaga(this.IgualTLlegada(0));
           for (int i = 0; i< nodos.size(); i++){
               newFila.insertar(nodos.get(i));
           }
       }*/
       
       return newFila;
       
    }
    
    
    ArrayList<Nodo> IgualTLlegada(int tllegada){
        
        ArrayList<Nodo> nodos = new ArrayList<>();
        Nodo temp = this.ultimo.siguiente;
        do{
           if(temp.llegada == tllegada){
               nodos.add(temp);
           }
           temp = temp.siguiente;
           
           
        }while (temp != ultimo.siguiente);
        
        return nodos;
    }
    
    ArrayList<Nodo> ordenPorRafaga(ArrayList<Nodo> nodos){
        
        
       int i, j;
       Nodo aux=null;
       for (i = 0; i < nodos.size() - 1; i++) {
            for (j = 0; j < nodos.size() - i - 1; j++) {
                if (nodos.get(j + 1).tx < nodos.get(j).tx) {
                    aux = nodos.get(j + 1);
                    
                    nodos.set(j+1, nodos.get(j));
                    nodos.set(j, aux);
                    
                }
            }
        }
        
        return nodos;
    }
    
    ArrayList<Nodo> toArray(){
        
       ArrayList<Nodo> nodos = new ArrayList<>();
       Nodo temp = this.ultimo.siguiente;
        do{
         nodos.add(temp);
         temp = temp.siguiente; 
        }while (temp != ultimo.siguiente);
       
       
        return nodos;
    }

    private void retirar(Nodo n) {
        
        Fila temp =new Fila();
        Nodo aux = ultimo.siguiente; 
        Nodo anterior = ultimo;
        do{
            if(aux.nombre.equals(n.nombre)){
                anterior.siguiente = aux.siguiente;
                aux = aux.siguiente;
            }
            else{
                //temp.insertar(aux);
                anterior = aux;
                aux = aux.siguiente;
            }
            
        }while (aux != ultimo.siguiente);
        
        
    }

    
        
    public Fila insertar(String n, int llegada, int rafaga){
        
        //String nombre = this.nombres[tamaño];
        Nodo nuevo = new Nodo(n,llegada, rafaga);
        if (ultimo != null) {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        tamaño++;
        return this;
    }
    
    
   
}
