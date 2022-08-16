package com.dc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MainController implements Initializable
{

    @FXML
    private TextField betTextField;

    @FXML
    private ImageView dieImageView1;

    @FXML
    private ImageView dieImageView2;

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        
    }
    
    @FXML
    void roll(ActionEvent event) 
    {

    }
}
