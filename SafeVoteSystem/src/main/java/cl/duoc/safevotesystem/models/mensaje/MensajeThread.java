/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem.models.mensaje;

/**
 *
 * @author Home
 */
public class MensajeThread implements Runnable{
    private final MensajeQueue queue;

    public MensajeThread(MensajeQueue queue) {
        this.queue = queue;
    }

    @Override 
    public void run() {
        while (true) {
            try {
                Mensaje mensaje = queue.take();
                mensaje.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    } 
    
    public interface MensajeQueue {
        void put(Mensaje mensaje) throws InterruptedException;
        Mensaje take() throws InterruptedException;
    }
}
 