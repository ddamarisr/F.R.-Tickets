/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Date;
import ui.UI;

/**
 *
 * @author danie
 */
public class Flight implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Passenger[][] passengers;
    private Route route;
    private int numberOfFlight;
    private Date date;
    private final Airline airline;

    public Flight(Route route, int numberOfFlight, Date date, Airline airline) {
        this.route=route;
        this.numberOfFlight = numberOfFlight;
        this.date=date;
        this.airline=airline;
        this.passengers= new Passenger[10][6]; //60 pasajeros inicialmente
    }

    public Passenger[][] getPassengers() {
        return passengers;
    }

    public Passenger addPassenger() throws IOException {

        int id = Integer.valueOf(UI.recorder("su número de documento: "));
        String name = UI.recorder("su nombre: ");
        String email = UI.recorder("su e-mail: ");
        String tel = UI.recorder("su número de teléfono: ");
        UI.printPlane(this);
        String seat = UI.recorder("su asiento escribiendo el número "
                + "y la letra correspondientes separados por un espacio:");

        boolean available = false;

        Passenger p = new Passenger();

        while (!available) {
            String seatCoord[] = seat.split(" ");
            int row = Integer.valueOf(seatCoord[0]) - 1;
            char column = seatCoord[1].charAt(0);
            column = Character.toLowerCase(column);
            int columnNumber = (int)column - 97; //97 es el ASCII de letra minúscula a
            
            System.out.println(columnNumber);

            if (passengers[row][columnNumber] == null) {
                Seat seatObject = new Seat(row, columnNumber);
                p = new Passenger(id, name, email, seatObject); //instanciarlo pero todavía no agregarlo
                passengers[row][columnNumber] = p; //añadir al avión
                available = true; //se sale del while porque selecciono un asiento válido
            } else {
                seat = UI.recorder("otro asiento");
            }

        }

        return p;

    }
    

    public Route getRoute() {
        return route;
    }
   
    public int getNumberOfFlight() {
        return numberOfFlight;
    }

    public void setNumberOfFlight(int numberOfFlight) {
        this.numberOfFlight = numberOfFlight;
    }
    
    public String getAirline() {
        return airline.getName();
    }
    
    public String getDateFormat(){
    return Integer.toString(date.getDate())+"/"+Integer.toString(date.getMonth()+1)+"/"+Integer.toString(date.getYear());
    }
    public Date getDate(){
        return date;
    }
 /*   
    public boolean fullFlight(){
        return this.plane.fullPlane();
    }
    
    public void addPassenger(Passenger p){
        plane.addPassenger(p);
    }
    
    public Plane getPlane(){
        return this.plane;
    }    
  */  
    
    public void addPassenger(Passenger p){
        
    }
    
    
    

    public void setDate(Date Date) {
        this.date = Date;
    }

    @Override
    public String toString() {
        return "Flight{" + "route=" + route + ", numberOfFlight=" + numberOfFlight + ", date=" + date + ", airline=" + airline + '}';
    }


   
    
    

   

    
    
    
    
    
    
    
    
    
    
}
