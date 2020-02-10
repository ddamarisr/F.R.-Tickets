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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Inicio implements Vista{

    private Scenes escena;
    private Buttons btAdministrador;
    private Buttons btUsuario;
    private Buttons btCerrar;
    
    public Inicio(){
        btAdministrador = new Buttons("Administrador");
        btUsuario = new Buttons("Usuario");
        btCerrar = new Buttons("Cerrar");
        VBoxs layout = new VBoxs();
        layout.getChildren().add(btAdministrador);
        layout.getChildren().add(btUsuario);
        layout.getChildren().add(btCerrar);
        ImageView logo = new ImageView("fr.jpeg");
        logo.setFitHeight(600);
        logo.setFitWidth(600);
        HBoxs later = new HBoxs();
        later.getChildren().add(layout);
        later.getChildren().add(logo);
        this.escena = new Scenes(later);
        
        btAdministrador.setOnAction(new EventoAdministrador());
        btUsuario.setOnAction(new EventoUsuario());
        btCerrar.setOnAction(new EventoCerrar());
    }
    
    @Override
    public Scene getScene() {
        return this.escena;
    }
    
    private class EventoAdministrador implements EventHandler{
        @Override
        public void handle(Event event) {      
            Controlador.ventanas.push(new Inicio());
            Administrador admin = new Administrador();
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(admin.getScene());
            stage.show();
        }
    }
    
    private class EventoUsuario implements EventHandler{
        @Override
        public void handle(Event event) { 
            Controlador.ventanas.push(new Inicio());
            Usuario user = new Usuario();
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(user.getScene());
            stage.show();
        }
    }
    
    private class EventoCerrar implements EventHandler{
        @Override
        public void handle(Event event) {      
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.close();
        }
    }
    
}

