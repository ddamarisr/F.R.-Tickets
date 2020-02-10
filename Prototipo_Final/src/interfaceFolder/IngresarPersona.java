/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import Bases.Buttons;
import Bases.HBoxs;
import Bases.Labels;
import Bases.TextFields;
import Bases.VBoxs;
import data.Flight;
import data.WriteAndRead;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;
import ui.UI;

/**
 *
 * @author USUARIO
 */
public class IngresarPersona implements Vista {
    private Scene escena;
    private TextFields tfDocumento;
    private TextFields tfNombre;
    private TextFields tfEmail;
    private TextFields tfTelefono;
    private TextFields tfLugar;
    private Label lbError;
    private Buttons btVolver;
    private Buttons btGuardar;
    private Flight flight;

    public IngresarPersona(Flight flight){
        this.flight = flight;
        Labels lbDocumento = new Labels("Ingrese número de documento:");
        tfDocumento = new TextFields();
        Labels lbNombre = new Labels("Ingrese nombre: ");
        tfNombre = new TextFields();
        Labels lbEmail = new Labels("Ingrese e-Mail: ");
        tfEmail = new TextFields();
        Labels lbTelefono = new Labels("Ingrese teléfono");
        tfTelefono = new TextFields();
        Labels lbLugar = new Labels("Ingrese lugar de reserva con el número de fila y letra de columna separados por un espacio: ");
        tfLugar = new TextFields();
        lbError = new Label("Error: Verifique que los datos son correctos.");
        lbError.setTextFill(RED);
        lbError.setVisible(false);
        btVolver = new Buttons("Volver");
        btGuardar = new Buttons("Guardar");
        HBoxs botones = new HBoxs();
        botones.getChildren().add(btGuardar);
        botones.getChildren().add(btVolver);
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbDocumento);
        layout.getChildren().add(tfDocumento);
        layout.getChildren().add(lbNombre);
        layout.getChildren().add(tfNombre);
        layout.getChildren().add(lbEmail);
        layout.getChildren().add(tfEmail);
        layout.getChildren().add(lbTelefono);
        layout.getChildren().add(tfTelefono);
        layout.getChildren().add(lbLugar);
        layout.getChildren().add(tfLugar);
        layout.getChildren().add(lbError);
        VBox picture = new VBox();
        
        for(String i: UI.printPlaneInterfaz(flight)){
            HBox fila = new HBox();
            char[] k = i.toCharArray();
            for (char j : k){
                if(' '==j)fila.getChildren().add(new Label(" "));
                else if('X'==j){
                    Image image = new Image(getClass().getResourceAsStream("SillaRoja.jpg"));  
                    Label a = new Label();
                    a.setGraphic(new ImageView(image));
                    fila.getChildren().add(a);
                }
                else if('_'==j){
                    Image image = new Image(getClass().getResourceAsStream("SillaAzul.jpg"));  
                    Label a = new Label();
                    a.setGraphic(new ImageView(image));
                    fila.getChildren().add(a);
                }
                
                else if(j=='D'){
                    fila.getChildren().add(new Label("     "+ Character.toString(j)+ ""));
                }
                
                else if(Character.isAlphabetic(j)){
                    fila.getChildren().add(new Label("      "+ Character.toString(j)+ " "));
                }
                else fila.getChildren().add(new Label(Character.toString(j)));                
            }
            picture.getChildren().add(fila);
        }
        
        layout.getChildren().add(picture);
        layout.getChildren().add(botones);
        this.escena = new Scene(layout, 850, 800);
        
        btVolver.setOnAction(new IngresarPersona.EventoVolver());
        btGuardar.setOnAction(new IngresarPersona.EventoGuardar());
    }
    
    @Override
    public Scene getScene() {
        return escena;
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
    
    private class EventoGuardar implements EventHandler{
        @Override
        public void handle(Event event) {      
            try {
                flight.addPassengerInterface(Controlador.defo, Integer.parseInt(tfDocumento.getText()), tfNombre.getText(), tfEmail.getText(), tfTelefono.getText(), tfLugar.getText());
                WriteAndRead.writeFlights(Controlador.defo);
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(Controlador.ventanas.pop().getScene());
                stage.show();
                Stage stage2 = new Stage();
                stage2.getIcons().add(new Image(getClass().getResourceAsStream("icono.jpg")));
                stage2.setScene(new Mensaje("La reserva se ha realizado con éxito", 1, stage2).getScene());
                stage2.show();   
            } 
            catch (Exception e) {
                lbError.setVisible(true);
            }
        }
    }
    
    
}
