/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cl.duoc.safevotesystem;

import cl.duoc.safevotesystem.models.mensaje.Mensaje;
import cl.duoc.safevotesystem.models.primes.PrimesList;

/**
 *
 * @author Home
 */
public class SafeVoteSystem {
    public static PrimesList primeList; 
    public static Mensaje mensaje;
    public static int numero;
    public static String texto;
    
    public static void main(String[] args) {
        Menu menu = new Menu(primeList, mensaje, numero, texto);
        menu.mostrarMenu();
        }
}
  
// Hacer metodos de CSV
// Buffered reader para cargar numeros primos
// Filewriter para escribir mensjaes encriptados + codigos primos en texto