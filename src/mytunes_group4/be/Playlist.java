/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NLens
 */
public class Playlist
{

    private int playlistId;
    private String name; //name of playlist
    private List<Song> songs;

    /**
     * Properties a playlist should have and can display.
     *
     * @param playlistId //each playlist have it's own id
     * @param name
     */
    public Playlist(int playlistId, String name)
    {
        this.playlistId = playlistId;
        this.name = name;
        songs = new ArrayList<>();

    }

    /**
     *
     * @return id of the playlist
     */
    public int getPlaylistId()
    {
        return playlistId;
    }

    /**
     *
     * @return name of the playlist
     */
    public String getName()
    {
        return name;
    }

    /**
     * Naming the playlist
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return A list of song objects in the playlist
     */
    public List<Song> getSongsInPlaylist()
    {
        return songs;
    }

    /**
     * Adds a song object to a playlist
     * @param song
     */
    public void addSongToPlaylist(Song song)
    {
        songs.add(song);
    }

    @Override
    public String toString()
    {
        return playlistId + " " + name;
    }

}
