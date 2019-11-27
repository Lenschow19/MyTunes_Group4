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
    // DETTE SKAL FLYTTES TIL BLL OG LAVES OM TIL AT KUNNE SPILLE FLERE SANGE
    private MediaPlayer mediaPlayer;
    private Media media; 
    
    public void playMusic(){
          
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
    
   
    
}
