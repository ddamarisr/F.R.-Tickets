/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import data.Flight;
import data.Passenger;
import java.util.ArrayList;

/**
 *
 * @author danie
 */
public class UI {

    public static ArrayList<String> printPlaneInterfaz(Flight flight) {
        Passenger[][] passengers = flight.getPassengers();
        ArrayList<String> lines = new ArrayList<>();
        lines.add("   ABC" + "  " + "DEF");        
        for (int i = 0; i < passengers.length; i++) {
            String line = "";
            line+=Integer.toString(i+1);
            if(i>=0 && i<9)line+=" ";
            if(i!=9) line+="   ";
            else line+="  ";
            for (int j = 0; j < passengers[0].length; j++) {
                if (j == (passengers[0].length / 2)) {
                    line+="  ";
                }
                if (passengers[i][j] == null) {
                    line+="_";
                } else {
                    line+="X";
                }
            }
            lines.add(line);
        }
        return lines;
    }
}
