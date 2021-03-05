/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco2;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisy
 */
public class HiloPlanif extends Thread {

    Graphics g;
    Fila f;

    public HiloPlanif(Graphics g, Fila f) {
        this.g = g;
        this.f = f;
    }

    public void run() {

        Nodo temp = f.ultimo.siguiente;
        int factor = 1000 / f.ultimo.tfinal;
        System.out.println("factor " + factor);
        int x = 10;
        int y = 500;
        do {
            g.drawString(temp.getNombre(), x, y);
            //g.drawString("Hola ", x, y);
            y += 30;
            temp = temp.siguiente;
        } while (temp != f.ultimo.siguiente);

        x = 40;
        y = 450;
        for (int i = 0; i < f.ultimo.tfinal+1; i++) {
            try {
                //g.drawString("Hola", 500,500);
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloPlanif.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.drawString(i + "-", x, y);

            temp = f.ultimo.siguiente;
            y += 50;
            do {
                if ((i<temp.llegada) || i>=temp.tfinal) {
                    //g.drawString("n", x+factor, y);
                } else {
                    if (i<temp.tcomienzo) {
                        //g.drawString(".", x+factor, y);
                        g.drawLine(x, y, x+factor-(int)(factor*0.5), y);
                    } else {
                        //g.drawString("_", x+factor, y);
                        g.drawLine(x, y, x+factor, y);
                    }
                }
                y += 30;
                temp=temp.siguiente;
                //x=40+factor;
            } while (temp != f.ultimo.siguiente);
            y = 450;
            x += factor;
        }

    }

}
