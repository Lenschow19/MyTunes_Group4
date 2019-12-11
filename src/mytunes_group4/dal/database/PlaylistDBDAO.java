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

    //test//
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
            String sql = "INSERT INTO Playlist VALUES (?);";
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

    public void deletePlaylist(Playlist playlist) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            int id = playlist.getPlaylistId();
            String sql = "DELETE FROM Playlist WHERE playlistId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate(sql);
            if (affectedRows != 1)
            {
                throw new Exception();
            }

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
            int id = playlist.getPlaylistId();
            String name = playlist.getName();
            String sql = "UPDATE Playlist SET name=? WHERE playlistId=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
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

    public boolean deleteSongInPlaylist(int songId, int playlistId) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            int sapId = getSapId(songId, playlistId);
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
            ex.printStackTrace();
            throw new Exception();
        }
    }

    private int getSapId(int songId, int playlistId) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            String sql = "SELECT sapId FROM SongsInPlaylist WHERE songId=? AND playlistId=?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, songId);
            ps.setInt(2, playlistId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            int id = rs.getInt("sapId");
            return id;

        } catch (SQLServerException ex)
        {
            ex.printStackTrace();
            throw new Exception();
        }
    }

    
}
