/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import Bases.Buttons;
import Bases.HBoxs;
import Bases.Scenes;
import Bases.VBoxs;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class Administrador implements Vista {
    private Scenes escena;
    Buttons btMostrar;
    Buttons btAgregar;
    Buttons btBuscar;
    Buttons btDestinos;
    Buttons btVolver;
    
    @Override
    public Scene getScene() {
        return escena;
    }
    
    public Administrador(){
        btMostrar = new Buttons("Mostrar Vuelos");
        btAgregar = new Buttons("Agregar Vuelos");
        btBuscar = new Buttons("Buscar Vuelos");
        btDestinos = new Buttons("Mostrar Destinos");
        btVolver = new Buttons("Volver");
        ImageView logo = new ImageView("fr.jpeg");
        logo.setFitHeight(600);
        logo.setFitWidth(600);
        HBoxs later = new HBoxs();
        VBoxs layout = new VBoxs();
        layout.getChildren().add(btMostrar);
        layout.getChildren().add(btAgregar);
        layout.getChildren().add(btBuscar);
        layout.getChildren().add(btDestinos);
        layout.getChildren().add(btVolver);
        later.getChildren().add(layout);
        later.getChildren().add(logo);
        this.escena = new Scenes(later);
        
        btMostrar.setOnAction(new Administrador.EventoLista(1));
        btAgregar.setOnAction(new Administrador.EventoAgregar());
        btBuscar.setOnAction(new Administrador.EventoBuscar());
        btDestinos.setOnAction(new Administrador.EventoLista(2));
        btVolver.setOnAction(new Administrador.EventoVolver());
    }
    
        
        
    private class EventoLista implements EventHandler{
        int num;
        public EventoLista(int a){
            this.num=a;
        }
        @Override
        
        public void handle(Event event) {  
            Stage stage = new Stage();
            Lista a = new Lista(num, stage);
            stage.setScene(a.getScene());
            stage.show(); 
        }
    }
    
    private class EventoAgregar implements EventHandler{
        @Override
        public void handle(Event event) {      
            AgregarVuelo agregar = new AgregarVuelo();
            Controlador.ventanas.push(new Administrador());
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(agregar.getScene());
            stage.show();
        }
    }
    
    private class EventoBuscar implements EventHandler{
        @Override
        public void handle(Event event) {      
            BuscarVuelos buscar = new BuscarVuelos();
            Controlador.ventanas.push(new Administrador());
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(buscar.getScene());
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
