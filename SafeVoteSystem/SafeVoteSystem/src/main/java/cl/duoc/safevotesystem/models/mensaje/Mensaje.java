/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem.models.mensaje;

public class Mensaje extends Thread{ 
    private String texto;
    private int numero; 

    public Mensaje(String texto, int numero) {
        this.texto = texto;
        this.numero = numero; 
    } 
    
    public String getTexto() {
        return texto;
    } 
 
    public int getNumero() {
        return numero;
    }
     
    @Override  
    public void run() {
        System.out.println("Mensaje: " + texto);
        System.out.println("Verificar codigo: " + numero);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        }
        System.out.println("Mensaje procesado: " + texto);
    }
    
}
    

 
