/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.SongView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML
    private Button cancelWindow;
    @FXML
    private TextField txtPath;
    @FXML
    private Button lookForSong;

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
        Stage stage = (Stage) cancelWindow.getScene().getWindow();
        stage.close();
    }
    
}
