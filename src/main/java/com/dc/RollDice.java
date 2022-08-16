package com.dc;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RollDice implements Runnable
{
    private volatile int dieFaceVal1;
    private volatile int dieFaceVal2;

    private ImageView dieImageView1;
    private ImageView dieImageView2;

    private MainController mainController;

    public RollDice(ImageView dieImageView1, ImageView dieImageView2, MainController mainController, Class c)
    {
        this.dieImageView1 = dieImageView1;
        this.dieImageView2 = dieImageView2;
        this.mainController = mainController;
    }

    public void run()
    {
        Random rand = new Random();
        
        for (int i = 0; i < 15; i++) 
        {
            try  {
                dieFaceVal1 = rand.nextInt(6) + 1;
                dieFaceVal2 = rand.nextInt(6) + 1;
            
                Image dieImage1 = new Image(getClass().getResourceAsStream("images/" + "dice-six-faces-" + dieFaceVal1 + ".png"));
                Image dieImage2 = new Image(getClass().getResourceAsStream("images/" + "dice-six-faces-" + dieFaceVal2 + ".png"));
                dieImageView1.setImage(dieImage1);
                dieImageView2.setImage(dieImage2);

                Thread.sleep(70);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        mainController.setTotalDiceFaceVal(getTotalDiceFaceVal());
    }

    public int getTotalDiceFaceVal()
    {
        return dieFaceVal1 + dieFaceVal2;
    }
}
