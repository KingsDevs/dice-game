package com.dc;


import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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

    @FXML
    private Label promptText;

    @FXML
    private RadioButton overRadio;

    @FXML
    private RadioButton underRadio;

    @FXML
    private ToggleGroup underover;

    @FXML
    private Label bankLabel;

    @FXML
    private Label numberOfBetsLabel;

    @FXML
    private Spinner<Integer> betAmountSpinner;

    private Random rand = new Random();

    private int numberOfBets;
    private int money;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        setNumberOfBets(5);
        setMoney(3);

        betTextField.textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if(!newValue.matches("\\d*"))
                {
                    betTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                
                if(!(betTextField.getText().isBlank() || betTextField.getText().isEmpty()))
                {
                    int bet = Integer.parseInt(betTextField.getText());
                    if( bet > 12)
                    {
                        betTextField.setText(oldValue);
                        promptText.setText("Input bet in range 2 - 12 only!");
                        promptText.setTextFill(Color.RED);
                    } 
                }

            }

        });
        
    }

    private void setNumberOfBets(int numberOfBets)
    {
        this.numberOfBets = numberOfBets;
        numberOfBetsLabel.setText("Number of Bets Left: " + numberOfBets);
    }

    private void setMoney(int money)
    {
        this.money = money;
        bankLabel.setText("Bank: " + money);
    }

    int rollDie(ImageView dieImageView)
    {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setByAngle(360);
        rotateTransition.setNode(dieImageView);
        rotateTransition.setDuration(Duration.millis(540));
        rotateTransition.play();

        int randomDieFaceVal = rand.nextInt(6) + 1;

        rotateTransition.setOnFinished(i -> {
            Image dieImage = new Image(getClass().getResourceAsStream("images/" + "dice-six-faces-" + randomDieFaceVal + ".png"));
            dieImageView.setImage(dieImage);
        });

        

        return randomDieFaceVal;
    }
    
    @FXML
    void roll(ActionEvent event) throws InterruptedException 
    {
        rollBtn.setDisable(true);
        int bet;

        setNumberOfBets(numberOfBets - 1);
        setMoney(money - 1);

        if(!betTextField.getText().isEmpty())
        {
            bet = Integer.parseInt(betTextField.getText());

            int dieVal1 = rollDie(dieImageView1);
            int dieVal2 = rollDie(dieImageView2);

            int diceTotalVal = dieVal1 + dieVal2;

            if(bet == diceTotalVal)
            {
                promptText.setText("You Won! You Earned +5 Money");
                promptText.setTextFill(Color.GREEN);
                setMoney(money + 5);
            }
            else if(bet > diceTotalVal && underRadio.isSelected())
            {
                promptText.setText("The Dice Rolls Under "+ bet +"! You Earned +2 Money");
                promptText.setTextFill(Color.ORANGE);
                setMoney(money + 2);
            }
            else if(bet < diceTotalVal && overRadio.isSelected())
            {
                promptText.setText("The Dice Rolls Over "+ bet +"! You Earned +2 Money");
                promptText.setTextFill(Color.ORANGE);
                setMoney(money + 2);
            }
            else
            {
                promptText.setText("You Lost!");
                promptText.setTextFill(Color.RED);
            }
        }
        else
        {
            promptText.setText("Please Enter Your Bet!");
            promptText.setTextFill(Color.RED);
        }

        // System.out.println(dieVal1);
        // System.out.println(dieVal2);
        // System.out.println(dieVal1 + dieVal2);

        if(numberOfBets > 0 && money > 0)
            rollBtn.setDisable(false);
        else
        {
            promptText.setText("The Game is Over!");
            promptText.setTextFill(Color.RED);
        }
   
    }

    
}
