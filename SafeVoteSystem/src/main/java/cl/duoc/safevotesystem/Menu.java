/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem;

import java.util.InputMismatchException;
import java.util.Scanner;
import cl.duoc.safevotesystem.models.mensaje.Mensaje;
import cl.duoc.safevotesystem.models.primes.PrimesList;
import java.io.BufferedWriter;

/**
 *
 * @author Home
 */
public class Menu {
    private Scanner sc = new Scanner(System.in);
    private PrimesList primeList;
    private Mensaje mensaje;
    private int numero;
    private String texto;

    public Menu(PrimesList primeList, Mensaje mensaje, int primo, String texto) {
        this.primeList = primeList;
        this.mensaje = mensaje;
        this.numero = numero;
        this.texto = texto;
    }
    
    public void mostrarMenu() {
        int option = 0;
 
        do { 
            System.out.println("\n Bienvenido al Menu Safe Vote System");
            System.out.println("1. Mensajeria");
            System.out.println("2. Agregar codigos");
            System.out.println("3. Buscar codigos");
            System.out.println("4. Eliminar codigos");
            System.out.println("5. Mostrar total de c�digos primos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
             
            option = sc.nextInt(); 
  
            switch (option) {
                case 1:
                    Mensajeria(sc);
                    break;
                case 2: 
                    AgregarCodigos(sc); 
                    break; 
                case 3: 
                    BuscarCodigos(sc);
                    break;
                case 4: 
                    EliminarCodigos(sc);
                    break;
                case 5:
                    TotalCodigos(); 
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Por favor ingrese una opcion valida");
            } 

        } while (option != 6); 
    }  

    public void Mensajeria(Scanner sc) {
        System.out.print("Ingrese texto del mensaje: ");
        texto = sc.nextLine(); 
        System.out.print("Ingrese codigo primo asociado: ");
        
        try {
            numero = sc.nextInt();
            sc.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un numero valido");
        }
        
        if (primeList.isPrime(numero)){
            mensaje.run();
            System.out.println("Mensaje enviado");
        } else {
            System.out.println("Codigo invalido");
        }        
    } 
    
    public void AgregarCodigos(Scanner sc) {   
        try {
            numero = sc.nextInt();
            sc.nextLine(); 
        
            if (primeList.add(numero)){
                System.out.println("Codigo agregado");
            } else {
                System.out.println("Codigo invalido");
            }   
        
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un numero valido");
        }   
    }
             
    public void BuscarCodigos(Scanner sc) {
        System.out.print("Ingrese codigo primo: ");
        
        try {
            numero = sc.nextInt(); 
            sc.nextLine(); 
       
         
            if (primeList.contains(numero)) {
                System.out.println("Codigo encontrado");
            } else {
                System.out.println("Codigo no encontrado.");
            }
        
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un numero valido");
        }   
    }
     
    public void EliminarCodigos(Scanner sc) {
        try {
            numero = sc.nextInt(); 
            sc.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un numero valido");
        }   
        
        primeList.remove(numero);         
    }
      
    public void TotalCodigos() {
        primeList.getPrimesCount();
    }
    
    // Metodos CSV
    
    private void GuardarCambios() {
        try {
            Comic.guardarComicsEnCSV(comics);
            System.out.println("Cambios guardados satisfactoriamente");
        } catch (Exception e) {
            System.out.println("Error al guardar los cambios: " + e.getMessage());
        }  
    } 
    
    public void ExportarReporteTxt() {
      String fecha = java.time.LocalDate.now().toString();
      String rutaArchivo = "reporte_comics_" + fecha + ".txt";
      try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(rutaArchivo))) {
        writer.write("Reporte de Comics");
        writer.newLine();
        if (comics.isEmpty()) {
          writer.write("No hay comics disponibles.");
          writer.newLine();
        } else { 
          for (Comic comic : comics) {
            writer.write(comic.getIsbn() + " - " + comic.getTitulo() + " - " + comic.getAutor() + " - " + comic.getAnio());
            writer.newLine();
          }
          writer.write("Total de comics: " + comics.size());
          writer.newLine();
          writer.write("Fecha de generación del reporte: " + fecha);
        }
        System.out.println("Reporte exportado exitosamente a: " + rutaArchivo);
      } catch (Exception e) {
        System.err.println("Error al exportar el reporte: " + e.getMessage());
      }
    }  
    
}

