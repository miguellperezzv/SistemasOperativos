/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolas.retroalimentadas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static java.lang.Thread.sleep;

/**
 *
 * @author luisy
 */
public class FilaManager {

    int semaforo = 0;

    //HiloMulticola[] hilos = new HiloMulticola[3];
    FilaManager() {

    }

    public synchronized void pausar(HiloMulticola hilo) {
        hilo.pausar = true;
        hilo.suspender = false;
        //System.out.println(hilo.nombre+"pausado!!!!!!!!");
        notify();
    }

    public synchronized void suspender(HiloMulticola hilo) {
        //hilo.pausar=true;
        hilo.suspender = true;
        hilo.semaforo.setBackground(Color.red);
        //System.out.println(hilo.nombre+" suspendido!!!!!!!!");
        //notify();
    }

    public synchronized void reanudar(HiloMulticola hilo) {
        //hilo.pausar=true;
        hilo.suspender = false;
        //System.out.println(hilo.nombre+" reanudado!!!!!!!!");

        if (hilo.conteo >= hilo.tiempoFinal) {
            this.entrar(hilo.siguiente);
        } else if (hilo.actualenvejecimiento == hilo.envejecimiento) {
            this.entrar(hilo.siguiente);
        } else {

            this.entrar(hilo);
        }

        notify();

    }

    public synchronized void entrar(HiloMulticola hilo) {

        while (semaforo == 1) {
            try {
                System.out.println(hilo.nombre + " en espera");

                wait();
            } catch (InterruptedException e) {
                //
            }
        }
        //precondition is true

        System.out.println(hilo.nombre + ", con " + hilo.conteo + " / " + hilo.tiempoFinal + "seg  està en ejecuciòn");

        /*graficar
     ...
    ... HACER EL DO
         */
        this.graficarCuadricula(hilo);
        this.graficar(hilo);

        hilo.conteo++;
        hilo.actualenvejecimiento++;
        hilo.semaforo.setBackground(Color.GREEN);
        try {
            sleep(500);  //tiempo de transacción

        } catch (InterruptedException e) {
            //
        }
        if (hilo.conteo < hilo.tiempoFinal) {
            //if(hilo.prueba>=0){

            /*
      asi funciona!!!! 
         this.suspender(hilo);
            this.reanudar(hilo.siguiente);
            semaforo=0;
            
    
             */
 /*VOY A PROBAR EL ENVEJECIMIENTO*/
            if (hilo.actualenvejecimiento == hilo.envejecimiento) {
                //System.out.println("ENVEJECIDO!!! : "+hilo.actualenvejecimiento);
                hilo.actualenvejecimiento = 0;
                hilo.setAlert("");
                semaforo = 0;
                this.suspender(hilo);
                this.reanudar(hilo.siguiente);

                //System.out.println("ENVEJECIDO!!! : "+hilo.actualenvejecimiento);
            } 
            
            else if (!hilo.alert.isEmpty()) {
                System.out.print("ALERT!!!!!!!! INGRESO DE " + hilo.alert);
                String enquehiloestoy = hilo.nombre;
                HiloMulticola actual = hilo;
                do {
                   
                    enquehiloestoy = hilo.siguiente.nombre;
                    actual = hilo.siguiente;
                } while (enquehiloestoy.equals(hilo.alert));
                
                hilo.actualenvejecimiento = 0;
                actual.actualenvejecimiento=0;
                
                hilo.setAlert("");
                
                //ultima prueba
                
                
                
                semaforo = 0;
                this.suspender(hilo);
                this.reanudar(actual);
            } 
            
            
            else {

                System.out.println("NO ENVEJECIDO!!! : " + hilo.actualenvejecimiento);
                this.suspender(hilo);
                this.reanudar(hilo);
                //hilo.actualenvejecimiento++;
            }
        } //prueba
        else {
            this.leave(hilo);
            //this.reanudar(hilo.siguiente);
        }

        //semaforo=1;
    }

