
package mytunes_group4.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import mytunes_group4.be.*;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class TunesViewController implements Initializable
{
    
    private TunesModel tModel;
    


    @FXML
    private ListView<Playlist> Playlists;
    @FXML
    private ListView<SongsInPlaylist> SongsInPlaylist;
    @FXML
    private ListView<Song> SongList;
    
    private TunesModel model = new TunesModel();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        
        
        try {
        tModel = new TunesModel();
        SongList.setItems(tModel.getSongs());
        } catch (Exception ex)
        {
            System.out.println("Something went wrong");
            ex.printStackTrace();
        }
                
        
    }    

    @FXML
    private void addNewPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void editPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void deletePlaylist(ActionEvent event)
    {
    }

    @FXML
    private void deleteSongInPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void addNewSong(ActionEvent event)
    {
    }

    @FXML
    private void editSong(ActionEvent event)
    {
    }

    @FXML
    private void deleteSong(ActionEvent event)
    {
    }

    @FXML
    private void playSong(ActionEvent event)
    {
        model.playMusic();
    }

    @FXML
    private void pauseSong(ActionEvent event)
    {
        model.pauseMusic();
    }

    @FXML
    private void stopSong(ActionEvent event)
    {
        model.stopMusic();
    }

    @FXML
    private void addSongToPlaylist(ActionEvent event)
    {
    }
    
    @FXML
    private Slider volumeSlider;
    
}
