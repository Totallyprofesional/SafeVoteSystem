/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem;

import java.util.InputMismatchException;
import java.util.Scanner;
import cl.duoc.safevotesystem.models.mensaje.Mensaje;
import cl.duoc.safevotesystem.models.primes.PrimesData;
import cl.duoc.safevotesystem.models.primes.PrimesList;
import cl.duoc.safevotesystem.models.primes.PrimesThread;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Home
 */ 
public class Menu {
    private Scanner sc = new Scanner(System.in);
    private PrimesList primesList;
    private PrimesThread primesThread;
    private Mensaje mensaje;
    private int numero;
    private String texto;
    private List<PrimesData> primes = new ArrayList<>();
    private BlockingQueue<Mensaje> queue = new LinkedBlockingQueue<>();
    private int minimo, maximo, n;

    public Menu(PrimesList primesList, PrimesThread primesThread, Mensaje mensaje, int numero, String texto, int minimo, int maximo, int n) {
        this.primesList = new PrimesList(); 
        this.primesThread = primesThread;
        this.mensaje = mensaje;
        this.numero = numero;
        this.texto = texto;
        this.minimo = minimo;
        this.maximo = maximo;
        this.n = n;
    }

    public void mostrarMenu() throws InterruptedException {
        int option = 0;
 
        do { 
            System.out.println("\n Bienvenido al Menu Safe Vote System");
            System.out.println("1. Mensajeria");
            System.out.println("2. Agregar codigos");
            System.out.println("3. Buscar codigos");
            System.out.println("4. Eliminar codigos");
            System.out.println("5. Mostrar total de codigos primos");
            System.out.println("6. Cargar lista de codigos CSV");
            System.out.println("7. Guardar Cambios");
            System.out.println("8. Exportar Reporte");
            System.out.println("9. Salir");
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
                    CargarCodigos();
                    break;
                case 7:
                    GuardarCambios();      
                    break;
                case 8:
                    ExportarReporteTxt(); 
                    break;
                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Por favor ingrese una opcion valida.");
                    break;
            } 

        } while (option != 9);  
    }   

    public void Mensajeria(Scanner sc) throws InterruptedException { 
        sc.nextLine(); 
        System.out.println("Ingrese texto del mensaje: ");
        String texto = sc.nextLine();
        
        int numero;    
 
        do{
            PrimesThread primesThread = new PrimesThread(10, 100);
            Thread t = new Thread(primesThread);
            t.start(); 
            t.join(); 
            numero = primesThread.getNumero(); 
        } while (!primesList.isPrime(numero));
        
        if (primesList.add(numero)) {
        System.out.println("Primo generado agregado: " + numero);
        }

        // Convertir lista a PrimesData 
        List<PrimesData> data = new ArrayList<>();
        for (int i = 0; i < primesList.size(); i++) {
            data.add(new PrimesData(
            String.valueOf(i + 1),
       String.valueOf(primesList.get(i))));
        }

        // Guardar CSV
        PrimesData.guardarEnCSV(data);
        
        Mensaje mensaje = new Mensaje(texto, numero);
        try {
            queue.put(mensaje);
            System.out.println("Mensaje enviado: " + texto);
            System.out.println("Codigo en queue: " + numero);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } 
    }
    
    public void AgregarCodigos(Scanner sc) {   
        try {
            System.out.print("Ingrese código primo: ");
            numero = sc.nextInt(); 
            sc.nextLine(); 
           
            if (primesList.add(numero)){
                System.out.println("Codigo agregado.");
            } else {
                System.out.println("Codigo invalido.");
            }   
        
        } catch (InputMismatchException e) { 
            System.out.println("Error. Ingrese un numero valido.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }  
             
    public void BuscarCodigos(Scanner sc) {
        try {
            System.out.print("Ingrese codigo primo: "); 
            numero = sc.nextInt(); 
            sc.nextLine();   
         
            if (primesList.contains(numero)) {
                System.out.println("Codigo encontrado.");
            } else {
                System.out.println("Codigo no encontrado."); 
            }
        
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un numero valido.");           
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }  
    }
     
    public void EliminarCodigos(Scanner sc) {
        try {
            System.out.print("Ingrese código primo: ");
            numero = sc.nextInt();  
            sc.nextLine();  
            
            if (!primesList.isEmpty()) {
                boolean removed = primesList.remove(Integer.valueOf(numero));
                if (removed) {
                    System.out.println("Código eliminado.");
                } else {
                    System.out.println("Código no encontrado.");
                } System.out.println("La lista está vacía.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error. Ingrese un numero valido.");   
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }          
    }   

    public void TotalCodigos() {
        System.out.println("Total de códigos: " + primesList.getPrimesCount());
    }
     
    public void CargarCodigos() {
        List<PrimesData> primes = PrimesData.cargarDesdeCSV(); 
        System.out.println("");
        System.out.println("Codigos leídos desde CSV:");
        
        for (PrimesData prime : primes) {
        System.out.println(prime.getIndex() + ": " + prime.getPrimeNumber());
        }
    }
    
    private void GuardarCambios() {
        try {
            PrimesData.guardarEnCSV(primes);
            System.out.println("Cambios guardados satisfactoriamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar los cambios: " + e.getMessage());
        }  
    }  
    
    public void ExportarReporteTxt() {
      String fecha = java.time.LocalDate.now().toString();
      String rutaArchivo = "reporte_codigos_" + fecha + ".txt";
      try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(rutaArchivo))) {
        writer.write("Lista de codigos.");
        writer.newLine();
        if (primes.isEmpty()) { 
          writer.write("No hay codigos registrados.");
          writer.newLine();
        } else { 
          for (PrimesData prime : primes) {
            writer.write(prime.getIndex() + " - " + prime.getPrimeNumber());
            writer.newLine();
          }
          writer.write("Total de codigos: " + primes.size());
          writer.newLine();
          writer.write("Fecha de generación del reporte: " + fecha);
        }
        System.out.println("Reporte exportado exitosamente a: " + rutaArchivo);
      } catch (Exception e) {
        System.err.println("Error al exportar el reporte: " + e.getMessage());
      }
    }  
    
}

