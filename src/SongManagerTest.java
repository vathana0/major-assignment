import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SongManagerTest {
    public  SongManager songManager;
    public Song a, b, c, d, e;
    public PlayList favorite;

    @BeforeEach
    void setUp() {
        // Initialized the song manager object
        songManager = new SongManager();
        
        // Create songs
        a = new Song("Frank Sinatra","My Way a ");
        b = new Song("Frank Sinatra","Fly me to the Moon b ");
        c = new Song("Bruno Mars","That's What I Like c ");
        d = new Song("Queen", "Bohemian Rhapsody d ");
        e = new Song("The Beatles", "Let it Be e ");
        
        // Add songs to Song object library
        songManager.addSongToLibrary(a);
        songManager.addSongToLibrary(b);
        songManager.addSongToLibrary(c);
        songManager.addSongToLibrary(d);
        songManager.addSongToLibrary(e);

        // Create playlist and add songs to the playlist
        favorite = new PlayList("favorite");
        favorite.addSong(a);
        favorite.addSong(b);
        favorite.addSong(c);
    }

    @Test
    void testAddSongsToLibrary() {
        // Check if the library contains the correct songs
        assertTrue(songManager.songLibrary.listOfSongs.contains(a));
        assertTrue(songManager.songLibrary.listOfSongs.contains(b));
        assertTrue(songManager.songLibrary.listOfSongs.contains(c));
        assertTrue(songManager.songLibrary.listOfSongs.contains(d));
        assertTrue(songManager.songLibrary.listOfSongs.contains(e));
    }
    // Adding sames song
    @Test
    void testAddSameSong() {
        // Check if the library contains the correct songs
        // This should not be allowed
        assertFalse(songManager.addSongToLibrary(a));
        assertFalse(songManager.addSongToLibrary(b));
        assertFalse(songManager.addSongToLibrary(c));
        assertFalse(songManager.addSongToLibrary(d));
        assertFalse(songManager.addSongToLibrary(e));


    }

    @Test
    void testCreatePlaylistAndAddSongs() {
        // Create playlist
        songManager.createPlayList("Classic Hits");
        songManager.addSongToPlayList(a, "Classic Hits");
        songManager.addSongToPlayList(b, "Classic Hits");
        songManager.addSongToPlayList(d, "Classic Hits");

        // Check that the playlist contains the expected songs
        PlayList classicHits = songManager.playlistManager.getPlayList("Classic Hits");
        assertNotNull(classicHits);
        assertEquals(3, classicHits.getSongPlayList().size());
        assertTrue(classicHits.getSongPlayList().contains(a));
        assertTrue(classicHits.getSongPlayList().contains(b));
        assertTrue(classicHits.getSongPlayList().contains(d));
    }
    @Test
    void testAddSongByNameToPlaylist() {
        // Create playlist using a name
        songManager.createPlayList("Hits Playlist");
    
        // Add songs by their name
        assertTrue(songManager.addSongByNameToPlaylist("My Way a", "Hits Playlist"));
        assertTrue(songManager.addSongByNameToPlaylist("Fly me to the Moon b", "Hits Playlist"));
        assertTrue(songManager.addSongByNameToPlaylist("That's What I Like c", "Hits Playlist"));
    
        // Fetch the playlist and check the songs have been added
        PlayList hitsPlaylist = songManager.playlistManager.getPlayList("Hits Playlist");
        assertNotNull(hitsPlaylist);
    
        assertEquals(3, hitsPlaylist.getSongPlayList().size());
        assertTrue(hitsPlaylist.getSongPlayList().contains(a));
        assertTrue(hitsPlaylist.getSongPlayList().contains(b));
        assertTrue(hitsPlaylist.getSongPlayList().contains(c));
    }

    @Test
    void testPlaySongsFromQueue() {
        // Enqueue songs
        songManager.enqueueSong(a);
        songManager.enqueueSong(b);
        songManager.enqueueSong(c);

        // Test playing the next song
        assertEquals(a, songManager.playCurrentSong()); 
        assertEquals(b, songManager.playNextSong()); 
        assertEquals(c, songManager.playNextSong()); 
        assertEquals(a, songManager.playNextSong()); // circular in nature 

        // Test playing the previous song
        assertEquals(c, songManager.playPreviousSong()); 
        assertEquals(b, songManager.playPreviousSong()); 
        assertEquals(a, songManager.playPreviousSong());
    }

    @Test
    void testShowSongsInPlayList() {
        // Check playlist has the correct songs
        favorite.showSongs();
        
        assertEquals(3, favorite.getSongPlayList().size());
        assertTrue(favorite.getSongPlayList().contains(a));
        assertTrue(favorite.getSongPlayList().contains(b));
        assertTrue(favorite.getSongPlayList().contains(c));
    }
    @Test
    void testQueueAfterSettingPlaylist() {
        // Step 1: Set the playlist
        songManager.playPlaylist(favorite.name);  // favorite has songs a, b, c
        assertEquals(3, favorite.getSongPlayList().size());  // Verify playlist size

        // Step 2: Verify playlist is set correctly
        assertEquals(a, songManager.playCurrentSong());  // Play first song from playlist
        assertEquals(b, songManager.playNextSong());     // Play next song from playlist
        assertEquals(c, songManager.playNextSong());     // Play next song from playlist
        assertEquals(a, songManager.playNextSong());     // circular by nature

        songManager.clearPlayer();

        // Step 3: Now enqueue songs after setting the playlist
        songManager.enqueueSong(d);  // New song "d"
        songManager.enqueueSong(e);  // New song "e"

        // Step 4: Ensure the queue is a new DoublyLinkedList, and does not contain previous playlist songs
        assertEquals(d, songManager.playCurrentSong());  // Play the first enqueued song "d"
        assertEquals(e, songManager.playNextSong());     // Play the next enqueued song "e"      
    }


}
