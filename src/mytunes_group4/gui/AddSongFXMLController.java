/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class AddSongFXMLController implements Initializable
{

    @FXML
    private TextField txtSongTitle;
    @FXML
    private TextField txtArtistTitle;
    @FXML
    private TextField txtSongGenre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void addNewSong(ActionEvent event)
    {
    }

    @FXML
    private void cancelSongWindow(ActionEvent event)
    {
    }
    
}
