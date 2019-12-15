/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mytunes_group4.be.*;
import mytunes_group4.dal.DalException;
import mytunes_group4.dal.database.SongDBDAO;

/**
 *
 * @author Rizvan
 */
public class SongManager
{
    private SongDBDAO songDB;
    private Song song;

    public SongManager() throws IOException
    {
        songDB = new SongDBDAO();
    }
    
    
    
    public List <Song> getAllSongs() throws IOException, DalException
    {
        return songDB.getAllSongs();
    }
   
    /**
     *
     * @param searchBase
     * @param query
     * @return
     */
    public static List<Song> search(List<Song> searchBase, String query)
    {
        List<Song> results = new ArrayList<>();
        for (Song song : searchBase)
        {
            if (song.getSongName().toLowerCase().contains(query.toLowerCase()) || song.getArtistName().toLowerCase().contains(query.toLowerCase()) || song.getGenre().toLowerCase().contains(query.toLowerCase())) 
            {
                results.add(song);
            }
        }
        return results;
    }

    public List<Song> searchSongs(String query) throws DalException, IOException
    {
        List<Song> allSongs = getAllSongs();
        allSongs = search(allSongs, query);
        return allSongs;
    }
    
    public void editSong(Song song) throws Exception {
        songDB.editSong(song);
    }

    public void deleteSong(Song song) throws Exception {
        songDB.deleteSong(song);
    }

    public Song addSong(String songName, String artistName, String genre, String path) throws Exception {
        song = songDB.addSong(artistName, songName, genre, path);
        return song;
    }

   
}
