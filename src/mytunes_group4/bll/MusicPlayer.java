/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.bll;

import java.io.File;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Rizvan
 */
public class MusicPlayer
{

    private MediaPlayer mediaPlayer;
    private Media media;
    private double currentVolume;

    public MusicPlayer()
    {
        currentVolume = 1.0;
    }

    public void playMusic()
    {
        String path = "Music/Pop/popsong.mp3";

        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    public void pauseMusic()
    {
        mediaPlayer.pause();
    }
    
    public void stopMusic()
    {
        mediaPlayer.stop();
    }

    public double getVolume()
    {
        return currentVolume;
    }

    public void setVolume(double value)
    {
        currentVolume = value;
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

}
