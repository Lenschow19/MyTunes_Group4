/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.model;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes_group4.be.Song;
import mytunes_group4.bll.SongManager;
import mytunes_group4.dal.DalException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes_group4.be.Playlist;
import mytunes_group4.bll.MusicPlayer;
import mytunes_group4.bll.PlaylistManager;


/**
 *
 * @author Rizvan
 */
public class TunesModel
{

    private Song song = null;
    private SongManager songManager;
    private PlaylistManager pm;
    private MusicPlayer mp;
    private Double currentVolume;

    private Playlist chosenPlaylist;
    private Song chosenSong;

    private ObservableList<Song> songsShownInPlaylist = FXCollections.observableArrayList();
    public ObservableList<Playlist> playlists = FXCollections.observableArrayList();
    public ObservableList<Song> songs = FXCollections.observableArrayList();

    public TunesModel() throws IOException, DalException, Exception
    {
        pm = new PlaylistManager();
        songManager = new SongManager();
    }

    /**
     * Gets and displays a list of all playlists in the database
     *
     * @return An observable list of playlist objects
     * @throws IOException
     * @throws Exception
     */
    public ObservableList<Playlist> getPlaylistList() throws IOException, Exception
    {
        playlists.clear();
        playlists.addAll(pm.getAllPlaylists());
        playlists.sort(new Comparator<Playlist>()
        {
            @Override
            public int compare(Playlist arg0, Playlist arg1)
            {
                return arg0.getPlaylistId() - arg1.getPlaylistId();
            }

        });
        return playlists;

    }

    /**
     * Gets and displays a list of all songs in the database
     *
     * @return An observable list of song objects
     * @throws IOException
     * @throws DalException
     */
    public ObservableList<Song> getSongs() throws IOException, DalException
    {
        songs.addAll(songManager.getAllSongs());
        songs.sort(new Comparator<Song>()
        {
            @Override
            public int compare(Song arg0, Song arg1)
            {
                return arg0.getSongId() - arg1.getSongId();
            }

        });
        return songs;
    }

    /**
     * Gets all songs in the selected playlist
     *
     * @return An observable list of song objects
     * @throws Exception
     */
    public ObservableList<Song> getSongsInPlaylist() throws Exception
    {
        songsShownInPlaylist.clear();
        songsShownInPlaylist.addAll(pm.getAllSongsInPlaylist(chosenPlaylist.getPlaylistId()));
        return songsShownInPlaylist;
    }

    /**
     * Sets the selected playlist
     *
     * @param chosenPlaylist
     */
    public void setChosenPlaylist(Playlist chosenPlaylist)
    {
        this.chosenPlaylist = chosenPlaylist;
    }

    /**
     * Gets the selected playlist
     *
     * @return A playlist object
     */
    public Playlist getChosenPlaylist()
    {
        return chosenPlaylist;
    }

    /*
    Updates playlist
    */
    public void updatePlaylist(Playlist selectedPlaylist) throws Exception
    {
        pm.updatePlaylist(selectedPlaylist);
        if (playlists.remove(selectedPlaylist))
        {
            playlists.add(selectedPlaylist);
            playlists.sort(new Comparator<Playlist>()
            {
                @Override
                public int compare(Playlist arg0, Playlist arg1)
                {
                    return arg0.getPlaylistId() - arg1.getPlaylistId();
                }

            });
        }
    }

    /*
    Add playlist to DB and playlistList
    */
    public void createPlaylist(String name) throws Exception
    {
        Playlist playlist = pm.createPlaylist(name);
        playlists.add(playlist);
        playlists.sort(new Comparator<Playlist>()
        {
            @Override
            public int compare(Playlist arg0, Playlist arg1)
            {
                return arg0.getPlaylistId() - arg1.getPlaylistId();
            }

        });
    }

    /**
     * Deletes a playlist object from the database
     *
     * @param playlist
     * @throws Exception
     */
    public void deletePlaylist(Playlist playlist) throws Exception
    {
        pm.deletePlaylist(chosenPlaylist);
        getPlaylistList();
    }

    /**
     * Searches for the given query in the list of songs
     *
     * @param query
     * @throws IOException
     * @throws DalException
     */
    public void search(String query) throws IOException, DalException
    {
        if (query.isEmpty())
        {
            songs.clear();
            songs.addAll(songManager.getAllSongs());
        }

        if (!query.isEmpty())
        {
            List<Song> searchedSongs = songManager.searchSongs(query);
            songs.clear();
            songs.addAll(searchedSongs);
        }
    }

    /**
     * Adds the selected song to the selected playlist
     *
     * @param playlist
     * @param song
     * @throws Exception
     */
    public void addSongToPlaylist(Playlist playlist, Song song) throws Exception
    {
        pm.addSongToPlaylist(playlist, song);
        playlist.addSongToPlaylist(chosenSong);
        getSongsInPlaylist();
    }

    /**
     * Sets the selected song
     *
     * @param selectedSong
     */
    public void setChosenSong(Song selectedSong)
    {
        chosenSong = selectedSong;
    }

    /**
     * Gets the selected song
     *
     * @return A song object
     */
    public Song getChosenSong()
    {
        return chosenSong;
    }

    /**
     * Removes the selected song from the selected playlist
     *
     * @param selectedSong
     * @throws Exception
     */
    public void deleteSongInPlaylist(Song selectedSong) throws Exception
    {
        pm.deleteSongInPlaylist(chosenSong.getSongId(), chosenPlaylist.getPlaylistId());
        chosenPlaylist.getSongsInPlaylist().remove(chosenSong);
        getSongsInPlaylist();

    }

    public int moveSong(int i, Song selectedSong, Playlist selectedPlaylist) throws Exception
    {
        List<Song> songHolder = new ArrayList<>();
        songHolder.addAll(songsShownInPlaylist);

        int index = songHolder.indexOf(selectedSong);
        if (index - i >= 0 && index - i + 1 <= songHolder.size())
        {
            Song ssong = songHolder.get(index);
            songHolder.set(index, songHolder.get(index - i));
            songHolder.set(index - i, ssong);
            songsShownInPlaylist.clear();
            songsShownInPlaylist.addAll(songHolder);
            try
            {
                if (pm.changeSongPosition(songHolder.get(index).getSongId(), songHolder.get(index - i).getSongId(), selectedPlaylist.getPlaylistId()))
                {
                    return index;
                }
            } catch (Exception ex)
            {
                Logger.getLogger(TunesModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1; 
    }

    /*
    Add song to db and songlist
    */
    public Song addSong(String songName, String artistName, String genre, String path) throws Exception
    {
        song = songManager.addSong(artistName, songName, genre, path);
        songs.add(song);
        return song;
    }

    /*
    Delete song from db and songlist
    */
    public void deleteSong(Song selectedSong) throws Exception
    {
        songManager.deleteSong(selectedSong);
        songs.remove(selectedSong);
    }

    /*
    Edit song
    */
    public void editSong(Song selectedSong) throws Exception
    {
        songManager.editSong(selectedSong);
        getSongs();
    }

}
