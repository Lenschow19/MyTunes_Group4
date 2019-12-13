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
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private SelectionModel<Song> selModel;
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
    private ListView<Song> SongsInPlaylist;
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
    private TableView<Song> songTableView;
    @FXML
    private TableColumn<Song, String> viewSongTitle;
    @FXML
    private TableColumn<Song, String> viewSongArtist;
    @FXML
    private TableColumn<Song, String> viewSongGenre;
    @FXML
    private TableView<Playlist> playlistTableView;
    @FXML
    private TableColumn<Playlist, String> viewName;
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
        
        SongsInPlaylist.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) ->
        {
            selModel = SongsInPlaylist.getSelectionModel();
        });
        
        songTableView.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) ->
        {
            selModel = songTableView.getSelectionModel();
        });
        
        //initialize our song selection and volumeslider
        setSongSelection();
        setSongSelectionInPlaylist();
        volumeSliderSetup();

        
        //initialize our table of songs and playlists
        try
        {
            tModel = new TunesModel();
            songTable();
            playlistTable();
        } catch (IOException ex)
        {
            Logger.getLogger(TunesViewController.class.getName()).log(Level.SEVERE, null, ex);
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

    }
    
    private void songTable() throws IOException, DalException
    {
        viewSongTitle.setCellValueFactory(new PropertyValueFactory<> ("songName"));
        viewSongArtist.setCellValueFactory(new PropertyValueFactory<> ("artistName"));
        viewSongGenre.setCellValueFactory(new PropertyValueFactory<> ("genre"));
        songTableView.getColumns().clear();
        songTableView.setItems(tModel.getSongs());
        songTableView.getColumns().addAll(viewSongTitle, viewSongArtist, viewSongGenre);
    }
    
    private void playlistTable() throws IOException, DalException, Exception
    {
        viewName.setCellValueFactory (new PropertyValueFactory<> ("name"));
        playlistTableView.getColumns().clear();
        playlistTableView.setItems(tModel.getPlaylistList());
        playlistTableView.getColumns().addAll(viewName);

        
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
        tModel.setChosenPlaylist(playlistTableView.getSelectionModel().getSelectedItem());
        tModel.deletePlaylist(tModel.getChosenPlaylist());
        
        /*Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("A Deletion Confirmation");
        alert.setHeaderText("Are you sure you want to delete:");
        alert.setContentText(playlistTableView.getSelectionModel().getSelectedItem() + "?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            playlistmanager.deletePlaylist(playlist);
        } else {
            alert.close();
        }*/
        
    }

    @FXML
    private void deleteSongInPlaylist(ActionEvent event) throws Exception
    {
        tModel.setChosenSong(SongsInPlaylist.getSelectionModel().getSelectedItem());
        tModel.deleteSongInPlaylist(tModel.getChosenSong());
    }
    
    @FXML
    private void addSongToPlaylist(ActionEvent event) throws Exception
    {
        tModel.setChosenSong(songTableView.getSelectionModel().getSelectedItem());
        tModel.addSongToPlaylist(playlistTableView.getSelectionModel().getSelectedItem(), tModel.getChosenSong());
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
        alert.setContentText(songTableView.getSelectionModel().getSelectedItem() + "?");
        
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
        btnPause.setText("Pause");
        isPlaying = true;
        song = selModel.getSelectedItem();
        setMusicPlayerPath();
        mediaPlayer.play();
        currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");

        //shows the duration of the song in seconds in gui (works but doesnt look nice)
        mediaPlayer.setOnReady(() ->
        {
            lblTime.setText("" + media.getDuration().toSeconds());
        });

        //plays the next song automatically after first song has finished
        mediaPlayer.setOnEndOfMedia(() ->
        {
            selModel.selectNext();
            setMusicPlayerPath();
            mediaPlayer.play();
            currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");

            //if the song is the last one on the list, it will play the first song after finished
            if (songTableView.getItems().size() == selModel.getSelectedIndex() + 1 || SongsInPlaylist.getItems().size() == selModel.getSelectedIndex() + 1)
            {
                selModel.selectFirst();
                setMusicPlayerPath();
                mediaPlayer.play();
                currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");
            }
        });
        
    }

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
    }

    

    /**
     * Displays the selected song from the list
     */
    private void setSongSelection()
    {
        ssTitle.setEditable(false);
        ssArtist.setEditable(false);

        songTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        songTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>()
        {
            @Override
            public void changed(ObservableValue<? extends Song> arg0, Song oldValue, Song newValue)
            {
                
                
                if (newValue != null)
                {
                    ssTitle.setText(newValue.getSongName());
                    ssArtist.setText(newValue.getArtistName());
                }
            }
        });

    }
    
    private void setSongSelectionInPlaylist()
    {
        ssTitle.setEditable(false);
        ssArtist.setEditable(false);
        
        SongsInPlaylist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        SongsInPlaylist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>()
        {
            @Override
            public void changed(ObservableValue<? extends Song> arg0, Song oldValue, Song newValue)
            {

                if (newValue != null)
                {
                    ssTitle.setText(newValue.getSongName());
                    ssArtist.setText(newValue.getArtistName());
                }
            }
        });
    }

    private void setSongsInPlaylistSelection()
    {
        playlistTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        playlistTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>()
        {
            @Override
            public void changed(ObservableValue<? extends Playlist> arg0, Playlist oldValue, Playlist newValue)
            {
                if (newValue != null)
                {
                    
                    tModel.setChosenPlaylist(playlistTableView.getSelectionModel().getSelectedItem()); 
                    
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
        song = selModel.getSelectedItem();
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
        selModel.selectPrevious();
        isPlaying = true;
        setMusicPlayerPath();
        mediaPlayer.play();
        currentSongPlaying.setText(song.getArtistName() + " - " + song.getSongName() + " is currently playing");
    }

    @FXML
    private void playNextSong(ActionEvent event)
    {
        selModel.selectNext();
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

    @FXML
    private void moveSongUp(ActionEvent event) throws Exception
    {
        tModel.setChosenSong(SongsInPlaylist.getSelectionModel().getSelectedItem());
        tModel.setChosenPlaylist(playlistTableView.getSelectionModel().getSelectedItem());
        int index;
        index = tModel.moveSong(1, tModel.getChosenSong(), tModel.getChosenPlaylist());
        if (index != -1)
        {
            SongsInPlaylist.getSelectionModel().select(index);
        }
    }

    @FXML
    private void moveSongDown(ActionEvent event) throws Exception
    {
        tModel.setChosenSong(SongsInPlaylist.getSelectionModel().getSelectedItem());
        tModel.setChosenPlaylist(playlistTableView.getSelectionModel().getSelectedItem());
        int index;
        index = tModel.moveSong(-1, tModel.getChosenSong(), tModel.getChosenPlaylist());
        if (index != -1)
        {
            SongsInPlaylist.getSelectionModel().select(index);
        }
    }

    

  

   
}
