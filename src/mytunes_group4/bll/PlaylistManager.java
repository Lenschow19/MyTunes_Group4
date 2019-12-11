/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.bll;

import java.io.IOException;
import java.util.List;
import mytunes_group4.be.Playlist;
import mytunes_group4.be.Song;
import mytunes_group4.dal.database.PlaylistDBDAO;

/**
 *
 * @author mads_
 */
public class PlaylistManager
{

    private PlaylistDBDAO playlistDbDao;

    public PlaylistManager() throws Exception
    {
        playlistDbDao = new PlaylistDBDAO();
    }

    public List<Playlist> getAllPlaylists() throws Exception
    {
        return playlistDbDao.getAllPlaylists();
    }
    
    public void updatePlaylist(Playlist playlist) throws Exception
    {
        playlistDbDao.updatePlaylist(playlist);
    }
    
    public void deletePlaylist(Playlist playlist) throws Exception
    {
        playlistDbDao.deletePlaylist(playlist);
    }
    
    public Playlist createPlaylist(String name) throws Exception
    {
        Playlist playlist = playlistDbDao.createPlaylist(name);
        return playlist;
    }
    
    public List<Song> getAllSongsInPlaylist(int playlistId) throws Exception
    {
        return playlistDbDao.getAllSongsInPlaylist(playlistId);
    }
    
    public boolean addSongToPlaylist(Playlist playlist, Song song) throws Exception
    {
        return playlistDbDao.addSongToPlaylist(playlist, song);
    }
    
    public boolean deleteSongInPlaylist(int songId, int playlistId) throws Exception
    {
        return playlistDbDao.deleteSongInPlaylist(songId, playlistId);
    }
}
