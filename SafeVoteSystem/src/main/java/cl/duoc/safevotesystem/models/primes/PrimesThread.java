/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem.models.primes;

import cl.duoc.safevotesystem.models.mensaje.Mensaje;
import cl.duoc.safevotesystem.models.primes.PrimesList;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Home
 */
public class PrimesThread implements Runnable{
    private int numero, minimo, maximo;

    public PrimesThread(int numero, int minimo, int maximo) {
        this.numero = numero;
        this.minimo = minimo;
        this.maximo = maximo;
    } 

    public int getNumero() {
        return numero; 
    }

    @Override
    public void run() {  
        numero = minimo + (int)(Math.random() * ((maximo - minimo) + 1));     

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
    }
    
}
