/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import interfaceFolder.Controlador;
import interfaceFolder.Inicio;
import interfaceFolder.Singleton;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author danie
 */
public class ProTest extends Application {
    

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, ParseException {
        Application.launch(args);
    }
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        Singleton singleton = Singleton.getSingleton();
        singleton.setStage(primaryStage);
        Controlador controlador = new Controlador();
        primaryStage.setTitle("F.R Ticketes");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icono.jpg")));
        primaryStage.setScene(new Inicio().getScene());
        primaryStage.show();
    }

}