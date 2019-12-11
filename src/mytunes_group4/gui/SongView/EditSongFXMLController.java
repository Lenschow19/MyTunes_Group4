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
import mytunes_group4.be.Song;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class EditSongFXMLController implements Initializable
{
    private TunesModel tMod;
    @FXML
    private TextField txtUpdateTitle;
    @FXML
    private TextField txtUpdateArtist;
    @FXML
    private TextField txtUpdateGenre;
    @FXML
    private Button cancelWindow;
    @FXML
    private TextField txtUpdatePath;
    @FXML
    private Button editSong;
    @FXML
    private Button browseFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    


    @FXML
    private void cancelEditWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancelWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void editSong(ActionEvent event) throws Exception {
        
        try {
            tMod = new TunesModel();
            String songName = txtUpdateTitle.getText().trim();
            String artistName = txtUpdateArtist.getText().trim();
            String genre = txtUpdateGenre.getText().trim();
            String path = txtUpdatePath.getText().trim();
            
            Song song = new Song(songName, artistName, genre, path);
            tMod.editSong(song);
        } catch (IOException ex) {
            throw ex;
        }
    }

    @FXML
    private void browseFile(ActionEvent event) {
    }
    
}
