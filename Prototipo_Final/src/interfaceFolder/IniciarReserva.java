/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import Bases.Buttons;
import Bases.HBoxs;
import Bases.Labels;
import Bases.Scenes;
import Bases.VBoxs;
import data.Flight;
import data.Route;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;

public class IniciarReserva implements Vista{
    private Scenes escena;
    private Buttons btBuscar;
    private Label lbError;
    private Buttons btVolver;
    private Buttons btSiguiente;
    private ListView<String> routes;
    private ObservableList<String> items;
    private ListView<String> vuelos;
    private ObservableList<String> items2;
    private ArrayList <Route> uniqueRoutes;
    private ArrayList<Flight> posFlights;
    
    
    public IniciarReserva(){
        Labels lbRuta = new Labels("Seleccione la ruta de interés");
        routes = new ListView<>();
        items = FXCollections.observableArrayList();   
        uniqueRoutes = new ArrayList<>();
        for (Flight fligths : Controlador.defo.getAirline().getFlights().values()) {
            
            String temp = fligths.getRoute().toString();
            if (!items.contains(temp)) {
                uniqueRoutes.add(fligths.getRoute());
                items.add(temp);
            }
        }
        routes.setItems(items);
        btBuscar = new Buttons("Buscar");
        Labels lbFecha = new Labels("Seleccione el vuelo de interés");
        vuelos = new ListView<>();
        items2 = FXCollections.observableArrayList();
        vuelos.setItems(items2);
        lbError = new Label("No ha seleccionado un elemento");
        lbError.setTextFill(RED);
        lbError.setVisible(false);
        btVolver = new Buttons("Volver");
        btSiguiente = new Buttons("Siguiente");
        HBoxs botones = new HBoxs();
        botones.getChildren().add(btVolver);
        botones.getChildren().add(btSiguiente);
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbRuta);
        layout.getChildren().add(routes);
        layout.getChildren().add(btBuscar);
        layout.getChildren().add(lbFecha);
        layout.getChildren().add(vuelos);
        layout.getChildren().add(lbError);
        layout.getChildren().add(botones);
        this.escena = new Scenes(layout);
        
        btVolver.setOnAction(new IniciarReserva.EventoVolver());
        btBuscar.setOnAction(new IniciarReserva.EventoBuscar());
        btSiguiente.setOnAction(new IniciarReserva.EventoSiguiente());
    }
    
    @Override
    public Scene getScene() {
        return this.escena;
    }
    
    
    private class EventoVolver implements EventHandler{
        @Override
        public void handle(Event event) {      
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(Controlador.ventanas.pop().getScene());
            stage.show();
        }
    }
    
    private class EventoBuscar implements EventHandler{
        @Override
        public void handle(Event event) {      
            try {
                items2.clear();
                lbError.setVisible(false);
                posFlights = Controlador.user.FindFlightbyRoute(Controlador.defo, uniqueRoutes.get(routes.getSelectionModel().getSelectedIndex()));
                for (Flight flight : posFlights) {
                    items2.add(flight.toString());
                }
            } 
            catch (Exception e) {
                lbError.setVisible(true);
            }
        }
    }
    
    private class EventoSiguiente implements EventHandler{
        @Override
        public void handle(Event event) {      
            try {
                IngresarPersona ingresar = new IngresarPersona(posFlights.get(vuelos.getSelectionModel().getSelectedIndex()));
                Controlador.ventanas.push(new IniciarReserva());
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(ingresar.getScene());
                stage.show();                
            } 
            catch (Exception e) {
                lbError.setVisible(true);
            }
        }
    }
    
}
