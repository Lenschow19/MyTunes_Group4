/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.PlaylistView;

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
 * @author M
 */
public class AddPlaylistController implements Initializable {

    @FXML
    private TextField txtPlaylistTitle;
    @FXML
    private Button cancel;
    @FXML
    private Button savePlaylist;
    private TunesModel tModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    /*
    Close stage
    */
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    
    /*
    Initialize tunesModel
    Add playlist
    */
    @FXML
    private void addNewPlaylist(ActionEvent event) throws Exception {
        try{
            tModel = new TunesModel();
            tModel.createPlaylist(txtPlaylistTitle.getText());

            Stage stage = (Stage) savePlaylist.getScene().getWindow();
            stage.close();
    
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
}
