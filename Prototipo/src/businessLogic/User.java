/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Flight;
import data.Passenger;
import data.Route;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import ui.UI;

/**
 *
 * @author USUARIO
 */
public class User {
    
    public ArrayList <Flight> FindFlightbyRoute(Administrator admin, Route route){ //esta funcion retorna una lista de vuelos que cumplen con las 
        //ruta seleccionada al momento de reservar
        ArrayList <Flight> posFlights=new ArrayList<>(); //Todos los vuelos compatibles con esta ruta 
        for (Flight flight:admin.getAirline().getFlights()){
            if(flight.getRoute().equals(route)){
                posFlights.add(flight); //Si el vuelo tiene la misma ruta añadirlo a la lista que se presenta al usuario
            }
        
        }
        return posFlights;
    }
    
}
   
