/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import customImplementations.Node;
import customImplementations.PassengerAVL;
import data.Flight;
import data.Passenger;
import data.Route;
import data.Seat;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class User {
    
    public ArrayList <Flight> FindFlightbyRoute(Administrator admin, Route route){ //esta funcion retorna una lista de vuelos que cumplen con las 
        //ruta seleccionada al momento de reservar
        ArrayList <Flight> posFlights=new ArrayList<>(); //Todos los vuelos compatibles con esta ruta 
        for (Flight flight:admin.getAirline().getFlights().values()){
            if(flight.getRoute().equals(route)){
                posFlights.add(flight); //Si el vuelo tiene la misma ruta añadirlo a la lista que se presenta al usuario
            }
        
        }
        return posFlights;
    }
    
    public Passenger findReservations(Administrator admin,String id){ //retorna un objeto tipo pasajero que es lo mismo que una reservación
        id=id.replace(" ", "");
        int reservationNumber=Integer.valueOf(id);
        PassengerAVL passengers=admin.getAirline().getPassengertree(); //cargamos el árbol de pasajeros. Buscar en un árbol es log(n)
        Node passengerNode=passengers.Find(reservationNumber);
        
        if(passengerNode.getKey().getId()!=reservationNumber){
            return null;
        }
        
        return passengerNode.getKey();   
    }
    
    public void deleteReservation (Administrator admin,Passenger p) throws IOException{ //self-explanatory
           Seat seat=p.getSeat(); //el asiento actual de la persona
           p.getFlight().getPassengers()[seat.getRow()][seat.getColumn()] = null; //quitar de la matriz de pasajeros
           PassengerAVL tree=admin.getAirline().getPassengertree();
           tree.root=tree.deleteNode(tree.root, p); //quitar del arbol de pasajeros
    }
    
}
   
