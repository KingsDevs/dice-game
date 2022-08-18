package com.dc;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class HighScoreController 
{

    @FXML
    private ListView<HighScore> highScoreListView;

    public void updateHighScoreListView() throws IOException
    {
        highScoreListView.getItems().clear();
        ArrayList<HighScore> highScores = HighScore.getHighscores();
        highScoreListView.getItems().addAll(highScores);
    }

    @FXML
    void clear(ActionEvent event) throws IOException 
    {
        HighScore.clearHighScores();
        highScoreListView.getItems().clear();
    }

}
