/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.bll;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import mytunes_group4.be.Song;

/**
 *
 * @author Rizvan
 */
public class MusicPlayer
{
    private String musicLocation;
    private Object length;
    private Double currentVolume; 

    private MediaPlayer mediaPlayer;

    public void playMusic(String musicLocation)
    {
        Media song = new Media(new File(musicLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(song);
        
        if (mediaPlayer != null)
        {
            mediaPlayer.seek((Duration) length);
            mediaPlayer.play();
        } else
        {
            mediaPlayer.play();
            
        }
    }
    
    
    
   
    public void pauseMusic(String musicLocation)
    {
        Media song = new Media(new File(musicLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(song);
        
        mediaPlayer.pause();
        length = mediaPlayer.getCurrentTime();
    }

    public void stopMusic(String musicLocation)
    {
        Media song = new Media(new File(musicLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(song);
        
        mediaPlayer.stop();
    }

    

    public MusicPlayer()
    {
        currentVolume = 1.0;
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

    

}
