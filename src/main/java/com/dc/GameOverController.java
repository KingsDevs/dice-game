package com.dc;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class GameOverController 
{

    @FXML
    private ListView<Bet> gameResultListView;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Button viewHighScoreBtn;

    @FXML
    private AnchorPane anchorPane;

    private MainController mainController;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void playEntrance()
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), anchorPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(.2);
        fadeTransition.play();
    }

    @FXML
    void quit(ActionEvent event) {

    }

    @FXML
    void retry(ActionEvent event) {

    }

    @FXML
    void viewHighScore(ActionEvent event) {

    }

}