    public synchronized void leave(HiloMulticola hilo) {
        semaforo = 0;

        System.out.println(hilo.nombre + " is left");

        notify();
        //this.notifyAll();

    }

    void graficar(HiloMulticola hilo) {

        int coordY = 0;
        int cuadro = 10;
        int margen = 480;
        int coordX = hilo.conteo * (cuadro + 2) + margen;

        if (hilo.nombre.equals("RoundRobin")) {
            coordY = 260;
        }
        if (hilo.nombre.equals("FIFO")) {
            coordY = 480;
        }
        if (hilo.nombre.equals("SJF")) {
            coordY = 660;
        }

        Nodo temp = hilo.ultimo.siguiente;
        do {
            if ((hilo.conteo < temp.llegadaOriginal) || hilo.conteo >= temp.tfinal) {

            } else {

                if (hilo.conteo < temp.tcomienzo) {

                    hilo.g.setColor(Color.ORANGE);
                    hilo.g.fillRect(coordX, coordY, cuadro, cuadro);

                } else {

                    hilo.g.setColor(Color.GREEN);
                    hilo.g.fillRect(coordX, coordY, cuadro, cuadro);

                }
            }
            /*
            
            ..........mi prueba 
            if(hilo.conteo<temp.tcomienzo){
                
            }else if(hilo.conteo==temp.llegada){
                hilo.g.setColor(Color.ORANGE);
            }
            else if(hilo.conteo>temp.llegada && hilo.conteo<temp.tcomienzo){
                hilo.g.setColor(Color.ORANGE);
                hilo.g.fillRect(coordX, coordY, cuadro, cuadro);  // CUADDRO NARANJA DE ESPERA
            }
            else if(hilo.conteo==temp.tcomienzo){
                hilo.g.setColor(Color.GREEN);
                hilo.g.fillRect(coordX, coordY, cuadro, cuadro); //cambio a cuadro verde
            }
            else if(hilo.conteo>temp.tcomienzo){
                hilo.g.setColor(Color.GREEN);
                hilo.g.fillRect(coordX, coordY, cuadro, cuadro);  //cuadro verde
            }
            else if(hilo.conteo>temp.tfinal){
                
            }*/
            temp = temp.siguiente;
            coordY += 12;
        } while (temp != hilo.ultimo.siguiente);

    }

    void graficarCuadricula(HiloMulticola hilo) {

        int coordY = 0;
        int cuadro = 10;
        int margen = 460;

        if (hilo.nombre.equals("RoundRobin")) {
            coordY = 260 + cuadro;
        }
        if (hilo.nombre.equals("FIFO")) {
            coordY = 480 + cuadro;
        }
        if (hilo.nombre.equals("SJF")) {
            coordY = 660 + cuadro;
        }

        Nodo temp = hilo.ultimo.siguiente;

        do {
            hilo.g.setColor(Color.black);
            hilo.g.setFont(new Font("ZapfDingbats", Font.BOLD, 11));
            hilo.g.drawString(temp.getNombre(), margen, coordY);
            coordY += 12;
            temp = temp.siguiente;
        } while (temp != hilo.ultimo.siguiente);

        int coordX = 0;
        margen = 470;
        if (hilo.nombre.equals("RoundRobin")) {
            coordY = 260 + cuadro;
        }
        if (hilo.nombre.equals("FIFO")) {
            coordY = 480 + cuadro;
        }
        if (hilo.nombre.equals("SJF")) {
            coordY = 660 + cuadro;
        }

        for (int i = 1; i < hilo.tiempoFinal + 1; i++) {
            coordX = i * (cuadro + 2) + margen;
            hilo.g.setColor(Color.black);
            hilo.g.setFont(new Font("ZapfDingbats", Font.PLAIN, 9));
            hilo.g.drawString("" + i, coordX, coordY - 10);
        }
    }
}
