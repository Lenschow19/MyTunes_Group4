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
                String songName = rs.getString("songName");
                String artistName = rs.getString("artistName");
                String genre = rs.getString("genre");
                String path = rs.getString("path");

                Song son = new Song(artistName, songName, genre, path);
                allSongs.add(son);
            }

            return allSongs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }

    public Song addSong(String songName, String artistName, String genre, String path) throws DalException
    {
        try ( Connection con = dbCon.getConnection())
        {

            String sql = "INSERT INTO Song VALUES (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, songName);
            ps.setString(2, artistName);
            ps.setString(3, genre);
            ps.setString(4, path);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int songId = rs.getInt(1);
                    Song son = new Song (songName, artistName, genre, path);
                    return son;
                }
            }
            throw new DalException();
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
