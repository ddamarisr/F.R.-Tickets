/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import businessLogic.Administrator;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
/**
 *
 * @author danie
 */
public class WriteAndRead {
    
    private static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    public static void writeFlights(Administrator defo)throws FileNotFoundException, IOException{
        HashMap <Integer, Flight> flights=defo.getAirline().getFlights();
        FileOutputStream file = new FileOutputStream(new File("flights.txt"));
        ObjectOutputStream out = new ObjectOutputStream(file);
        
        for (Flight i : flights.values()) {
            out.writeObject(i);
        }
        
        out.close();  
        file.close();
    }

    public static HashMap <Integer, Flight> reader(String path) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        
        HashMap <Integer, Flight> flights=new HashMap <>();
        FileInputStream fi = new FileInputStream(new File(path)); //archivo
        
        ObjectInputStream oi = new ObjectInputStream(fi);//objeto

        while (true) {
            try {
                Flight flight= (Flight) oi.readObject();
                flights.put(flight.getNumberOfFlight(), flight);
                
            } catch (EOFException e) {
                oi.close();
                return flights;
            } 
        }
    }
}
