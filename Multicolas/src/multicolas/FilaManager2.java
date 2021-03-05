/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolas;

import java.awt.Color;
import java.awt.Font;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisy
 */
class FilaManager2 {

    int coordY = 500;
    int coordX = 50;
    int semaforo = 0;
    int conteito=0;
    
    //generalidades
    int procesos_ejecutados = 0;
    int conteo=0;

    void graficar(HiloMulticola hilo) {
        
        
        
        int coordY = this.coordY;
        int cuadro = 10;
        int margen = 50;
        //int coordX = hilo.conteo * (cuadro + 2) + margen + this.coordX;
        int coordX = hilo.conteo * (cuadro + 2) + margen + this.coordX;
        
        
        Nodo temp = hilo.ultimo.siguiente;
        do {
           // this.graficarCuadricula(hilo);
           hilo.semaforo.setBackground(Color.red);
            semaforo = 0;
            if ((hilo.conteo < temp.llegadaOriginal) || hilo.conteo >= temp.tfinal) {
                if (hilo.conteo == temp.tfinal) {
                    procesos_ejecutados++;
                    //semaforo=1;
                    System.out.println("ESTOY PARADO EN EL fin DE UN PROCESO");
                    hilo.semaforo.setBackground(Color.RED);
                    try{
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FilaManager2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (hilo.conteo > hilo.envejecimiento) {
                        System.out.print("EL HILO ENVEJECIOO!!");

                         hilo.envejecimiento(hilo);
                        //siguiente
                    } else {
                        // this.graficar(hilo);
                    }

                }
            } else {

                hilo.semaforo.setBackground(Color.red);
                if (hilo.conteo < temp.tcomienzo) {

                    hilo.g.setColor(Color.ORANGE);
                    hilo.g.fillRect(coordX, coordY, cuadro, cuadro);

                } else {

                    hilo.g.setColor(Color.GREEN);
                    hilo.g.fillRect(coordX, coordY, cuadro, cuadro);

                }
            }

            temp = temp.siguiente;

            coordY += 12;

            //this.coordY +=12;
        } while (temp != hilo.ultimo.siguiente);

        hilo.conteo++;
        hilo.actualenvejecimiento++;
        conteito++;
        
        //hilo.manager.coordY=+12;
        hilo.semaforo.setBackground(Color.GREEN);
        try {
            sleep(500);  //tiempo de transacción

        } catch (InterruptedException e) {
            //
        }

        //if(hilo.conteo<hilo.envejecimiento){  segundo if abajo
        if (hilo.conteo < hilo.ultimo.tfinal) {
            this.graficar(hilo);
        } else { //se envejeció!!!! o se acabaron los procesos!!!!

            //this.setCoordY(500+ ((this.procesos_ejecutados*12))+12);
            //this.setCoordX(500+ ((this.procesos_ejecutados*12))+12);
            //hilo.siguiente.manager.procesos_ejecutados+=this.procesos_ejecutados;
            if (hilo.siguiente == null) {
                System.out.println("Se acabaron los hilos");
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(FilaManager2.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.exit(0);
            } else {
                
                this.coordY=(500 + ((this.procesos_ejecutados * 12)) + 12);
                this.coordX=(conteito* (cuadro + 2) + margen);
                //this.coordX=hilo.conteo * (cuadro + 2) + margen + this.coordX;
                /*
                hilo.siguiente.manager.procesos_ejecutados=this.procesos_ejecutados;
                //hilo.siguiente.conteo=hilo.conteo;
                hilo.siguiente.manager.coordY = (500 + ((this.procesos_ejecutados * 12)) + 12);
                hilo.siguiente.manager.coordX = (hilo.conteo * (cuadro + 2) + margen);
                System.out.println("EL SIGUIENTE HILO SU COORDENADA X QUEDÓ DE :" + hilo.siguiente.manager.coordX);
                this.graficar(hilo.siguiente);
                */
                hilo.siguiente.manager=hilo.manager;
                this.graficar(hilo.siguiente);
                
            }

        }

    }

    private void setCoordY(int coordy) {
        this.coordY = coordy;
    }

    private void setCoordX(int coordx) {
        this.coordX = coordx;
    }
    
    void graficarCuadricula(HiloMulticola hilo){
        int cuadro = 10;
        int margen = 500;

        

        Nodo temp = hilo.ultimo.siguiente;

        do {
            hilo.g.setColor(Color.black);
            hilo.g.setFont(new Font("ZapfDingbats", Font.BOLD, 11));
            hilo.g.drawString(temp.getNombre(), margen, coordY);
            coordY += 12;
            temp = temp.siguiente;
        } while (temp != hilo.ultimo.siguiente);

        int coordX = 0;
        margen = 50;
        

        /*
        for (int i = 1; i < hilo.tiempoFinal + 1; i++) {
            coordX = i * (cuadro + 2) + margen;
            hilo.g.setColor(Color.black);
            hilo.g.setFont(new Font("ZapfDingbats", Font.PLAIN, 9));
            hilo.g.drawString("" + i, coordX, coordY - 10);
        }*/
    }
}
