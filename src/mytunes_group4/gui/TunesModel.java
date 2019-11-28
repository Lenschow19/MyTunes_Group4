/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui;

import java.io.File;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import mytunes_group4.bll.MusicPlayer;

/**
 *
 * @author Rizvan
 */
public class TunesModel
{

    private MusicPlayer mp;

    public TunesModel()
    {
        mp = new MusicPlayer();
    }

    // DETTE SKAL FLYTTES TIL BLL OG ÆNDRES TIL AT KUNNE SPILLE FLERE SANGE
    private MediaPlayer mediaPlayer;
    private Media media;

    public void playMusic()
    {
        String path = "C:\\Users\\mads_\\OneDrive\\Documents\\GitHub\\MyTunes_Group4\\Music\\Pop\\popsong.mp3";

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
