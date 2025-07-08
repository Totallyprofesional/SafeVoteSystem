/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.duoc.safevotesystem.models.primes;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class PrimesList extends ArrayList<Integer> {
    
    public boolean isPrime(int numero) {  
        try {  
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
    public boolean add(Integer numero) { 
        if (!isPrime(numero)) {
            throw new IllegalArgumentException("Numero ingresado debe ser primo");
        }
        return super.add(numero); 
    }
    
    @Override
    public boolean remove(Object numero) {
        if (numero instanceof Integer && !isPrime((Integer) numero)) {
            throw new IllegalArgumentException("Numero ingresado debe ser primo");
        }
        return super.remove(numero);
    }
    
    public int getPrimesCount() {
        return this.size();
    }
    
    // Metodos CSV
    
     public static List<Comic> cargarComicsDesdeCSV() {
        List<Comic> comics = new ArrayList<>();
        try (
            FileReader reader = new FileReader("comics.csv");
            CSVReader csvReader = new CSVReader(reader);
        ) {
            System.out.println(new java.io.File(".").getAbsolutePath());
            
            String[] nextLine;
            csvReader.readNext(); // skip header
            
            while ((nextLine = csvReader.readNext()) != null) {
                String isbn = nextLine[0];
                String titulo = nextLine[1];
                String autor = nextLine[2];
                int anio = Integer.parseInt(nextLine[3]);

                Comic comic = new Comic(isbn, titulo, autor, anio); 
                comics.add(comic);
            }
        } catch (Exception e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        return comics;
    }

    public static void guardarComicsEnCSV(List<Comic> comics) {
        try (
            FileWriter writer = new FileWriter("comics.csv");
            CSVWriter csvWriter = new CSVWriter(writer);
        ) {
            String[] header = {"isbn", "titulo", "autor", "año"};
            csvWriter.writeNext(header);

            for (Comic comic : comics) {
            String[] data = {
                comic.getIsbn(),
                comic.getTitulo(),
                comic.getAutor(),
                String.valueOf(comic.getAnio())
            };
            csvWriter.writeNext(data);
            }
        } catch (IOException e) {
        System.out.println("Error de guardado: " + e.getMessage());
        }
    }
    
}