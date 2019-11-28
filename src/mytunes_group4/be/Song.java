/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.be;

/**
 *
 * @author Rizvan & NLens
 */
public class Song
{
    private int id; 
    private String songName;
    private String artistName;
    private String genre;
    private int duration; //How long the songs playtime is
    
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

    public Song(String title, String artist, String genre)
    {
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
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
     * @param trackDuration determans how long the playtime id for a song
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
      return "Title: " + songName+ " | Artist: " + artistName + " | Genre: " + genre;
   } 
}
