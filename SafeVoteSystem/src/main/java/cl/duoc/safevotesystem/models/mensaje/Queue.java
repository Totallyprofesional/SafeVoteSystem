/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem.models.mensaje;

import cl.duoc.safevotesystem.models.mensaje.MensajeThread.MensajeQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Home
 */
public class Queue implements MensajeQueue {
    private final BlockingQueue<Mensaje> queue = new LinkedBlockingQueue<>();

    @Override
    public void put(Mensaje mensaje) throws InterruptedException {
        queue.put(mensaje);
    }

    @Override 
    public Mensaje take() throws InterruptedException {
        return queue.take();
    }
     
}
