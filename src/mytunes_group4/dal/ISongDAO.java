/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.dal;

import java.util.List;
import mytunes_group4.be.Song;

/**
 *
 * @author M
 */
public interface ISongDAO
{
  
    Song createSong(String artistName, String songName) throws DalException;
    
    void deleteSong(Song song) throws DalException;

    List<Song> getAllSongs() throws DalException;

    void updateSong(Song song) throws DalException;

    void writeAllSongs(List<Song> allSongs, String fileName) throws DalException;
    

}
