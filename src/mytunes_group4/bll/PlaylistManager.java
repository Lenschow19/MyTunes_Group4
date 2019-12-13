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

    /**
     * Gets all playlists in the database
     * @return A list of playlist objects
     * @throws Exception
     */
    public List<Playlist> getAllPlaylists() throws Exception
    {
        return playlistDbDao.getAllPlaylists();
    }
    
    public void updatePlaylist(Playlist playlist) throws Exception
    {
        playlistDbDao.updatePlaylist(playlist);
    }
    
    /**
     * Deletes a playlist object
     * @param playlist
     * @return true if playlist was deleted
     * @throws Exception
     */
    public boolean deletePlaylist(Playlist playlist) throws Exception
    {
        return playlistDbDao.deletePlaylist(playlist);
    }
    
    public Playlist createPlaylist(String name) throws Exception
    {
        Playlist playlist = playlistDbDao.createPlaylist(name);
        return playlist;
    }
    
    /**
     * Gets all songs in a given playlist 
     * @param playlistId
     * @return All song objects in a playlist
     * @throws Exception
     */
    public List<Song> getAllSongsInPlaylist(int playlistId) throws Exception
    {
        return playlistDbDao.getAllSongsInPlaylist(playlistId);
    }
    
    /**
     * Adds a song to a playlist in the database
     * @param playlist
     * @param song
     * @return true if song was added to playlist
     * @throws Exception
     */
    public boolean addSongToPlaylist(Playlist playlist, Song song) throws Exception
    {
        return playlistDbDao.addSongToPlaylist(playlist, song);
    }
    
    /**
     * Deletes a song from a playlist in the database
     * @param playlistId
     * @param songId
     * @return true if song was deleted from playlist
     * @throws Exception
     */
    public boolean deleteSongInPlaylist(int playlistId, int songId) throws Exception
    {
        return playlistDbDao.deleteSongInPlaylist(songId, playlistId);
    }
    
    public boolean changeSongPosition(int firstSongId, int secondSongId, int playlistId) throws Exception
    {
        return playlistDbDao.changeSongPosition(firstSongId, secondSongId, playlistId);
    }
}
