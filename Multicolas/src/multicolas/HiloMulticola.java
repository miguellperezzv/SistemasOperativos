/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolas;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author luisy
 */
public class HiloMulticola extends Thread {

    int envejecimiento = 0;
    int actualenvejecimiento = 0;
    Graphics g;
    String nombre = "";
    String apodo  ="";
    int tamano;
    Nodo ultimo;
    String[] nombres = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};
    int quantum = 0;
    FilaManager2 manager;
    boolean suspender;
    boolean pausar;
    HiloMulticola siguiente = null;
    JPanel panel = new JPanel();
    int prueba = 10000;
    JPanel semaforo = new JPanel();

    Nodo temp = null;

    int conteo = 0;
    int tiempoFinal = 0;

    String alert = "";

    JTextArea txtArea;

    HiloMulticola(String name, FilaManager2 m, int envejecimiento, String apodo) {
        ultimo = null;
        tamano = 0;
        this.nombre = name;
        this.manager = m;
        suspender = false;
        pausar = false;
        this.envejecimiento = envejecimiento;
        //this.panel = panel;
        this.apodo=apodo;
    }

    private HiloMulticola() {
        //To change body of generated methods, choose Tools | Templates.
        ultimo = null;
        tamano = 0;
    }

    void setSiguiente(HiloMulticola hilo) {
        this.siguiente = hilo;
    }

    public boolean vacio() {
        if (ultimo == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertar(Nodo n) {

        //String nombre = this.nombres[tamaÃ±o];
        //Nodo nuevo = new Nodo(nombre,llegada, rafaga);
        if (ultimo != null) {
            n.siguiente = ultimo.siguiente;
            ultimo.siguiente = n;
        }
        ultimo = n;
        tamano++;

    }

    public void insertar(int llegada, int rafaga) {

        String nombre = this.nombres[tamano]+this.apodo;
        Nodo nuevo = new Nodo(nombre, llegada, rafaga);
        if (ultimo != null) {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        tamano++;
    }

    public String listar() {
        Nodo aux = ultimo.siguiente;
        String cadena = "";

        if (vacio()) {
            return "No hay más clientes en la fila";
        } else {
            cadena = "Proceso \t T Llegada \t Ráfaga \t Comienzo  \t t Final \t t Retorno \t t Espera \n";
            do {

                if (aux != null) {
                    cadena = cadena + aux.nombre + "\t" + aux.llegadaOriginal + "\t" + aux.rafagaOriginal + "\t" + aux.tcomienzo + "\t" + aux.tfinal + "\t" + aux.tretorno + "\t" + aux.tespera + "\n";
                    aux = aux.siguiente;
                } else {
                    cadena += "No hay más clientes en la fila";
                }

            } while (aux != ultimo.siguiente);

            return cadena;
        }
    }

    void calcularTiempos() {
        
        
        int tiempoTotal = ultimo.siguiente.llegada;
        Nodo temp = this.ultimo.siguiente;
        int cantProcesos = 0;
        //Fila filaTemp = new Fila();

        do {

            temp.tcomienzo = tiempoTotal;
            temp.tfinal = temp.tx + temp.tcomienzo;
            temp.tretorno = temp.tfinal - temp.llegada;
            temp.tespera = temp.tretorno - temp.getTx();
            //this.ultimo.siguiente = temp;
            tiempoTotal = temp.tfinal;
            this.tiempoFinal = temp.tfinal;
            temp = temp.siguiente;
            cantProcesos++;

        } while (temp != ultimo.siguiente);

        //System.out.println("PROCESOS: " + cantProcesos);
        System.out.print("CALCULANDO TIEMPOS!! de "+this.nombre+ "\n" + this.listar());
    }

    void calcularRoundTiempos() {

        int tiempoTotal = ultimo.siguiente.llegada;
        Nodo temp = ultimo.siguiente;
        int cantProcesos = 0;
        //Fila filaTemp = new Fila();
        System.out.println("ENTRANDO A ROUNDROBIN");
        do {
            
            if (temp.getTx() > this.quantum) {
                
                temp.uso = temp.uso + 1;
                Nodo n = new Nodo(temp.nombre + "" + temp.uso, temp.llegada, (temp.getTx() - this.quantum), 0, tiempoTotal + this.quantum);
                temp.setTx(this.quantum);
                this.insertar(n);
            }
            temp.tcomienzo = tiempoTotal;
            temp.tfinal = temp.tx + temp.tcomienzo;
            temp.tretorno = temp.tfinal - temp.llegada;
            temp.tespera = temp.tretorno - temp.getTx();
            //this.ultimo.siguiente = temp;
            tiempoTotal = temp.tfinal;
            this.tiempoFinal = temp.tfinal;
            temp = temp.siguiente;
            cantProcesos++;

        } while (temp != ultimo.siguiente);

        System.out.println("PROCESOS: \n" +this.listar() );
    }

    
    void calcularTiemposSJF() {
        
        int tiempoTotal = ultimo.siguiente.llegada;
        Nodo temp = ultimo.siguiente;
        int cantProcesos = 0;
        //Fila filaTemp = new Fila();
        System.out.println("ENTRANDO A ROUNDROBIN");
        do {

            if (temp.getTx() > this.quantum) {

                temp.uso = temp.uso + 1;
                Nodo n = new Nodo(temp.nombre + "" + temp.uso, temp.llegadaOriginal, (temp.getTx() - this.quantum), 0, tiempoTotal + this.quantum);
               // Nodo n = new Nodo(temp.nombre + "" + temp.uso,temp.llegada,temp.getTx()-this.quantum);
                temp.setTx(this.quantum);
                this.insertar(n);
            }
            temp.tcomienzo = tiempoTotal;
            temp.tfinal = temp.tx + temp.tcomienzo;
            temp.tretorno = temp.tfinal - temp.llegada;
            temp.tespera = temp.tretorno - temp.getTx();
            //this.ultimo.siguiente = temp;
            tiempoTotal = temp.tfinal;
            this.tiempoFinal = temp.tfinal;
            temp = temp.siguiente;
            cantProcesos++;

        } while (temp != ultimo.siguiente);
    }
    
    /*
    void calcularTiemposSJF() {
        
        //System.out.println("THIS ENVEJECIMEINTO1!!! "+this.envejecimiento);
        HiloMulticola newHilo = new HiloMulticola("SJF", new FilaManager2(), this.envejecimiento, "f.1");

        Nodo temp = this.ultimo.siguiente;
        int limite = 0;
        do {
            Nodo menor = getMenor(limite);
            limite = menor.tx;
            newHilo.insertar(menor.nombre, menor.llegada, menor.tx);
            //this.retirar(menor);
            temp = ultimo.siguiente;
        } while (temp != ultimo.siguiente);
        
        newHilo.calcularTiempos();
        
        
        Nodo temp2 = this.ultimo.siguiente;
        do {
            this.retirar(temp2);
            temp2 = temp2.siguiente;
        } while (temp2 != ultimo.siguiente);
        
        Nodo temp3 = newHilo.ultimo.siguiente;
        do {
            this.insertar(temp3.nombre, temp3.llegada, temp3.tx);
            temp3 = temp3.siguiente;
        } while (temp2 != ultimo.siguiente);
        
        System.out.println("ORDENADITOS CON EL NUEVO METODO \n"+this.listar());
    }*/

    
    void calcularTiemposSJFObsoleto() {
        HiloMulticola newHilo = new HiloMulticola("SJF", new FilaManager2(), 20, ".r1");
        Nodo temp = this.ultimo.siguiente;
        int cant = 0;
        int tiempoTotal = ultimo.siguiente.llegada;
        //Fila filaTemp = new Fila();

        do {
            temp = temp.siguiente;
            cant++;

        } while (temp != ultimo.siguiente);

        System.out.println("PROCESOS: " + cant);

        if (this.IgualTLlegada(0).size() == 1) {
            Nodo n = this.IgualTLlegada(0).get(0);
            System.out.println("Hay un nodo con t de llegada en 0 ");
            newHilo = newHilo.insertar(n.nombre, n.llegada, n.tx);
            //this.retirar(this.ultimo.siguiente);
            System.out.println("newFila con cero al inicio \n" + newHilo.mostrarLista());
        }

        ArrayList<Nodo> arrayTemp = this.ordenPorRafaga(this.toArray());
        for (int i = 0; i < arrayTemp.size(); i++) {

            if (arrayTemp.get(i).llegada == 0 && this.IgualTLlegada(0).size() == 1) {

            } else {
                Nodo n = arrayTemp.get(i);
                newHilo = newHilo.insertar(n.nombre, n.llegada, n.tx);
                System.out.println("SE AGREGÒ EL NODO " + n.nombre);
            }

        }
        //System.out.println("newFila los q faltan \n" + newHilo.mostrarLista());

        /*
       if(this.IgualTLlegada(0).size()==1){
          newHilo.insertar(this.IgualTLlegada(0).get(0));
       }
       else if (this.IgualTLlegada(0).size()>1){
           ArrayList<Nodo> nodos = this.ordenPorRafaga(this.IgualTLlegada(0));
           for (int i = 0; i< nodos.size(); i++){
               newHilo.insertar(nodos.get(i));
           }
       }*/
        newHilo.calcularTiempos();
        System.out.println("NEWHILO ES \n" + newHilo.listar());
        //this = new HiloMulticola();

        this.ultimo.siguiente = newHilo.ultimo.siguiente;

        Nodo n = newHilo.ultimo.siguiente.siguiente;
        do {
            this.insertar(n.nombre, n.llegada, n.getTx());
            System.out.println("AGREGADO!!!");
            n = n.siguiente;
        } while (n != newHilo.ultimo.siguiente.siguiente);
        System.out.println("CALCULANDO SFJ");
        this.calcularTiempos();
    }

    void setPanel(JPanel p, JPanel s) {
        this.panel = p;
        this.semaforo = s;
    }

    public String mostrarLista() {
        Nodo aux = ultimo.siguiente;
        String cadena = "";

        if (vacio()) {
            return "No hay mÃ¡s clientes en la fila";
        } else {
            do {

                if (aux != null) {
                    cadena = cadena + aux.nombre + "\t" + aux.getTx() + "\n";
                    aux = aux.siguiente;
                } else {
                    cadena += "No hay mÃ¡s clientes en la fila";
                }

            } while (aux != ultimo.siguiente);

            return cadena;
        }

    }

    public HiloMulticola insertar(String n, int llegada, int rafaga) {

        //String nombre = this.nombres[tamaÃ±o];
        Nodo nuevo = new Nodo(n, llegada, rafaga);
        if (ultimo != null) {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        tamano++;
        return this;
    }

    ArrayList<Nodo> toArray() {

        ArrayList<Nodo> nodos = new ArrayList<>();
        Nodo temp = this.ultimo.siguiente;
        do {
            nodos.add(temp);
            temp = temp.siguiente;
        } while (temp != ultimo.siguiente);

        return nodos;
    }

    ArrayList<Nodo> IgualTLlegada(int tllegada) {

        ArrayList<Nodo> nodos = new ArrayList<>();
        Nodo temp = this.ultimo.siguiente;
        do {
            if (temp.llegada == tllegada) {
                nodos.add(temp);
            }
            temp = temp.siguiente;

        } while (temp != ultimo.siguiente);

        return nodos;
    }

    ArrayList<Nodo> ordenPorRafaga(ArrayList<Nodo> nodos) {

        int i, j;
        Nodo aux = null;
        for (i = 0; i < nodos.size() - 1; i++) {
            for (j = 0; j < nodos.size() - i - 1; j++) {
                if (nodos.get(j + 1).tx < nodos.get(j).tx) {
                    aux = nodos.get(j + 1);

                    nodos.set(j + 1, nodos.get(j));
                    nodos.set(j, aux);

                }
            }
        }
        System.out.println(nodos.get(0).tx + "es el primero y el segundo es" + nodos.get(nodos.size() - 1).tx);
        return nodos;
    }

    public void run() {

       //manager.graficarCuadricula(this);
        manager.graficar(this);
        
        
    }

    public void run2() {

        /*
        manager.entrar(this);
        //if(this.prueba>0){

        if (this.conteo <= this.tiempoFinal) {
            //manager.leave(this);
            //this.run();
            //manager.graficar(this);

            
            
                
                manager.suspender(this);
                manager.reanudar(this.siguiente);
            

        } else {
            manager.reanudar(this.siguiente);
            manager.leave(this);

        }
         */
    }

    void setGraphics(Graphics graphics) {
        this.g = graphics;
    }

    void setAlert(String s) {
        this.alert = s;
    }

    ArrayList<Nodo> getEnvejecidos() {
        ArrayList<Nodo> envejecidos = new ArrayList<>();
        Nodo temp = ultimo.siguiente;

        do {
            //System.out.println("get envejecidos \n envejecimiento"+ this.envejecimiento+"\n temp.tespera: "+(temp.tespera+this.envejecimiento));
            if (temp.tespera + this.envejecimiento > this.envejecimiento) {
                System.out.println("ENTRA!!!!");
                Nodo viejo = new Nodo(temp.getNombre(), temp.llegada, temp.getTx());
                envejecidos.add(viejo);
                retirar(viejo);
            }
            temp = temp.siguiente;
        } while (temp != ultimo.siguiente);

        return envejecidos;
    }

    void retirar(Nodo r) {

        Nodo aux = ultimo.siguiente;
        Nodo anterior = ultimo;
        do {
            if (aux.nombre.equals(r.nombre)) {
                anterior.siguiente = aux.siguiente;
                aux = aux.siguiente;
            } else {
                //temp.insertar(aux);
                anterior = aux;
                aux = aux.siguiente;
            }

        } while (aux != ultimo.siguiente);
        
        System.out.println("RETIRè EL NODO "+ r.nombre);
        System.out.println("LISTANDO "+ this.listar());
    }

    void actualizarTextArea() {
        this.txtArea.setText(this.listar());
    }

    void setTextArea(JTextArea txt) {
        this.txtArea = txt;
    }

    void envejecimiento(HiloMulticola hilo) {

        if (hilo.siguiente != null) {
            ArrayList<Nodo> nuevos = hilo.siguiente.getEnvejecidos();
            System.out.println("CANTIDAD DE ENVEJECIDOS de :  " + hilo.siguiente.nombre + " :" + nuevos.size());

            for (int i = 0; i < nuevos.size(); i++) {
                hilo.insertar(nuevos.get(i).nombre, nuevos.get(i).llegada, nuevos.get(i).getTx());
            }

            if (hilo.nombre.equals("RoundRobin")) {
                hilo.calcularRoundTiempos();
            }
            if (hilo.nombre.equals("FIFO")) {
                hilo.calcularTiempos();
            }
            if (hilo.nombre.equals("SJF")) {
                hilo.calcularTiemposSJF();
            }

            hilo.actualizarTextArea();
            hilo.siguiente.actualizarTextArea();
           // this.siguiente.envejecimiento();
            hilo.envejecimiento(this.siguiente);
        }

    }

    private Nodo getMenor(int limite) {

        Nodo temp = ultimo.siguiente;
        Nodo menor = ultimo.siguiente;
        do {
            
                if (temp.tx < menor.tx) {
                    menor = temp;
                }
                else{
                    menor = menor;
                }
            

            temp = temp.siguiente;
        } while (temp != ultimo.siguiente);
        System.out.println("EL MENOR ES" + menor.nombre);
        return menor;
    }
}
