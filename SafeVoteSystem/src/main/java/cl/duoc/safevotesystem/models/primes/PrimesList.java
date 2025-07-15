/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem.models.primes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** 
 *
 * @author Home
 */
public class PrimesList extends ArrayList<Integer> {
     
    public boolean isPrime(int numero) {  
        try {  
            if (numero <= 1) return false;
            if (numero <= 3) return true;
            if (numero % 2 == 0 || numero % 3 == 0) return false;
         
            // Desde 25 
            for (int i = 5; i * i <= numero; i += 6) 
            if (numero % i == 0 || numero % (i + 2) == 0) return false;
                     
        } catch (Exception e) {
            System.out.println("Error de verificacion: " + e.getMessage());
            return false;
        }
        return true; 
    } 
         
    @Override 
    public synchronized boolean add(Integer numero) { 
        if (!isPrime(numero)) {
            throw new IllegalArgumentException("Numero ingresado debe ser primo");
        }
        boolean added = super.add(numero);  
        notify(); 
        return added;
    }
    
    @Override
    public synchronized boolean remove(Object numero) {
        while (this.isEmpty()) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return super.remove(numero);
    }
    
    public int getPrimesCount() {
        return this.size();
    } 
    
}