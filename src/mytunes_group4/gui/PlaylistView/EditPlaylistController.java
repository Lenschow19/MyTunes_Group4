/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.PlaylistView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes_group4.dal.DalException;
import mytunes_group4.gui.MainView.TunesViewController;
import mytunes_group4.gui.model.TunesModel;
import mytunes_group4.be.Playlist;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class EditPlaylistController implements Initializable
{
    
    @FXML
    private TextField txtPlaylistTitle;
    @FXML
    private Button savePlaylist;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

 
    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void savePlaylist(ActionEvent event) throws DalException, Exception {
        
        TunesModel tmod = new TunesModel();
        
        Playlist playlist = new Playlist(TunesViewController.getPlaylistId, txtPlaylistTitle.getText().trim());
        tmod.updatePlaylist(playlist);
        
        Stage stage = (Stage) savePlaylist.getScene().getWindow();
        stage.close();
    }
    
}
