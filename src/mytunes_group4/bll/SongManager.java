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
    
    public void deleteSong(Song song) throws Exception
    {
        //SongDBDAO.deleteSong(song);
        throw new UnsupportedOperationException("not working at the moment. please choose different paths in life and come back later.");
    }
}
