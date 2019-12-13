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
import mytunes_group4.gui.model.TunesModel;

/**
 * FXML Controller class
 *
 * @author mads_
 */
public class AddPlaylistController implements Initializable
{
    private Button savePlaylist;
    @FXML
    private TextField txtPlaylistTitle;

    private TunesModel tMod;
    @FXML
    private Button cancel;
    @FXML
    private Button addPlaylist;

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
        try {
            tMod = new TunesModel();
            String name = txtPlaylistTitle.getText().trim();
            tMod.createPlaylist(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Stage stage = (Stage) savePlaylist.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeWindow(ActionEvent event)
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}
