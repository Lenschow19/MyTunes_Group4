package mytunes_group4.gui.MainView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mytunes_group4.be.*;
import mytunes_group4.bll.MusicPlayer;
import mytunes_group4.dal.DalException;
import mytunes_group4.gui.model.TunesModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class TunesViewController implements Initializable
{
    private Song song = null;
    private String songPath;
    private TunesModel tModel;
    private MediaPlayer mediaPlayer;
    private Media media;

    @FXML
    private ListView<Playlist> Playlists;
    @FXML
    private ListView<SongsInPlaylist> SongsInPlaylist;
    @FXML
    private ListView<Song> SongList;
    @FXML
    private Slider volumeSlider;

    @FXML
    private TextField ssArtist;
    @FXML
    private TextField ssTitle;
    @FXML
    private TextField txtSongSearch;
    @FXML
    private Slider songProgress;
    @FXML
    private Label currentSongPlaying;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        setSongSelection();

        try
        {
            tModel = new TunesModel();
        } catch (DalException ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            SongList.setItems(tModel.getSongs());
        } catch (Exception ex)
        {
            System.out.println("Something went wrong");
            ex.printStackTrace();
        }

        try
        {
            Playlists.setItems(tModel.getPlaylistList());

        } catch (Exception ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tModel.volumeSliderSetup(volumeSlider);

    }

    @FXML
    private void addNewPlaylist(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes_group4/gui/PlaylistView/AddPlaylist.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editPlaylist(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes_group4/gui/PlaylistView/EditPlaylist.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deletePlaylist(ActionEvent event) throws Exception
    {
        Playlist selectedPlaylist = Playlists.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null)
        {
            try
            {
                tModel.deletePlaylist(selectedPlaylist);
            } catch (IOException ex)
            {
                Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void deleteSongInPlaylist(ActionEvent event)
    {
    }

    @FXML
    private void addNewSong(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes_group4/gui/SongView/AddSongFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setTitle("Add song");
        } catch (IOException ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editSong(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mytunes_group4/gui/SongView/EditSongFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setTitle("Edit song");
        } catch (IOException ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteSong(ActionEvent event)
    {
    }

    private boolean isPlaying;
    
    @FXML
    private void playSong(ActionEvent event) //Plays selected song
    {
        if (song == null)
        {
            song = SongList.getSelectionModel().getSelectedItem();
            setMusicPlayerPath();
            mediaPlayer.play();
            currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");
        }
        else if (song != SongList.getSelectionModel().getSelectedItem())
        {
            setMusicPlayerPath();
            mediaPlayer.play();
            currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");
        }
    }

    @FXML
    private void pauseSong(ActionEvent event)
    {
        tModel.pauseMusic();
    }

    @FXML
    private void stopSong(ActionEvent event)
    {
        mediaPlayer.stop();
        currentSongPlaying.setText("None is currently playing");
    }

    @FXML
    private void addSongToPlaylist(ActionEvent event)
    {
        
        
        
    }

    

    /**
     * Displays the selected song from the list
     */
    private void setSongSelection()
    {
        ssTitle.setEditable(false);
        ssArtist.setEditable(false);

        SongList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        SongList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>()
        {
            @Override
            public void changed(ObservableValue<? extends Song> arg0, Song oldValue, Song newValue)
            {
                if (newValue != null)
                {
                    ssTitle.setText(newValue.getArtistName());
                    ssArtist.setText(newValue.getSongName());
                }
            }
        });

    }

    @FXML
    private void handleSearch(KeyEvent event)
    {
        try
        {
            String query = txtSongSearch.getText().trim();
            tModel.search(query);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    private void setMusicPlayerPath()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
        }
        
        song = SongList.getSelectionModel().getSelectedItem();
        songPath = song.getPath();
        media = new Media(new File(songPath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);      
    }
    
    
    

    @FXML
    private void changeVolume(MouseEvent event)
    {
       
    }

    @FXML
    private void playPreviousSong(ActionEvent event)
    {
        SongList.getSelectionModel().selectPrevious();
        setMusicPlayerPath();
        mediaPlayer.play();
        currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");
    }

    @FXML
    private void playNextSong(ActionEvent event)
    {
        SongList.getSelectionModel().selectNext();
        setMusicPlayerPath();
        mediaPlayer.play();
        currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");
        
    }

}
