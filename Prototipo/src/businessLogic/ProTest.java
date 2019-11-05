/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import data.Airline;
import data.Flight;
import data.Passenger;
import data.WriteAndRead;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import ui.UI;

/**
 *
 * @author danie
 */
public class ProTest {
 

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, ParseException {
        
        Airline airline1 = new Airline("Aerolineas PAYASO", 123456);//Acá creamos los objetos aerolínea. Los vuelos se cargan automáticamente
        
        Administrator defo = new Administrator(airline1);

        String role = UI.welcome();

        if (role.equals("Admin")) {
            UI.AdminMenu(defo);
        } else if (role.equals("User")) {
            User user = new User();
            UI.UserMenu(user, defo);
        } else if (role.equals("Bench")) {
            System.out.println("INSERTAR EN LA MITAD");

            Bench.insertion(10000, "10_mil_datos.txt");
            Bench.insertion(100000, "100_mil_datos.txt");
            Bench.insertion(1000000, "1million.txt");

            System.out.println();

            System.out.println("ELIMINAR EN LA MITAD");

            Bench.delete(10000, "10_mil_datos.txt");
            Bench.delete(100000, "100_mil_datos.txt");
            Bench.delete(1000000, "1million.txt");

            System.out.println();

            System.out.println("INSERTAR AL FINAL");

            Bench.append(10000, "10_mil_datos.txt");
            Bench.append(100000, "100_mil_datos.txt");
            Bench.append(1000000, "1million.txt");
            
        }
    }

}
