/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.be;

import java.util.ArrayList;

/**
 *
 * @author NLens
 */
public class Playlist
{
    private int playlistId; 
    private String name; //name of playlist
    private int totalDuration; //Displaying duration of all songs in the playlist
    private int songCounter; //Displaying number of songs in the playlist
    
    private ArrayList<Song> playlist;
    
    /**
     * Properties a playlist should have and can display.
     * @param id //each playlist have it's own id
     * @param name
     * @param totalDuration //calculated
     * @param songCounter //counter
     */
    public Playlist(int playlistId, String name)
    {
        this.playlistId = playlistId; 
        this.name = name;
        this.totalDuration = totalDuration;
        this.songCounter = songCounter;
    }
    
    

    /**
     *
     * @return id of the playlist
     */
    public int getPlaylistId()
    {
        return playlistId;
    }

    /**
     *
     * @return name of the playlist
     */
    public String getName()
    {
        return name;
    }

    /**
     * Naming the playlist
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return playlistId + " " + name;
    }
  
    
    
    
}
