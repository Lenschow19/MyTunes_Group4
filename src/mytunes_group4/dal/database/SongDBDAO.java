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
import mytunes_group4.dal.ISongDAO;
import mytunes_group4.be.Song;

/**
 *
 * @author M
 */
public class SongDBDAO implements ISongDAO
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
                int id = rs.getInt("songId");
                String songName = rs.getString("songName");
                String artistName = rs.getString("artistName");
                String genre = rs.getString("genre");
                String path = rs.getString("path");

                Song son = new Song(id, artistName, songName, genre, path);
                allSongs.add(son);
            }

            return allSongs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }

   @Override
    public Song addSong(String songName, String artistName, String genre, String path) throws DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            
            String sql = "INSERT INTO Song VALUES (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, songName);
            ps.setString(2, artistName);
            ps.setString(3, genre);
//            ps.setDouble(4, duration);
            ps.setString(4, path);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int songId = rs.getInt(1);
                    Song son = new Song (songId, songName, artistName, genre, path);
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

    @Override
    public void deleteSong(Song song) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int songId = song.getSongId();
            String sql = "DELETE FROM Song WHERE songId=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, songId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new DalException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException();
        }
    }

    @Override
    public void editSong(Song song) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int songId = song.getSongId();
//            String songName = song.getSongName();
//            String artistName = song.getArtistName();
//            String genre = song.getGenre();
//            String path = song.getPath();

            String songName = new String();
            String artistName = new String();
            String genre = new String();
            String path = new String();
            String sql = "UPDATE Song SET songName=?, artistName=?, genre=?, path=? WHERE songId=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, songName);
            ps.setString(2, artistName);
            ps.setString(3, genre);
            ps.setString(4, path);
            ps.setInt(5, songId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new DalException();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                throw new Exception();
            } catch (Exception ex1) {
                Logger.getLogger(SongDBDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}
