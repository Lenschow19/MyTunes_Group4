/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Rizvan
 */
public class TunesModel
{
    // TODO: DETTE SKAL FLYTTES TIL BLL OG LAVES OM TIL AT KUNNE SPILLE FLERE SANGE
    private MediaPlayer mediaPlayer;
    private Media media; 
    
    /**
     * Plays the music when pressed
     */
    public void playMusic(){
          
        String path = "C:\\Users\\mads_\\OneDrive\\Documents\\GitHub\\MyTunes_Group4\\Music\\Pop\\popsong.mp3";  
            
        media = new Media(new File(path).toURI().toString());  
             
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
