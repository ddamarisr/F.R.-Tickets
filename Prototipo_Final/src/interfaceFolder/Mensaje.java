/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import Bases.HBoxs;
import Bases.Labels;
import Bases.VBoxs;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class Mensaje implements Vista {
    private Scene escena;
    Labels lbMensaje;
    ImageView logo;
    Button btAceptar;
    Button btCancelar;
    Stage stage;
    
    @Override
    public Scene getScene() {
        return escena;
    }
    
    public Mensaje(String mensaje, int opcion, Stage stage){
        this.stage = stage;
        lbMensaje = new Labels(mensaje);
        if(opcion == 1) logo = new ImageView("chulito.jpg");
        else logo = new ImageView("equis.jpg");
            HBoxs box = new HBoxs();
            box.getChildren().add(logo);
            box.getChildren().add(lbMensaje);
            this.escena = new Scene(box, 400, 100);
    }    
}
