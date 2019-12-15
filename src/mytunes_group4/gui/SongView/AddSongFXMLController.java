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
import mytunes_group4.be.Song;
import mytunes_group4.gui.MainView.TunesViewController;
import mytunes_group4.gui.model.TunesModel;

/**
 * FXML Controller class
 *
 * @author M
 */
public class AddSongFXMLController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtPath;
    private TunesModel tMod;
    private  TunesViewController tcon;
    @FXML
    private Button cancelWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /*
    Initialize tunesModel
    Add song
    */
    @FXML
    private void addSong(ActionEvent event) throws Exception {
        try {
            tMod = new TunesModel();

            tMod.addSong(txtTitle.getText().trim(), txtArtist.getText().trim(), txtGenre.getText().trim(), txtPath.getText().trim());
            
            Stage stage = (Stage) add.getScene().getWindow();
            stage.close();
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    /*
    Close stage
    */
    @FXML
    private void cancelSongWindow(ActionEvent event) {
        
        Stage stage = (Stage) cancelWindow.getScene().getWindow();
        stage.close();
    }

    /*
    Browse files
    */
    @FXML
    private void browseFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String fileAsString = file.toString();

            txtPath.setText(fileAsString);
        } else {
            txtPath.setText(null);
        }
    }

    }
    

