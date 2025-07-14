/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem.models.primes;

import cl.duoc.safevotesystem.models.primes.PrimesList;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Home
 */
public class PrimesThread implements Runnable{
    private PrimesList primesList;
    private BlockingQueue<Integer> queue;
    private int minimo; 
    private int maximo;
    private int n;

    public PrimesThread(PrimesList primesList, BlockingQueue<Integer> queue, int minimo, int maximo, int n) {
        this.primesList = primesList;
        this.queue = queue;
        this.minimo = minimo;
        this.maximo = maximo;
        this.n = n;
    }

     @Override
    public void run() {  
        primesList = new PrimesList();
         
        for (int i = 0; i < n; i++) {
            int numero = minimo + (int)(Math.random() * ((maximo - minimo) + 1));   
                          
            if(primesList.isPrime(numero)){
                primesList.add(numero); 
            }else {
                System.out.println("Numero ingresado no es primo");
            } 
        }
          
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
    }
    
    public PrimesList getPrimes() {
        return primesList;
    }
    
}
