/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bases;

import javafx.scene.control.Button;

/**
 *
 * @author USUARIO
 */
public class BtCerrar extends Button{
    public BtCerrar(){
        super();
        this.setScaleY(1.3);
    }
    public BtCerrar(String nombre){
        super(nombre);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setScaleY(1.3);
        this.setStyle("-fx-background-color: Red");
    }
    
}