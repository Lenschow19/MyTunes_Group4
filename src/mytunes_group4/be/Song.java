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
    private int songId; 
    private String songName;
    private String artistName;
    private String genre;
    private double duration; //How long the songs playtime is
    private String path;
    
    /**
     * Parameters when creating a new song to the database.
     * @param songId
     * @param songName
     * @param artistName
     * @param genre
     * @param duration //calculated
     */
    public Song(int songId, String songName, String artistName, String genre, double duration, String path)
    {
        this.songId = songId; 
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
        this.path = path;
    }

    
    /**
     * 
     * @return song id
     */
    public int getId()
    {
        return songId;
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
    public void setDuration(double trackDuration) {
      duration = trackDuration;
   }
    
   /**
    * 
    * @return  the playtime of the song
    */
   public double getDuration() {
      return duration;
   }
   
   public final Duration getDuration() {
       // TODO if we want automatic count of length
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
