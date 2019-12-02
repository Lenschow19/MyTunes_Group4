/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.dal.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mytunes_group4.be.Playlist;

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
                int id = rs.getInt("id");
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

    public Playlist createPlaylist(String name) throws Exception
    {

        String sql = "INSERT INTO Playlist (name) VALUES (?);";
        Connection con = dbc.getConnection();
        try ( PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, name);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next())
            {
                id = rs.getInt(1);

            }
            Playlist pl = new Playlist(id, name);
            return pl;
        } catch (SQLException ex)
        {
            throw new Exception("Could not create playlist", ex);
        }

    }

    public void deletePlaylist(Playlist playlist) throws Exception
    {
        try ( Connection con = dbc.getConnection())
        {
            int id = playlist.getId();
            String sql = "DELETE FROM Playlist WHERE id=?";
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
            int id = playlist.getId();
            String name = playlist.getName();
            String sql = "UPDATE Playlist SET name=? WHERE id=?";
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
}
