/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.be;

/**
 *
 * @author NLens
 */
public class Playlist
{
    private String name; //name of playlist
    private int totalDuration; //Displaying duration of all songs in the playlist
    private int songCounter; //Displaying number of songs in the playlist
    
    public Playlist(String name, int totalDuration, int songCounter)
    {
        this.name = name;
        this.totalDuration = totalDuration;
        this.songCounter = songCounter;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
  
    
}