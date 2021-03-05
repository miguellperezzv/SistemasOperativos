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
public class Multicolas {

    /**
     * @param args the command line arguments
     */
    
    static int envejecimiento=10;
    public static void main(String[] args) {
        // TODO code application logic here
         String[] nombres = {"RoundRobin", "FIFO", "SJF"};
        //HiloMulticola[] hilos = new HiloMulticola[3];
        
        
        FilaManager2 m = new FilaManager2();
        FilaManager2 m1 = new FilaManager2();
        FilaManager2 m2 = new FilaManager2();
        HiloMulticola roundrobin = new HiloMulticola("RoundRobin", m, envejecimiento,".r" );
        HiloMulticola fifo = new HiloMulticola("FIFO", m, envejecimiento, ".f");
        HiloMulticola sjf = new HiloMulticola("SJF", m, envejecimiento, ".r1");
        
        
        roundrobin.setSiguiente(fifo);
        fifo.setSiguiente(sjf);
        //sjf.setSiguiente(roundrobin);
        
        
        
        /* ESTE FUE SOLO PARA EJECUTAR LOS HILOS FIFO Y ROUND ROBIN
        roundrobin.setSiguiente(fifo);
        fifo.setSiguiente(roundrobin);
        //sjf.setSiguiente(sjf);*/
        
        Frame frame = new Frame(roundrobin, fifo, sjf);
        frame.setVisible(true);
        
        
        roundrobin.setGraphics(frame.getGraphics());
        fifo.setGraphics(frame.getGraphics());
        sjf.setGraphics(frame.getGraphics());
    }
    
}
