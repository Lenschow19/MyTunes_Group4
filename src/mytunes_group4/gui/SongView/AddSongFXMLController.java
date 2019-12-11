/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.SongView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes_group4.gui.model.TunesModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class AddSongFXMLController implements Initializable
{

     private TunesModel tMod;
    @FXML
    private Button cancelWindow;
    @FXML
    private Button addSong;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtPath;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void addSong(ActionEvent event) throws Exception {

        try {
            tMod = new TunesModel();
            String songName = txtTitle.getText().trim();
            String artistName = txtArtist.getText().trim();
            String genre = txtGenre.getText().trim();
            String path = txtPath.getText().trim();
            tMod.addSong(songName, artistName, genre, path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void cancelSongWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancelWindow.getScene().getWindow();
        stage.close();
    }
    
}
