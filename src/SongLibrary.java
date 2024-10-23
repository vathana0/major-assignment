import java.util.ArrayList;
public class SongLibrary {
    // The Song Library Class Roles is for handling all the possible song
    public ArrayList<Song> listOfSongs;

    // Constructor
    public SongLibrary(){
        listOfSongs = new ArrayList<Song>();
    }

    // addSong to Library
    public boolean addSongToLibrary(Song song) {
        if (song == null){
            System.out.println("Can't added a Null Song");
            return false;

        }else if(listOfSongs.contains(song)){
            System.out.println("Song Already Exist");
            return false;
        }
        listOfSongs.add(song);
        System.out.println("Added " + song.title + "to the Library");
        return true;
    }

    // adding song by string name instead
    public boolean addSongToLibrary(String songName) {
        if (songName == null){
            System.out.println("Can't added a Null");
            return false;

        }else if(findSongByName(songName) == null){
            System.out.println("Song is not found");
            return false;
        }
        Song foundSong = findSongByName(songName);
        listOfSongs.add(foundSong);
        System.out.println("Added " + foundSong.title + "to the Library");
        return true;
    }
    
    // Method to find a song by its exact name in the library
    // Method: Find a song by its exact name in the library (returns null if not found)
    public Song findSongByName(String songName) {
        if (songName == null || songName.isEmpty()) {
            return null; 
        }

        for (Song song : this.listOfSongs) {
            if (song.title.equalsIgnoreCase(songName)) {
                return song;
            }
        }
        return null;  // No match found
    }

    // Method: Find a song by artist (returns null if not found)
    public Song findSongByArtist(String artist) {
        if (artist == null || artist.isEmpty()) {
            return null; 
        }

        for (Song song : listOfSongs) {
            if (song.getArtist().equalsIgnoreCase(artist)) {
                return song;
            }
        }
        return null;  // No match found
    }

   // method to display all songs in the song library
    public void showSongs() {
        System.out.println("Songs in the library:");
        for (int i = 0 ; i < listOfSongs.size();i++){
            Song song = listOfSongs.get(i);
            System.out.println(i  + " " + song.toString());
        }
    }

    
}
