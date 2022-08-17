package com.dc;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    @FXML
    private Label moneyLeftLabel;

    private MainController mainController;

    private ArrayList<HighScore> highScores;

    public void setMainControllerandMoneyLeft(MainController mainController) throws IOException
    {
        this.mainController = mainController;
        gameResultListView.getItems().addAll(mainController.getBetList());

        int moneyLeft = mainController.getMoney();

        moneyLeftLabel.setText("Money Left: " + moneyLeft);
        playerNameLabel.setText("Player Name: " + App.getPlayerName());

        highScores = HighScore.getHighscores();
        HighScore currentPlayer = new HighScore(App.getPlayerName(), moneyLeft);

        if(highScores == null)
        {
            highScores = new ArrayList<HighScore>();
            highScores.add(currentPlayer);
        }
        else
            highScores.add(currentPlayer);
        
        HighScore.setHighScores(highScores);
         
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
    void viewHighScore(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(App.loadFXMLloader("highScoreView"));
        Parent root = loader.load();

        HighScoreController highScoreController = loader.getController();
        highScoreController.updateHighScoreListView();

        App.createModal(root, viewHighScoreBtn.getScene().getWindow(), "High Scores");
    }

}
