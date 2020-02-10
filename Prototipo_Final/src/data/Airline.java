/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import businessLogic.Administrator;
import customImplementations.PassengerAVL;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;

/**
 *
 * @author danie
 */
public class Airline implements Serializable{
    
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    private HashMap<Integer, Flight> flights = new HashMap<Integer, Flight>();
    //private LinkedList<Flight> flights;
    private Route[] routesArray;
    private  transient PassengerAVL passengertree; //No sera serializado
 

    private static final long serialVersionUID = 1L;

    private String name;
    private int nit;

    public Airline(String name, int nit) throws IOException, FileNotFoundException {
        this.name = name;
        this.nit = nit;
        try {
            this.flights = WriteAndRead.reader("Flights.txt");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Airline.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        passengertree = new PassengerAVL(); // Vamos a llenar el arbol de pasajeros

        for (Integer i:flights.keySet()) {
            Flight current = flights.get(i);
            Passenger[][] passengersInThisFlight = current.getPassengers();
            for (int j = 0; j < passengersInThisFlight.length; j++) {
                for (int k = 0; k < passengersInThisFlight[0].length; k++) {
                    if (passengersInThisFlight[j][k] != null) {
                        passengertree.root=passengertree.insert(passengertree.root, passengersInThisFlight[j][k]); //Poblando el arbol de pasajeros. el metodo de insercion lidia con el balance del arbol
                    }
                }
            }
        }
    }

    public void addFlight(Administrator defo, String origen, String destination, String num, String dat) throws IOException, ParseException {
        Route newRoute = new Route(origen, destination);
        int number = Integer.parseInt(num);
        Date date = sdf.parse(dat);
        Flight newFlight = new Flight(newRoute, number, date, this,defo);
        this.flights.put(number, newFlight);
 
    }
    
    
    public PassengerAVL getPassengertree() {
        return passengertree;
    }

    public String getName() {
        return name;
    }

    public int getNit() {
        return nit;
    }

    public HashMap<Integer, Flight> getFlights() {
        return flights;
    }


    
    
    
    
    
    
    
    
    
}
