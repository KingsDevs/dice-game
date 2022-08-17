package com.dc;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EnterNameController {

    @FXML
    private Button doneBtn;

    @FXML
    private TextField nameField;

    @FXML
    void done(ActionEvent event) throws IOException 
    {
        String name = nameField.getText();

        if((name.isEmpty() || name.isBlank()))
            name = "No name";
        
        App.setPlayerName(name);

        Stage stage =  (Stage)doneBtn.getScene().getWindow();
        stage.close();
    }

}
