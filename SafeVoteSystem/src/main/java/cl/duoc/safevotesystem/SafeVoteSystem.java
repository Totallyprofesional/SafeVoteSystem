/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cl.duoc.safevotesystem;

import cl.duoc.safevotesystem.models.mensaje.Mensaje;
import cl.duoc.safevotesystem.models.primes.PrimesList;
import cl.duoc.safevotesystem.models.primes.PrimesThread;

/**
 *
 * @author Home
 */
public class SafeVoteSystem { 
    public static PrimesList primesList;  
    public static PrimesThread primesThread;
    public static Mensaje mensaje; 
    public static int numero;
    public static String texto;
    public static int minimo, maximo, n;
    
    public static void main(String[] args) throws InterruptedException {
        Menu menu = new Menu(primesList, primesThread, mensaje, numero, texto, minimo, maximo, n);
        menu.mostrarMenu();
    }
} 
  