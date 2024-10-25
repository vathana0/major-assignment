package app.manager;
import java.util.ArrayList;
import app.model.*;
import app.util.*;
public class PlaylistManager {
    public ArrayList<Playlist> listOfPlayLists;

    // Constructor
    public PlaylistManager() {
        listOfPlayLists = new ArrayList<>();
    }

    // METHOD: Create a playlist by name
    public boolean createPlaylist(String name) {
        if (playlistExists(name)) {
            System.out.println("Playlist with name '" + name + "' already exists.");
            return false;
        }

        Playlist newPlaylist = new Playlist(name);
        listOfPlayLists.add(newPlaylist);
        System.out.println("Playlist '" + name + "' has been created.");
        return true;
    }

    // METHOD: Adding a playlist using a playlist object
    public boolean addPlaylist(Playlist playlist) {
        if (playlist == null) {
            System.out.println("Cannot add a null playlist.");
            return false;
        }

        if (playlistExists(playlist.name)) {
            System.out.println("Playlist with name '" + playlist.name + "' already exists.");
            return false;
        } else {
            listOfPlayLists.add(playlist);
            System.out.println("Playlist '" + playlist.name + "' has been added.");
            return true;
        }
    }

    // METHOD: Remove a playlist by name
    public boolean removePlaylist(String name) {
        Playlist playlistToRemove = findPlaylistbyName(name);
        if (playlistToRemove == null) {
            System.out.println("Playlist '" + name + "' does not exist.");
            return false;
        }
        listOfPlayLists.remove(playlistToRemove);
        System.out.println("Playlist '" + name + "' has been removed.");
        return true;
    }
    
    // METHOD : Remove a playlist object
    public boolean removePlaylist(Playlist playlist) {
        if (playlist == null) {
            System.out.println("Cannot remove a null playlist.");
            return false;
        }

        // Using ArrayList Method
        if (!listOfPlayLists.remove(playlist)) {
            System.out.println("Playlist '" + playlist.getName() + "' does not exist.");
            return false;
        }
        System.out.println("Playlist '" + playlist.getName() + "' has been removed.");
        return true;
    }
    

    // Helper Function: Check if a playlist exists
    public boolean playlistExists(String playlistName) {
        return findPlaylistbyName(playlistName) != null;
    }

    // METHOD: Add a song to a playlist by name
    public boolean addSongToPlayList(Song song, String playlistName) {
        Playlist playlist = findPlaylistbyName(playlistName);
        if (playlist == null) {
            System.out.println("Can't add song to a null or non-existent playlist");
            return false;
        }

        playlist.addSong(song);
        return true;
    }


    //Helper: Method for getting specific playlist
    public Playlist findPlaylistbyName(String playlistName) {
        for (Playlist playlist : listOfPlayLists){
            // find the exact match
            if (playlist.name.equalsIgnoreCase(playlistName)) {  
                return playlist;  // Return the matching playlist
            }
        }
        return null; 
    }

    // METHOD: GET All the song in the specific playlist
    public CircularDoublyLinkedList getSongPlayList(String playlistName) {
        Playlist playlist = findPlaylistbyName(playlistName);
        if (playlist != null) {
            return playlist.songList;
        }
        System.out.println("Playlist '" + playlistName + "' not found.");
        return null;
    }

    // Display all the avialible Playlist
    public void showPlayList() {
        if (listOfPlayLists.isEmpty()) {
            System.out.println("No playlists available.");
        } else {
            System.out.println("Available playlists:");
            for (int i = 0 ; i < listOfPlayLists.size(); i++){
                Playlist playlist = listOfPlayLists.get(i);
                System.out.println(i + " " + playlist.getName());
            }
        }
    }

}

