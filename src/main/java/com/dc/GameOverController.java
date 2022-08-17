package com.dc;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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
        Stage stage =  (Stage)viewHighScoreBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void retry(ActionEvent event) throws IOException {
        App.setRoot("main");
        quit(new ActionEvent());
    }

    @FXML
    void viewHighScore(ActionEvent event) {

    }

}
