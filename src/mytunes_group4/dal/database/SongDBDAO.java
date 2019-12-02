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
import mytunes_group4.dal.ISongDAO;
import mytunes_group4.be.Song;
import mytunes_group4.dal.DalException;

/**
 *
 * @author M
 */
public class SongDBDAO implements ISongDAO{
    

    private DatabaseConnector dbCon;

    public SongDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }
    

    /*public final ObserveableMap<java.lang.String,java.lang.Object> getMetadata()
    {
        //TODO if we want automatic info extraction
    }*/

//    public final ObserveableMap<java.lang.String,java.lang.Object> getMetadata()
//    {
//        //TODO if we want automatic info extraction
//    }

    
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
                int songId = rs.getInt("songId");
                double duration = rs.getDouble("duration");
                String path = rs.getNString("path");
                Song son = new Song(songId, artistName, songName, genre, duration, path);
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
    public void deleteSong(Song song) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = song.getId();
            String sql = "DELETE FROM movie WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
    public void updateSong(Song song) throws DalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeAllSongs(List<Song> allSongs, String fileName) throws DalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Song createSong(String artistName, String songName, String genre, double duration, String path) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO movie VALUES (?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, artistName);
            ps.setString(2, songName);
            ps.setString(3, genre);
            ps.setDouble(4, duration);
            ps.setString(5, path);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) // <-- Remember to do this!!
                {
                    int id = rs.getInt(1);
                    Song mov = new Song(id, artistName, songName, genre, duration, path);
                    return mov;
                }
            }
            throw new DalException();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException();
        }
    }
}
