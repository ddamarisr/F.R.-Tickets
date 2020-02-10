/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import Bases.Buttons;
import Bases.Labels;
import Bases.VBoxs;
import data.WriteAndRead;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class OpcionesVuelo implements Vista{
    private Scene escena;
    private Buttons btCancelar;
    private Buttons btModificar;
    private Buttons btLista;
    private Buttons btVolver;
    private int index;
    
    public OpcionesVuelo(int index){
        this.index = index;
        Labels lbVuelo = new Labels("El Vuelo Seleccionado es: " + Controlador.defo.getAirline().getFlights().get(index).getNumberOfFlight());
        btCancelar = new Buttons("Cancelar Vuelo");
        btModificar = new Buttons("Modificar Información del Vuelo");
        btLista = new Buttons("Ver Lista de Pasajeros");
        btVolver = new Buttons("Volver");
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbVuelo);
        layout.getChildren().add(btCancelar);
        layout.getChildren().add(btModificar);
        layout.getChildren().add(btLista);
        layout.getChildren().add(btVolver);
        this.escena = new Scene(layout, 400,400);
        
        btCancelar.setOnAction(new OpcionesVuelo.EventoCancelar());
        btModificar.setOnAction(new OpcionesVuelo.EventoModificar());
        btLista.setOnAction(new OpcionesVuelo.EventoLista());
        btVolver.setOnAction(new OpcionesVuelo.EventoVolver());
    }
    
    
    @Override
    public Scene getScene() {
        return escena;
    }
    
    private class EventoModificar implements EventHandler{
        @Override
        public void handle(Event event) {      
            ModificarVuelo modificar = new ModificarVuelo(index);
            Controlador.ventanas.push(new OpcionesVuelo(index));
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(modificar.getScene());
            stage.show();
        }
    }
    
    private class EventoLista implements EventHandler{
        @Override
        public void handle(Event event) {      
            Stage stage = new Stage();
            stage.setScene(new ListaPasajeros(stage, index).getScene());
            stage.show();
        }
    }
    
    private class EventoCancelar implements EventHandler{
        @Override
        public void handle(Event event) { 
            Singleton singleton = Singleton.getSingleton();
            Stage stage2 = singleton.getStage();
            stage2.setScene(Controlador.ventanas.pop().getScene());
            stage2.show();

            String texto = "";
            
            try {
                Controlador.defo.deleteFlight(index);
                WriteAndRead.writeFlights(Controlador.defo);
                texto = "El Vuelo Ha Sido Eliminado";
            } catch (IOException ex) {
                Logger.getLogger(OpcionesVuelo.class.getName()).log(Level.SEVERE, null, ex);
                texto = "El Vuelo No Ha Sido Eliminado";
            } catch (ParseException ex) {
                Logger.getLogger(OpcionesVuelo.class.getName()).log(Level.SEVERE, null, ex);
                texto = "El Vuelo No Ha Sido Eliminado";
            }
            
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icono.jpg")));
            if("El Vuelo Ha Sido Eliminado".equals(texto)) stage.setScene(new Mensaje(texto, 1, stage).getScene());
            else stage.setScene(new Mensaje(texto, 2, stage).getScene());
            stage.show();    
        }
        
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
    
    
}
