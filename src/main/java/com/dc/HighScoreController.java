package com.dc;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class HighScoreController 
{

    @FXML
    private ListView<HighScore> highScoreListView;

    public void updateHighScoreListView() throws IOException
    {
        ArrayList<HighScore> highScores = HighScore.getHighscores();
        highScoreListView.getItems().addAll(highScores);
    }

}
