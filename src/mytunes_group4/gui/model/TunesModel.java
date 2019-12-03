/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.model;


import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes_group4.be.Song;
import mytunes_group4.bll.SongManager;
import mytunes_group4.dal.DalException;
import java.io.File;
import java.util.Comparator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mytunes_group4.be.Playlist;
import mytunes_group4.bll.MusicPlayer;
import mytunes_group4.bll.PlaylistManager;

/**
 *
 * @author Rizvan
 */
public class TunesModel
{

    private ObservableList<Song> allSong;
    private SongManager songManager;
    private PlaylistManager pm;
    private MusicPlayer mp;
    
    public TunesModel() throws IOException, DalException, Exception
    {
        this.pm = new PlaylistManager();
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
        
        
    }
    
    private ObservableList<Playlist> playlists = FXCollections.observableArrayList(); 
    
    public ObservableList<Playlist> getPlaylistList() throws IOException, Exception
    {
        playlists.addAll(pm.getAllPlaylists());
        playlists.sort(new Comparator<Playlist>()
            {
                @Override
                public int compare(Playlist arg0, Playlist arg1)
                {
                    return arg0.getId() - arg1.getId();
                }

            });
        return playlists; 
        
    }
    
    public void updatePlaylist(Playlist selectedPlaylist) throws Exception
    {
        pm.updatePlaylist(selectedPlaylist);
        if (playlists.remove(selectedPlaylist))
        {
            playlists.add(selectedPlaylist);
            playlists.sort(new Comparator<Playlist>()
            {
                @Override
                public int compare(Playlist arg0, Playlist arg1)
                {
                    return arg0.getId() - arg1.getId();
                }

            });
        }
    }
    
    public void deletePlaylist(Playlist selectedPlaylist) throws Exception
    {
        pm.deletePlaylist(selectedPlaylist);
        playlists.remove(selectedPlaylist);
    }
    
    public void createPlaylist(String name) throws Exception
    {
        Playlist playlist = pm.createPlaylist(name);
        playlists.add(playlist);
        playlists.sort(new Comparator<Playlist>()
            {
                @Override
                public int compare(Playlist arg0, Playlist arg1)
                {
                    return arg0.getId() - arg1.getId();
                }

            });
    }

   

    // DETTE SKAL FLYTTES TIL BLL OG ÆNDRES TIL AT KUNNE SPILLE FLERE SANGE
    private MediaPlayer mediaPlayer;
    private Media media;

    public void playMusic()
    {
        String musicLocation = "Music/Pop/popsong.mp3";

        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playMusic(musicLocation);
        
        //media = new Media(new File(path).toURI().toString());
        //mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.play();
        
       
    }


    private ObservableList<Song> allSongs;
    

    
    
    public ObservableList<Song> getSongs()
    {
        return allSongs;
    }
    
    
    
    // TODO: DETTE SKAL FLYTTES TIL BLL OG LAVES OM TIL AT KUNNE SPILLE FLERE SANGE
    
    
    /**
     * Plays the music when pressed
     */
    
    
    /**
     * Pausing the music when pressed
     */

    public void pauseMusic()
    {
        mediaPlayer.pause();
    }

    
    /**
     * Stops the music when pressed
     */

    public void stopMusic()
    {
        mediaPlayer.stop();
    }


    // doesn't work :(
    public void changeVolume(Slider vS)
    {
        vS.setValue(mediaPlayer.getVolume() * 100);
        vS.valueProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                mediaPlayer.setVolume(vS.getValue() / 100);                
            }
        });
    }

    public void search(String query) throws IOException, DalException
    {
        if (query.isEmpty())
        {
            allSong.clear();
            allSong.addAll(songManager.getAllSongs());
        } else
        {
            allSong.clear();
            allSong.addAll(songManager.search(query));
        }
    }

}
