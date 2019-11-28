/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes_group4.be.Song;
import mytunes_group4.bll.SongManager;
import mytunes_group4.dal.DalException;

/**
 *
 * @author Rizvan
 */
public class TunesModel
{
    private ObservableList<Song> allSongs;
    private SongManager songManager;

    public TunesModel() throws IOException, DalException
    {
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
        
        
    }
    
    public ObservableList<Song> getSongs()
    {
        return allSongs;
    }
    
    
    
    
}
