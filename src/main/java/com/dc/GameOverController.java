package com.dc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;


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
        gameResultListView.getItems().addAll(mainController.getBetList());
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
