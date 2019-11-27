/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.be;

import static javafx.beans.binding.Bindings.length;

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
    
    
    public Song(int id, String songName, String artistName, String genre, int duration)
    {
        this.id = id; 
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
        this.duration = duration;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSongName()
    {
        return songName;
    }

    public void setSongName(String songName)
    {
        this.songName = songName;
    }

    public String getArtistName()
    {
        return artistName;
    }

    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    
    public void setDuration(int h, int m, int s) {
      duration = (h*3600 + m*60 + s);
      if(h==0) {
         duration = (m*60+s);
      }   
   }
   public int getDuration() {
      return duration;
   }
    
    @Override
   public String toString() {
      return "Title: " + getSongName()+ ", Artist: " + getArtistName() + ", Genre: " + getGenre()+ ", Track Length: " + getDuration();
   } 
}
