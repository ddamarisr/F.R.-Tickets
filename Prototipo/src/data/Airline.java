/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class Airline implements Serializable{
    
    private LinkedList<Flight> flights;
    private Route [] routesArray;
   
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int nit;

    public Airline(String name, int nit) throws IOException, FileNotFoundException{
        this.name = name;
        this.nit = nit;
        try {
            this.flights=WriteAndRead.reader("Flights.txt");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Airline.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public String getName() {
        return name;
    }

    public int getNit() {
        return nit;
    }

    public LinkedList<Flight> getFlights() {
        return flights;
    }
    
    
    
    
    
    
    
    
    
    
}
