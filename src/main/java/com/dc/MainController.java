package com.dc;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

    private ArrayList<Bet> betList = new ArrayList<Bet>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        

        betList.clear();

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

        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, money, 1);
        betAmountSpinner.setValueFactory(spinnerValueFactory);
        
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

    public ArrayList<Bet> getBetList()
    {
        return betList;
    }
    
    public Integer getMoney()
    {
        return money;
    }

    private int rollDie(ImageView dieImageView)
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

    private void gameOver() throws IOException, InterruptedException
    {
        FXMLLoader loader = new FXMLLoader(App.loadFXMLloader("gameOverView"));
        Parent root = loader.load();

        GameOverController gameOverController = loader.getController();
        gameOverController.setMainControllerandMoneyLeft(this);
        
        Thread.sleep(800);
        App.createModal(root, rollBtn.getScene().getWindow(), "Game Over");
        

    }
    
    @FXML
    void roll(ActionEvent event) throws IOException, InterruptedException
    {
        rollBtn.setDisable(true);
        int bet;

        
        if(!betTextField.getText().isEmpty())
        {
            int betMoney = betAmountSpinner.getValue();
            setNumberOfBets(numberOfBets - 1);
            setMoney(money - betMoney);

            bet = Integer.parseInt(betTextField.getText());

            int dieVal1 = rollDie(dieImageView1);
            int dieVal2 = rollDie(dieImageView2);

            int diceTotalVal = dieVal1 + dieVal2;
            boolean won = true;
            String uo = "Over";

            int moneyWon = 0;

            if(underRadio.isSelected())
                uo = "Under";

            if(bet == diceTotalVal)
            {
                moneyWon = 5 * betMoney;
                
                promptText.setText("You Won! You Earned +"+ moneyWon +" Money");
                promptText.setTextFill(Color.GREEN);
                
                setMoney(moneyWon + money);
            }
            else if(bet > diceTotalVal && underRadio.isSelected())
            {
                moneyWon = 2 * betMoney;
                
                promptText.setText("The Dice Rolls Under "+ bet +"! You Earned +" + moneyWon + " Money");
                promptText.setTextFill(Color.GREEN);
                
                setMoney(moneyWon + money);
            }
            else if(bet < diceTotalVal && overRadio.isSelected())
            {
                moneyWon = 2 * betMoney;
                
                promptText.setText("The Dice Rolls Over "+ bet +"! You Earned +" + moneyWon + " Money");
                promptText.setTextFill(Color.GREEN);
                
                setMoney(moneyWon + money);
            }
            else
            {
                promptText.setText("You Lost!");
                promptText.setTextFill(Color.RED);
                won = false;
            }

            betList.add(new Bet(bet, uo, diceTotalVal, won));

            SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, money, 1);
            betAmountSpinner.setValueFactory(spinnerValueFactory);
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
            gameOver();
        }
   
    }

    
}
