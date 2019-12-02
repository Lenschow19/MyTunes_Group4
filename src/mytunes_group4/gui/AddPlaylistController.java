/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes_group4.dal.DalException;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class AddPlaylistController implements Initializable
{

    @FXML
    private Button savePlaylist;
    @FXML
    private TextField txtPlaylistTitle;

    private TunesModel tModel;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
      
    }

    @FXML
    private void addNewPlaylist(ActionEvent event) throws Exception
    {
        String name = txtPlaylistTitle.getText().trim();
        tModel.createPlaylist(name);

        /*Stage stage = (Stage) savePlaylist.getScene().getWindow();
        stage.close();*/
    }

    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
