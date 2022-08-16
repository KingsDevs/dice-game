package com.dc;


import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    private int totalDiceFaceVal;
    private Random rand = new Random();

    
    
    public void setTotalDiceFaceVal(int totalDiceFaceVal)
    {
        this.totalDiceFaceVal = totalDiceFaceVal;
    }

    int rollDices(ImageView dieImageView)
    {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setByAngle(360);
        rotateTransition.setNode(dieImageView);
        rotateTransition.setDuration(Duration.millis(540));
        rotateTransition.play();

        int randomDieFaceVal = rand.nextInt(6) + 1;

        rotateTransition.setOnFinished(i -> {
            Image dieImage = new Image(getClass().getResourceAsStream("images/" + "dice-six-faces-" + randomDieFaceVal + ".png"));
            dieImageView2.setImage(dieImage);
        });

        return randomDieFaceVal;
    }
    
    @FXML
    void roll(ActionEvent event) throws InterruptedException 
    {
        rollBtn.setDisable(true);
        
        rollBtn.setDisable(false);

       
        
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }
}
