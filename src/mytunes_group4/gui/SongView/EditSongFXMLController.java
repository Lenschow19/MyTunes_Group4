/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.SongView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mytunes_group4.gui.model.TunesModel;
import mytunes_group4.be.Song;
import mytunes_group4.gui.MainView.TunesViewController;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class EditSongFXMLController implements Initializable
{
    private TunesModel tMod;
    private TunesViewController tvc;
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
    
    
    public void EditSongFXMLController(){
        txtUpdateTitle.setText(TunesViewController.getTitle);
        txtUpdateArtist.setText(TunesViewController.getArtist);
        txtUpdateGenre.setText(TunesViewController.getGenre);
        txtUpdatePath.setText(TunesViewController.getPath);
    }
    

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
            
            Song song = new Song(TunesViewController.getSongId, txtUpdateTitle.getText().trim(), txtUpdateArtist.getText().trim(), txtUpdateGenre.getText().trim(), txtUpdatePath.getText().trim());
            tMod.editSong(song);
            
            Stage stage = (Stage) editSong.getScene().getWindow();
            stage.close();
            
        } catch (IOException ex) {
            throw ex;
        }
    }

    
    @FXML
    private void browseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String fileAsString = file.toString();

            txtUpdatePath.setText(fileAsString);
        } else {
            txtUpdatePath.setText(null);
        }
    }
    
}
