/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.be;

import java.io.Serializable;

/**
 *
 * @author Rizvan & NLens
 */
public class Song implements Serializable
{
    private int id; 
    private String songName;
    private String artistName;
    private String genre;
    private int duration; //How long the songs playtime is
    private String path;
    
    /**
     * Parameters when creating a new song to the database.
     * @param id //each song has it's own individual id
     * @param songName
     * @param artistName
     * @param genre
     * @param duration //calculated
     */
    public Song(int id, String songName, String artistName, String genre, int duration)
    {
        this.id = id; 
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
    }

    public Song(String artistName, String songName, String genre, String path)
    {
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
    
    

    
    /**
     * 
     * @return song id
     */
    public int getId()
    {
        return id;
    } 

    /**
     *
     * @return song title
     */
    public String getSongName()
    {
        return songName;
    }

    /**
     *
     * @param songName sets the title of the song
     */
    public void setSongName(String songName)
    {
        this.songName = songName;
    }

    /**
     *
     * @return name of artist
     */
    public String getArtistName()
    {
        return artistName;
    }

    /**
     *
     * @param artistName sets name of the artist
     */
    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }

    /**
     *
     * @return genre of the song
     */
    public String getGenre()
    {
        return genre;
    }

    /**
     *
     * @param genre sets the genre of the song
     */
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    
    /**
     *
     * @param trackDuration determines how long the playtime id for a song
     */
    public void setDuration(int trackDuration) {
      duration = trackDuration;  
   }
    
   /**
    * 
    * @return  the playtime of the song
    */
   public int getDuration() {
      return duration;
   }
   
   /**
    * outputs String of the songs properties
    * @return 
    */
   @Override
   public String toString() {
      return songName + " by " + artistName + " (" + genre + ")";
   } 
}
