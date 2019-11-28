/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui;


import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes_group4.be.Song;
import mytunes_group4.bll.SongManager;
import mytunes_group4.dal.DalException;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Rizvan
 */
public class TunesModel
{
    private ObservableList<Song> allSongs;
    private SongManager songManager;

    public TunesModel() throws IOException, DalException
    {
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
        
        
    }
    
    public ObservableList<Song> getSongs()
    {
        return allSongs;
    }
    
    
    
    // TODO: DETTE SKAL FLYTTES TIL BLL OG LAVES OM TIL AT KUNNE SPILLE FLERE SANGE
    private MediaPlayer mediaPlayer;
    private Media media; 
    
    /**
     * Plays the music when pressed
     */
    public void playMusic(){
        
        String songTest = "Music/Pop/popsong.mp3";
            
        media = new Media(new File(songTest).toURI().toString());  
             
        mediaPlayer = new MediaPlayer(media);
            
        mediaPlayer.play();
    }
    
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
    
}
