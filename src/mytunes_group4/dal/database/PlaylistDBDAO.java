/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mytunes_group4.be.Playlist;
import mytunes_group4.be.Song;

/**
 *
 * @author mads_
 */
public class PlaylistDBDAO
{

    private DatabaseConnector dbc;

    public PlaylistDBDAO() throws Exception
    {
        dbc = new DatabaseConnector();
    }

    

    /**
     * Gets all playlists in the database
     * @return A list of playlist objects
     * @throws Exception
     */
    public List<Playlist> getAllPlaylists() throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {

            String sql = "SELECT * FROM Playlist;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Playlist> allPlaylists = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("playlistId");
                String name = rs.getString("name");

                Playlist pl = new Playlist(id, name);
                allPlaylists.add(pl);
            }
            return allPlaylists;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    /**
     * Gets all songs in a given playlist 
     * @param playlistId
     * @return All song objects in a playlist
     * @throws Exception
     */
    public List<Song> getAllSongsInPlaylist(int playlistId) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {

            String sql = "SELECT * FROM Song "
                    + "INNER JOIN SongsInPlaylist ON SongsInPlaylist.songId = Song.songId "
                    + "INNER JOIN Playlist ON Playlist.playlistId = SongsInPlaylist.playlistId "
                    + "WHERE Playlist.playlistId = " + playlistId + ";";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            List<Song> songs = new ArrayList<>();

            while (rs.next())
            {
                Song song = new Song(
                        rs.getInt("songId"),
                        rs.getString("songName"),
                        rs.getString("artistName"),
                        rs.getString("genre"),
                        rs.getString("path")
                );

                songs.add(song);
            }
            return songs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    public Playlist createPlaylist(String name) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            String sql = "INSERT INTO Playlist VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Playlist pl = new Playlist(id, name);
                    return pl;
                }
            }
            throw new Exception();

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    

    public void updatePlaylist(Playlist playlist) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            int playlistId = playlist.getPlaylistId();
            String name = playlist.getName();
            String sql = "UPDATE Playlist SET name=? WHERE playlistId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, playlistId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1)
            {
                throw new IOException();
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    /**
     * Adds a song to a playlist
     * @param playlist
     * @param song
     * @return true if a song was added to a playlist
     * @throws Exception
     */
    public boolean addSongToPlaylist(Playlist playlist, Song song) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            String sql = "INSERT INTO SongsInPlaylist VALUES (?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int songId = song.getSongId();
            int playlistId = playlist.getPlaylistId();
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            if (ps.executeUpdate() == 1)
            {
                return true;
            } else
            {
                throw new Exception("Could not add song to playlist");
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }
    
    /**
     * Deletes a playlist object
     * @param playlist
     * @return true if playlist was deleted
     * @throws Exception
     */
    public boolean deletePlaylist(Playlist playlist) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            int playlistId = playlist.getPlaylistId();
            String sql = "DELETE FROM Playlist WHERE playlistId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);
            if (ps.executeUpdate() == 1)
            {
                return true;
            } else
            {
                throw new Exception();
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }

    }

    /**
     * Deletes a song from a playlist in the database
     * @param playlistId
     * @param songId
     * @return true if song was deleted from a playlist
     * @throws Exception
     */
    public boolean deleteSongInPlaylist(int playlistId, int songId) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            int sapId = getSapId(playlistId, songId);
            String sql = "DELETE SongsInPlaylist WHERE sapId=?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, sapId);

            if (ps.executeUpdate() == 1)
            {
                return true;
            } else
            {
                throw new Exception();
            }

        } catch (SQLServerException ex)
        {
            
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Gets a SongAndPlaylistId from the database
     * @param playlistId
     * @param songId
     * @return 
     * @throws Exception
     */
    private int getSapId(int playlistId, int songId) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            String sql = "SELECT sapId FROM SongsInPlaylist WHERE playlistId=? AND songId=?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt("sapId");
            return id;

        } catch (SQLServerException ex)
        {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }
    
    /**
     *
     * @param firstSongId
     * @param secondSongId
     * @param playlistId
     * @return
     * @throws Exception
     */
    public boolean changeSongPosition(int firstSongId, int secondSongId, int playlistId) throws Exception
    {
        try (Connection con = dbc.getConnection())
        {
            int firstSapId = getSapId(playlistId, firstSongId);
            int secondSapId = getSapId(playlistId, secondSongId);
            
            String sql = "UPDATE SongsInPlaylist SET songId=? WHERE sapId=?;"
                    + "UPDATE SongsInPlaylist SET songId=? WHERE sapId=?;";
                    
           
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, secondSongId);
            ps.setInt(2, firstSapId);
            ps.setInt(3, firstSongId);
            ps.setInt(4, secondSapId);
            
            if (ps.executeUpdate() == 1)
            {
                return true; 
            }
            
            else
            {
                throw new Exception();
            }
            
        } catch (SQLServerException ex)
        {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    
}
