/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.dal.database;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mytunes_group4.be.Song;
import mytunes_group4.dal.DalException;

/**
 *
 * @author M
 */
public class SongDBDAO
{

    private DatabaseConnector dbCon;
    
    public SongDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    
    public List<Song> getAllSongs() throws DalException
    {
        
        try
        {
            dbCon = new DatabaseConnector();
        } catch (IOException ex)
        {
            Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Song;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Song> allSongs = new ArrayList<>();
            while (rs.next())
            {
                String title = rs.getString("songName");
                String artist = rs.getString("artistName");
                String genre = rs.getString("genre");
                
                Song song = new Song(title, artist, genre);
                allSongs.add(song);
            }
            
            return allSongs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
        
        
    }
    
    public void deleteSong(Song song)
    {
        
    }
    
    public void editSong(Song song)
    {
        
    }
    
}
