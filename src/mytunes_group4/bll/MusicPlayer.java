/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.bll;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Rizvan
 */
public class MusicPlayer
{

    public void playMusic(String musicLocation)
    {
        File musicPath = new File(musicLocation);

        try
        {
            if (musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else
            {
                System.out.println("Can't find file.");
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

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
