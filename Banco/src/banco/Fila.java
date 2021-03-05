/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author luisy
 */
public class Fila {

    Queue<Nodo> fila = new LinkedList<>();

    Fila() {
        //fila.add(this.crearCajero());
    }

    Nodo crearCajero() {
        Nodo cajero = new Nodo(false, "Cajero", 0, false);
        return cajero;
    }

    String agregar(Nodo n) {

        String info = "";
        if (n.prioridad == true) {
            this.ponerAdelante(n);
            info = n.getNombre() + " ubicado adelante";
        } else {
            info = "Persona agregada";
            fila.add(n);
        }
        return info;
    }

    void ponerAdelante(Nodo n) {
        System.out.println("entrando a poner adelante");
        Queue<Nodo> temp = new LinkedList<>();

        temp.add(n);
        temp.addAll(fila);
        fila = temp;
        //temp.
    }

    void imprimir() {
        Iterator it = fila.iterator();
        while (it.hasNext()) {
            Nodo n = (Nodo) it.next();
            System.out.println(n.nombre + " Prioridad: " + n.prioridad + ", txs: " + n.getTx());
        }
    }

    String atender() {

        String info = "";
        if (fila.peek().getTx() >= 3) {
            fila.peek().tx -= 3;
            info = fila.peek().getNombre() + "con más de 3 Tx, vuelve a la cola";
            fila.add(fila.poll());
        } else {
            do {
                fila.peek().tx -= 1;
            } while (fila.peek().getTx() == 0);

            info = fila.peek().getNombre() + "Atendida, sale de la cola";
            fila.remove();
        }
        return info;
    }

}
