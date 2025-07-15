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
public class PrimesData {
    private String Index;
    private String primeNumber;

    public PrimesData(String Index, String primeNumber) {
        this.Index = Index;
        this.primeNumber = primeNumber;
    }
     
    public String getIndex() {
        return Index;
    }

    public String getPrimeNumber() {
        return primeNumber;
    }
    
    public static List<PrimesData> cargarDesdeCSV() {  
        List<PrimesData> primes = new ArrayList<>();
        try (
            FileReader reader = new FileReader("primos.csv"); 
            CSVReader csvReader = new CSVReader(reader);
        ) {
            System.out.println(new java.io.File(".").getAbsolutePath());
            
            String[] nextLine;
            csvReader.readNext();  
            
            while ((nextLine = csvReader.readNext()) != null) { 
                String Index = nextLine[0];
                String primeNumber = nextLine[1];

                PrimesData prime = new PrimesData(Index, primeNumber); 
                primes.add(prime); 
            }
        } catch (Exception e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        return primes;
    }

    public static void guardarEnCSV(List<PrimesData> primes) {
        try (
            FileWriter writer = new FileWriter("primos.csv");
            CSVWriter csvWriter = new CSVWriter(writer);
        ) {
            String[] header = {"Index", "PrimeNumber"};
            csvWriter.writeNext(header);

            for (PrimesData prime : primes) {
            String[] data = {
                prime.getIndex(),
                prime.getPrimeNumber(),
            };
            csvWriter.writeNext(data);
            }
        } catch (IOException e) {
        System.out.println("Error de guardado: " + e.getMessage());
        }
    }   
    
}
