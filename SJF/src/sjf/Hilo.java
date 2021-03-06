/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author luisy
 */
public class Hilo extends Thread {

    public Fila fila = new Fila();
    JTextArea txtAreaUsuarios = new JTextArea();

    public Hilo(Fila f, JTextArea txt) {
        this.fila = f;
        this.txtAreaUsuarios = txt;

    }

    public void run() {

        boolean fin = false;

        while (fin != true) {
            String info = "";

            if (fila.vacio()) {
                System.out.println("Saliendo del hilo con comporobacion de vacio");
                JOptionPane.showMessageDialog(null, "No hay más clientes en la fila!");
                fin = true;
                info = "****Personas en la fila  *** \n"
                        + "NOMBRE \t TRANSACCIONES  \n";

                // f.actualizarTxtUsuarios(info);
                txtAreaUsuarios.setText(info);

            } else {

                while (fila.vacio() == false) {

                    try {
                        sleep(1000);
                        System.out.println("durmiendo ");
                    } catch (InterruptedException ex) {
                        System.out.println("No se pudo dormir " + ex);
                    }
                    txtAreaUsuarios.setText(fila.mostrarLista());
                    //System.out.println("ATENDIDO!! "+fila.mostrarLista());
                    //Fila temp = fila.atender(info);

                    //fila = temp;
                    if (fila != null) {
                        fila = fila.atender(info);
                    } else {
                        fin =true;
                        System.out.println("Saliendo del hilo");
                    }

                }

            }
        }

        //f.actualizarTextArea();
        //f.actualizarlbl_info(info);
        //lbl_info.setText(info);
        //fila.mostrarLista();
    }
}
