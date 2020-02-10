/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import Bases.Buttons;
import Bases.Labels;
import Bases.Scenes;
import Bases.TextFields;
import Bases.VBoxs;
import data.Flight;
import data.Route;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class BuscarVuelos implements Vista{
    Scenes escena;
    ListView<String> list;
    ObservableList<String> vuelos;
    Labels lbIngrese;
    Labels lbOrigen;
    TextFields tfOrigen;
    Labels lbDestino;
    TextFields tfDestino;
    Label lbError;
    Label lbError2;
    ComboBox busqueda;
    Buttons btBuscar;
    Buttons btReestablecer;
    Buttons btOpciones;
    LinkedList<Integer> index = new LinkedList<>();

    
    public BuscarVuelos(){
              
        
        Buttons btVolver = new Buttons("Volver");
        ObservableList<String> options = FXCollections.observableArrayList("Ruta", "Número de Vuelo");
        busqueda = new ComboBox(options);
        list = new ListView<>();
        vuelos = FXCollections.observableArrayList();
        lbIngrese = new Labels("Escoja el criterio de búsqueda");
        lbOrigen = new Labels("Ingrese la ciudad de origen:");
        lbOrigen.setVisible(false);
        tfOrigen = new TextFields();
        tfOrigen.setVisible(false);
        lbDestino = new Labels("Ingrese la ciudad de destino:");
        lbDestino.setVisible(false);
        tfDestino = new TextFields();
        tfDestino.setVisible(false);
        lbError = new Label("");
        lbError.setTextFill(RED);
        lbError.setVisible(false);
        lbError2 = new Label("Seleccione un elemento de la lista");
        lbError2.setTextFill(RED);
        lbError2.setVisible(false);
        btBuscar = new Buttons("Buscar");
        btBuscar.setDisable(true);
        btReestablecer = new Buttons("Reestablecer");
        btOpciones = new Buttons("Opciones");
        btOpciones.setDisable(true);
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbIngrese);
        layout.getChildren().add(busqueda);
        layout.getChildren().add(lbOrigen);
        layout.getChildren().add(tfOrigen);
        layout.getChildren().add(lbDestino);
        layout.getChildren().add(tfDestino);
        layout.getChildren().add(btBuscar);
        layout.getChildren().add(lbError);
        layout.getChildren().add(list);
        layout.getChildren().add(lbError2);
        layout.getChildren().add(btOpciones);
        layout.getChildren().add(btReestablecer);
        layout.getChildren().add(btVolver);
        this.escena = new Scenes(layout);
        
        btVolver.setOnAction(new BuscarVuelos.EventoVolver());
        busqueda.setOnAction(new BuscarVuelos.EventoBusqueda());
        btBuscar.setOnAction(new BuscarVuelos.EventoBuscar());
        btReestablecer.setOnAction(new BuscarVuelos.EventoReestablecer());
        btOpciones.setOnAction(new BuscarVuelos.EventoOpciones());
    }
    
    @Override
    public Scene getScene() {
        return escena;
    }
    
    public void reset(){
        lbOrigen.setVisible(false);
        tfOrigen.setVisible(false);
        lbDestino.setVisible(false);
        tfDestino.setVisible(false);
        tfOrigen.setText(null);
        tfDestino.setText(null);
        list.setItems(null);
        index = new LinkedList<>();
        btOpciones.setDisable(true);
        lbError.setVisible(false);
        lbError2.setVisible(false);
        busqueda.getSelectionModel().clearSelection();
    }
    
    public void setVisibleRuta(){
        lbOrigen.setText("Ingrese la ciudad de origen:");
        tfOrigen.setText(null);
        btBuscar.setDisable(false);
        lbOrigen.setVisible(true);
        tfOrigen.setVisible(true);
        lbDestino.setVisible(true);
        tfDestino.setVisible(true);
    }
    
    public void setVisibleVuelo(){
        lbOrigen.setVisible(true);
        tfOrigen.setVisible(true);
        tfOrigen.setText(null);
        lbOrigen.setText("Ingrese el número de vuelo:");
        btBuscar.setDisable(false);
        lbDestino.setVisible(false);
        tfDestino.setVisible(false);
    }

    public String getOrigen() {
        return tfOrigen.getText();
    }

    public String getDestino() {
        return tfDestino.getText();
    }
    
    public String getCriterio(){
        return (String) busqueda.getValue();
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
    
    private class EventoBusqueda implements EventHandler{
        @Override
        public void handle(Event event) {      
            if(busqueda.getValue().equals("Ruta")) setVisibleRuta();
            else{
                setVisibleVuelo();
            }
        }
    }
    
    private class EventoOpciones implements EventHandler{
        @Override
        public void handle(Event event) {      
            try {
                int a = list.getItems().indexOf(list.getSelectionModel().getSelectedItem());
                OpcionesVuelo modificar = new OpcionesVuelo(index.get(a));
                Controlador.ventanas.push(new BuscarVuelos());
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(modificar.getScene());
                stage.show();
            } catch (Exception e) {
                lbError2.setVisible(true);
            }
            
        }
    }
    
    private class EventoReestablecer implements EventHandler{
        @Override
        public void handle(Event event) {      
            reset();
        }
    }
    
    
    private class EventoBuscar implements EventHandler{
        @Override
        public void handle(Event event) {    
            list.setItems(null);
            LinkedList<Flight> posFlights = new LinkedList<>();
            vuelos.clear();
            
            if(busqueda.getValue().equals("Ruta")){
                Route otherRoute = new Route(getOrigen(),getDestino());
                    for (Flight temp : Controlador.defo.getAirline().getFlights().values()) {
                        if ( otherRoute.equals(temp.getRoute()))
                        {
                            posFlights.add(temp);
                            index.add(temp.getNumberOfFlight());
                        }
                    }
            }
            else{
                try {
                    int flNum = Integer.valueOf(getOrigen());
                    for (Flight temp : Controlador.defo.getAirline().getFlights().values()) {
                        if (flNum == temp.getNumberOfFlight()) {
                            posFlights.add(temp);
                            index.add(temp.getNumberOfFlight());
                        }
                    }
                } 
                catch (Exception e) {
                    lbError.setVisible(true);
                    lbError.setText("No hay registros con los datos proporcionados");
                }   
            }
            
            if(posFlights.isEmpty()){
                lbError.setVisible(true);
                lbError.setText("No hay registros con los datos proporcionados");
            }
            else{
                for(Flight temp: posFlights){
                    vuelos.add(temp.toString());
                }
                list.setItems(vuelos);
                lbError.setVisible(false);
                btOpciones.setDisable(false);
            }
            
        }
    }
}
