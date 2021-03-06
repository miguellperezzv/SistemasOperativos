/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolas.retroalimentadas;

/**
 *
 * @author luisy
 */
public class MulticolasRetroalimentadas {

    /**
     * @param args the command line arguments
     */
    
    static int envejecimiento=5;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        String[] nombres = {"RoundRobin", "FIFO", "SJF"};
        //HiloMulticola[] hilos = new HiloMulticola[3];
        
        
        FilaManager m = new FilaManager();
        HiloMulticola roundrobin = new HiloMulticola("RoundRobin", m, envejecimiento);
        HiloMulticola fifo = new HiloMulticola("FIFO", m, envejecimiento);
        HiloMulticola sjf = new HiloMulticola("SJF", m, envejecimiento);
        
        
        roundrobin.setSiguiente(fifo);
        fifo.setSiguiente(sjf);
        sjf.setSiguiente(roundrobin);
        
        
        
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
