/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.dal.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytunes_group4.be.Song;
import mytunes_group4.dal.ISongDAO;
import mytunes_group4.dal.DalException;
/**
 *
 * @author M
 */
public class SongDAO implements ISongDAO 
{

    private static final String SONG_SOURCE = "data/songs.txt";

    @Override
    public List<Song> getAllSongs() throws DalException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(SONG_SOURCE))))
        {
            List<Song> allSongs = new ArrayList<>();

            while (true)
            {
                String aLineOfText = br.readLine();
                if (aLineOfText == null)
                {
                    break;
                } else if (!aLineOfText.isEmpty())
                {
                    try
                    {
                        String[] arrSong = aLineOfText.split(",");
                        int id = Integer.parseInt(arrSong[0].trim()); //Jeg læser ID'et.
                        int duration = Integer.parseInt(arrSong[1].trim()); //Jeg læser årstal.
                        String songName = arrSong[2].trim(); //Jeg læser titlen.
                        String artistName = arrSong[3].trim();
                        String genre = arrSong[4].trim();
                        String path = arrSong[5].trim();
                        // Add if commas in title, includes the rest of the string:
                        for (int i = 3; i < arrSong.length; i++) //Loop will only run if the array has a length of 3+
                        {
                            songName += "," + arrSong[i];
                        }
                        Song son = new Song(id, songName, artistName, genre, path);
                        allSongs.add(son);
                    } catch (Exception e)
                    {
                        //Skip row
                    }
                }
            }
            return allSongs;
        } catch (IOException ex)
        {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    @Override
    public void deleteSong(Song song) throws DalException
    {
        try
        {
            List<Song> allSongs = getAllSongs();
            if (allSongs.remove(song))
            {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(SONG_SOURCE))))
                {
                    for (Song son : allSongs)
                    {
                        bw.write(son.getSongId() + "," + son.getArtistName()+ "," + son.getSongName());
                        bw.newLine();
                    }
                }
            }
        } catch (IOException ex)
        {
            throw new DalException();
        }
    }

    @Override
    public void editSong(Song song) throws DalException
    {
        List<Song> allSongs = getAllSongs();
        if (allSongs.remove(song))
        {
            allSongs.add(song);
            //Maybe sort list
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(SONG_SOURCE))))
            {
                for (Song son : allSongs)
                {
                    bw.write(son.getSongId() + "," + son.getArtistName()+ "," + son.getSongName());
                    bw.newLine();
                }
            } catch (IOException ex)
            {
                Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DalException();
            }
        }
    }

//    @Override
//    public void writeAllSongs(List<Song> allSongs, String fileName) throws DalException
//    {
//        File listFile = new File(fileName);
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(listFile)))
//        {
//            oos.writeObject(allSongs);
//            oos.flush();
//        } catch (IOException ex)
//        {
//            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
//            throw new DalException();
//        }

//    }

    public static void main(String[] args)
    {
//        try
//        {
//            SongDAO songDao = new SongDAO();
//            List<Song> allSongs = songDao.getAllSongs();
//            songDao.writeAllSongs(allSongs, "data/songsAsObjects.txt");
//            System.out.println("Done");
//        } catch (DalException ex)
//        {
//            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public Song addSong(String artistName, String songName, String genre, String path) throws DalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
