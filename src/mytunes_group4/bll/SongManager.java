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
   
    public List<Song> search(String query) throws DalException
    {
        List<Song> searchBase = songDB.getAllSongs();
        List<Song> result = new ArrayList<>();

        for (Song song : searchBase)
        {
            if (song.getSongName().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(song);
            }
        }
        return result;
    }
}
