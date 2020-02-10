/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;


import data.Airline;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author danie
 */
public class Administrator {
    
    private Airline airline;

    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

    public Administrator(Airline airline) {
        this.airline=airline;
    }

    public Airline getAirline() {
        return airline;
    }
    
    public void addFlight(Administrator defo, String origen, String destination, String num, String dat) throws IOException, ParseException{ 
        this.airline.addFlight(this, origen, destination, num, dat);
    }
    
    public void deleteFlight(int index) throws IOException, ParseException {
        airline.getFlights().remove(index);
    }  
}
