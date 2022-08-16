package com.dc;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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

    private ArrayList<File> dieFacesImages = new ArrayList<File>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        for (int i = 1; i <= 6; i++) 
        {
            File dieImageFace = App.loadImage("dice-six-faces-" + i + ".png");
            dieFacesImages.add(dieImageFace); 
        }
    }
    
    @FXML
    void roll(ActionEvent event) 
    {

    }
}
