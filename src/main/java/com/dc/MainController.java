package com.dc;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController implements Initializable
{

    @FXML
    private TextField betTextField;

    @FXML
    private ImageView dieImageView1;

    @FXML
    private ImageView dieImageView2;

    @FXML
    private Button rollBtn;

    private ArrayList<Image> dieFacesImages = new ArrayList<Image>();
    private Random rand = new Random();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) 
    {
        for (int i = 1; i <= 6; i++) 
        {
            Image dieImageFace = App.loadImage("dice-six-faces-" + i + ".png");
            dieFacesImages.add(dieImageFace); 
        }
    }

    int rollDice() throws InterruptedException 
    {
        int dieFaceVal1 = 0;
        int dieFaceVal2 = 0;
        for (int i = 0; i < 10; i++) 
        {
            dieFaceVal1 = rand.nextInt(6) + 1;
            dieFaceVal2 = rand.nextInt(6) + 1;
            
            dieImageView1.setImage(dieFacesImages.get(dieFaceVal1 - 1));
            dieImageView2.setImage(dieFacesImages.get(dieFaceVal2 - 1));
            

            Thread.sleep(100);
        }

        return dieFaceVal1 + dieFaceVal2;
    }
    
    @FXML
    void roll(ActionEvent event) throws InterruptedException 
    {
        rollBtn.setDisable(true);
        int totalValDice = rollDice();
        rollBtn.setDisable(false);

        System.out.println(totalValDice);
        
    }
}
