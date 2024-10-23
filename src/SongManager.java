import java.io.*;

public class SongManager {
    public SongLibrary songLibrary;
    public PlaylistManager playlistManager;
    public Player player;

    // Constructor
    public SongManager() {
        this.songLibrary = new SongLibrary();
        this.playlistManager = new PlaylistManager();
        this.player = new Player();
    }

    // Method: For playing a playlist
    public void playPlaylist(String playlistName) {
        PlayList playlist = playlistManager.findPlaylistbyName(playlistName);
        if (playlist != null) {
            player.setPlaylist(playlist);
            player.playCurrentSong();
        } else {
            System.out.println("Playlist not found.");
        }
    }

    // Method: For playing a playlist
    public void playPlaylist(PlayList playlist) {
        if (playlist != null) {
            player.setPlaylist(playlist);
            player.playCurrentSong();
        } else {
            System.out.println("Playlist not found.");
        }
    }

    // Method for adding Song to the Song Library
    public boolean addSongToLibrary(Song song) {
        return songLibrary.addSongToLibrary(song);
    }


    // Method to add a song by searching for its exact name and adding it to a playlist
    public boolean addSongByNameToPlaylist(String songName, String playlistName) {
        // Make sure that it exist in the first places
        Song foundSong = songLibrary.findSongByName(songName);
        if (foundSong != null) {
            // Add the song to the playlist
            playlistManager.addSongToPlayList(foundSong, playlistName);
            return true;  
        } else {
            System.out.println("No song found with the name: " + songName);
            return false;
        }
    }

    // Method to display all songs in the song library
    public void showSongs() {
        songLibrary.showSongs();
    }

    // Creating Playlists
    public void createPlayList(String name) {
        playlistManager.createPlaylist(name);
    }
    public void createPlayList(PlayList playlistName) {
        playlistManager.createPlaylist(playlistName);
    }
    
    //Removing Playlist
    public void removePlaylist(String name){
        playlistManager.removePlaylist(name);
    }
    public void removePlaylist(PlayList playlistName){
        playlistManager.removePlaylist(playlistName);
    }

    // Method to add a song to a specific playlist
    public void addSongToPlayList(Song song, String playlistName) {
        playlistManager.addSongToPlayList(song, playlistName);
    }

    // Method to enqueue a song for playing
    public void enqueueSong(Song song) {
        player.enqueueSong(song);
    }

    public void clearPlayer(){
        player.clearPlayer();
    }

    // Method to play the next song in the queue
    public Song playCurrentSong(){
        return player.playCurrentSong();
    }

    public Song playNextSong() {
       return player.playNextSong();
    }

    public Song playPreviousSong() {
        return player.playPreviousSong();
     }

    // Method to display all songs in a playlist
    public void showPlayListSong(String playlistName) {
        playlistManager.findPlaylistbyName(playlistName).showSongs();
    }
    public void showPlayListSong(PlayList playlistName) {
        playlistName.showSongs();;
    }

    // TODO: Show all the PlayList
    public void showPlayList() {
        playlistManager.showPlayList();
    }

    public void loadFromCSV(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String headerLine = reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String artist = parts[0];
            String  songName = parts[1];
            
            // Create the New Song Object
            Song loadSong = new Song(artist, songName);
            songLibrary.addSongToLibrary(loadSong);
        } 
        reader.close();
    }
    
}