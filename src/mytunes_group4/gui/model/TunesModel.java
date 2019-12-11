/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui.model;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes_group4.be.Song;
import mytunes_group4.bll.SongManager;
import mytunes_group4.dal.DalException;
import java.util.Comparator;
import java.util.List;
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
    private ObservableList<Song> allSong;
    private SongManager songManager;
    private PlaylistManager pm;
    private MusicPlayer mp;
    private Double currentVolume;
    
    private Playlist chosenPlaylist; 
    
    private ObservableList<Song> songsShownInPlaylist;

    public TunesModel() throws IOException, DalException, Exception
    {
        pm = new PlaylistManager();
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
        songsShownInPlaylist = FXCollections.observableArrayList();
        

    }

    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();
    private ObservableList<Song> songs = FXCollections.observableArrayList();
    

    public ObservableList<Playlist> getPlaylistList() throws IOException, Exception
    {
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
    
    public void setChosenPlaylist(Playlist chosenPlaylist)
    {
        this.chosenPlaylist = chosenPlaylist;
    }
    
    public Playlist getChosenPlaylist()
    {
        return chosenPlaylist; 
    }
    
    public ObservableList<Song> getSongsInPlaylist() throws Exception
    {
        songsShownInPlaylist.clear();
        songsShownInPlaylist.addAll(pm.getAllSongsInPlaylist(chosenPlaylist.getPlaylistId()));
        return songsShownInPlaylist; 
    }
    
   
    
    
    
    
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

    public void deletePlaylist(Playlist selectedPlaylist) throws Exception
    {
        pm.deletePlaylist(selectedPlaylist);
        playlists.remove(selectedPlaylist);
    }

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

    private MusicPlayer musicPlayer = new MusicPlayer();
    private String musicLocation = "Music/Pop/popsong.mp3";
    public void playMusic()
    {
        musicPlayer.playMusic(musicLocation);
    }

     /**
     * Pausing the music when pressed
     */
    public void pauseMusic()
    {
        musicPlayer.pauseMusic(musicLocation);
    }

    /**
     * Stops the music when pressed
     */
    public void stopMusic()
    {
        musicPlayer.stopMusic(musicLocation);
    }
    
    private ObservableList<Song> allSongs;

    
    
    

   
    
    // doesn't work :(
//    public void volumeSliderSetup(Slider volumeSlider)
//    {
//        volumeSlider.setValue(musicPlayer.getVolume() * volumeSlider.getMax());
//        volumeSlider.valueProperty().addListener(new InvalidationListener()
//        {
//            @Override
//            public void invalidated(Observable observable)
//            {
//                musicPlayer.setVolume(volumeSlider.getValue() / volumeSlider.getMax());
//                if (volumeSlider.getValue() == 0)
//                {
//                }
//            }
//        });
//
//    }

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

   

 

    public Song addSong(String songName, String artistName, String genre, String path) throws Exception {
        songManager.addSong(artistName, songName, genre, path);
        return song;
    }

    public void deleteSong(Song selectedSong) throws Exception {
        songManager.deleteSong(selectedSong);
        songs.remove(selectedSong);
    }

    public void editSong(Song selectedSong) throws Exception {
        songManager.editSong(selectedSong);
    }    
    

}
