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

public class MainController
{

    @FXML
    private TextField betTextField;

    @FXML
    private ImageView dieImageView1;

    @FXML
    private ImageView dieImageView2;

    @FXML
    private Button rollBtn;

    private Random rand = new Random();


    int rollDice() throws InterruptedException 
    {
        Thread rollThread = new Thread() {
            private int dieFaceVal1 = 0;
            private int dieFaceVal2 = 0;
            public void run()
            {
                for (int i = 0; i < 10; i++) 
                {
                    try  {
                        dieFaceVal1 = rand.nextInt(6) + 1;
                        dieFaceVal2 = rand.nextInt(6) + 1;
                    
                        Image dieImage1 = new Image(getClass().getResourceAsStream("images/" + "dice-six-faces-" + dieFaceVal1 + ".png"));
                        Image dieImage2 = new Image(getClass().getResourceAsStream("images/" + "dice-six-faces-" + dieFaceVal2 + ".png"));
                        dieImageView1.setImage(dieImage1);
                        dieImageView2.setImage(dieImage2);

                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        
                }
            }
            
           
        };
        
        rollThread.start();
    

        return 0;
    }
    
    @FXML
    void roll(ActionEvent event) throws InterruptedException 
    {
        rollBtn.setDisable(true);
        
        RollDice rollDice = new RollDice(dieImageView1, dieImageView2);
        
        Thread rollThread = new Thread(rollDice);
        rollThread.start();
        rollThread.join();
        
        int totalDiceFaceVal = rollDice.getTotalDiceFaceVal(); 

        rollBtn.setDisable(false);

        System.out.println(totalDiceFaceVal);

        System.out.println(dieImageView1.getImage());
        // System.out.println(dieImageView1.getImage().getUrl().toString());
        
    }
}
