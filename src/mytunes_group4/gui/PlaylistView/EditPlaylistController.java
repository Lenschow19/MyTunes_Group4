/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.PlaylistView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    TunesModel tModel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            tModel = new TunesModel();
        } catch (DalException ex)
        {
            Logger.getLogger(EditPlaylistController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Logger.getLogger(EditPlaylistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Closes window
     */
    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    /*
    Saves playlist
     */
    @FXML
    private void savePlaylist(ActionEvent event)
    {

        try
        {

            Playlist playlist = new Playlist(TunesViewController.getPlaylistId, txtPlaylistTitle.getText().trim());
            tModel.updatePlaylist(playlist);

            Stage stage = (Stage) savePlaylist.getScene().getWindow();
            stage.close();
        } catch (DalException ex)
        {
            Logger.getLogger(EditPlaylistController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Logger.getLogger(EditPlaylistController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
