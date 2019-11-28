/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.bll;

import javafx.scene.media.*;
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author Rizvan
 */
public class MusicPlayer
{

    private double currentVolume;

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
        currentVolume = value; 
    }

}
