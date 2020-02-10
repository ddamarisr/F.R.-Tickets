/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import Bases.Buttons;
import Bases.Labels;
import Bases.TextFields;
import Bases.VBoxs;
import data.Passenger;
import data.Seat;
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
public class CambiarAsiento implements Vista {
    private Scene escena;
    private TextFields tfLugar;
    private Label lbError;
    private Buttons btVolver;
    private Buttons btGuardar;
    private Passenger p;
    
    public CambiarAsiento(Passenger p){
        this.p = p;
        Labels lbLugar = new Labels("Ingrese el nuevo lugar de reserva con el número de fila y letra de columna separados por un espacio: ");
        tfLugar = new TextFields();
        lbError = new Label("Error: Verifique que los datos son correctos.");
        lbError.setTextFill(RED);
        lbError.setVisible(false);
        btVolver = new Buttons("Volver");
        btGuardar = new Buttons("Guardar");
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbLugar);
        layout.getChildren().add(tfLugar);
        layout.getChildren().add(lbError);
        layout.getChildren().add(btGuardar);
        VBox picture = new VBox();
        for(String i: UI.printPlaneInterfaz(p.getFlight())){
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
        btVolver = new Buttons("Volver");
        layout.getChildren().add(picture);
        layout.getChildren().add(btVolver);
        this.escena = new Scene(layout, 500, 500);
        
        btVolver.setOnAction(new CambiarAsiento.EventoVolver());
        btGuardar.setOnAction(new CambiarAsiento.EventoGuardar());
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
                lbError.setVisible(false);
                Seat newSeat = p.getFlight().checkAvailableSeatsInterface(tfLugar.getText());
                p.getFlight().getPassengers()[p.getSeat().getRow()][p.getSeat().getColumn()] = null; //liberar espacio del avion
                p.setSeat(newSeat); //Cambiar el asiento asignado
                p.getFlight().getPassengers()[p.getSeat().getRow()][p.getSeat().getColumn()] = p; //añadir al avión en la nueva posicion
                WriteAndRead.writeFlights(Controlador.defo);
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(Controlador.ventanas.pop().getScene());
                stage.show();
                
                Stage stage2 = new Stage();
                stage2.getIcons().add(new Image(getClass().getResourceAsStream("icono.jpg")));
                stage2.setScene(new Mensaje("El asiento ha sido cambiado correctamente", 1, stage2).getScene());
                stage2.show();  
                
            } catch (Exception e) {
                lbError.setVisible(true);
            }
            
        }
    }
}