package mytunes_group4.gui.MainView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import mytunes_group4.be.*;
import mytunes_group4.bll.PlaylistManager;
import mytunes_group4.bll.SongManager;
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
    private Playlist selectedPlaylist;

    private Playlist playlist;
    private PlaylistManager playlistmanager;
    private SongManager songmanager;
    private boolean isPlaying;
    private double currentVolume;


    @FXML
    private ListView<Playlist> Playlists;
    @FXML
    private ListView<Song> SongsInPlaylist;
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
    private Label currentSongPlaying;
    @FXML
    private Button btnPause;
    @FXML
    private Label lblTime;
    @FXML
    private Button newSong;

    /**
     * Initializes the controller class.
     *
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
            setSongsInPlaylistSelection();
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

        

        //tModel.volumeSliderSetup(volumeSlider);

      //  volumeSliderSetup();


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
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("A Deletion Confirmation");
        alert.setHeaderText("Are you sure you want to delete:");
        alert.setContentText(Playlists.getSelectionModel().getSelectedItem() + "?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            playlistmanager.deletePlaylist(playlist);
        } else {
            alert.close();
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
    private void deleteSong(ActionEvent event) throws Exception
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("'Delete Song' I Choose You");
        alert.setHeaderText("Are you sure you want to delete:");
        alert.setContentText(SongList.getSelectionModel().getSelectedItem() + "?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES){
            tModel.deleteSong(song);
        } else {
            alert.close();
        }
    }


    

    @FXML
    private void playSong(ActionEvent event) //Plays selected song
    {
        if (song == null)
        {
            song = SongList.getSelectionModel().getSelectedItem();
            setMusicPlayerPath();
            mediaPlayer.play();
        } else if (song != SongList.getSelectionModel().getSelectedItem())
        {
            setMusicPlayerPath();
            mediaPlayer.play();
        }
    }
        
    /*@FXML
    private void playSong(ActionEvent event) //Plays selected song
    {
        btnPause.setText("Pause");
        isPlaying = true;
        song = SongList.getSelectionModel().getSelectedItem();
        setMusicPlayerPath();
        mediaPlayer.play();
        currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");


    }*/

    @FXML
    private void pauseSong(ActionEvent event)
    {
        if (isPlaying == true)
        {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            {
                mediaPlayer.pause();
                btnPause.setText("Resume");
            } else
            {
                mediaPlayer.play();
                btnPause.setText("Pause");
            }
        } else
        {
            System.out.println("Play a song first");
        }
    }

    @FXML
    private void stopSong(ActionEvent event)
    {
        mediaPlayer.stop();
        currentSongPlaying.setText("Nothing is currently playing");
        isPlaying = false;
        currentSongPlaying.setText("Nothing is currently playing");
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

    private void setSongsInPlaylistSelection()
    {
        Playlists.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Playlists.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>()
        {
            @Override
            public void changed(ObservableValue<? extends Playlist> arg0, Playlist oldValue, Playlist newValue)
            {
                if (newValue != null)
                {
                    
                    tModel.setChosenPlaylist(Playlists.getSelectionModel().getSelectedItem()); 
                    
                    try
                    {
                        SongsInPlaylist.setItems(tModel.getSongsInPlaylist());
                    } catch (Exception ex)
                    {
                        Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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


        
        //shows the duration of the song in seconds in gui (works but doesnt look nice)
        mediaPlayer.setOnReady(() ->
        {
            lblTime.setText("" + media.getDuration().toSeconds());

        });

        
        //plays the next song automatically after first song has finished
        mediaPlayer.setOnEndOfMedia(() ->
        {
            SongList.getSelectionModel().selectNext();
            setMusicPlayerPath();
            mediaPlayer.play();
        });

    }

    @FXML
    private void changeVolume(MouseEvent event)
    {


    }

    @FXML
    private void playPreviousSong(ActionEvent event)
    {
        SongList.getSelectionModel().selectPrevious();
        isPlaying = true;
        setMusicPlayerPath();
        mediaPlayer.play();
        currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");
    }

    @FXML
    private void playNextSong(ActionEvent event)
    {
        SongList.getSelectionModel().selectNext();
        isPlaying = true;
        setMusicPlayerPath();
        mediaPlayer.play();
        currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");

    }

    public double getVolume()
    {
        return currentVolume;
    }

    public void setVolume(double value)
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.setVolume(value);
        }
        currentVolume = value;
    }
    public void volumeSliderSetup()
    {
        currentVolume = 1.0;
        volumeSlider.setValue(getVolume() * volumeSlider.getMax());
        volumeSlider.valueProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                setVolume(volumeSlider.getValue() / volumeSlider.getMax());
                if (volumeSlider.getValue() == 0)
                {
                }
            }
        });

    }

    @FXML
    private void confirmationDeletePopUpPlaylist(MouseEvent event) throws Exception
    {
        
    }

    @FXML
    private void confirmationDeletePopUpSong(MouseEvent event)
    {
       

    }

    

    /*private void setSongsOnListView(Playlist playlist)
    {
        tModel.setSongsInPlaylist(playlist);

    }*/

   
}
