import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // TODO : MAKE INTERFACE THAT USE THE SONG MANAGER

        // Create an instance of SongManager
        SongManager songManager = new SongManager();

        // Create a bunch of random song
        Song a = new Song("Frank Sinatra","My Way");
        Song b = new Song( "Frank Sinatra","Fly me to the Moon");
        Song c = new Song( "Bruno Mars","That's What I Like");
        Song d = new Song("Queen", "Bohemian Rhapsody");
        Song e = new Song("The Beatles", "Let it Be");
        
        // Create a node and make a doublylinked list
        Node node1 = new Node(a);
        Node node2 = new Node(b);
        Node node3 = new Node(c);

        node1.next = node2;
        node2.prev = node1; 

        node2.next = node3; 
        node3.prev = node2;

        // // forward traversal
        // Node head = node1;
        // while (head != null){
        //     System.out.print(head.song.toString() + " -> ");
        //     head = head.next;
        // }
        // System.out.println();

        // // backward traversal
        // Node tail = node3; 
        // while ( tail != null){
        //     System.out.print(tail.song.toString() + " -> ");
        //     tail = tail.prev;
        // }
        // System.out.println();

        // Testing out the DoublylinkedList
        DoublyLinkedList list = new DoublyLinkedList();
        list.addNode(a);
        list.addNode(b);
        list.addNode(c);

        list.printList();
        
        // testing playlist
        PlayList favorite = new PlayList("favorite");
        favorite.songList = list;
        favorite.addSong(new Song("Queen","Bohemian Rhapsody" ));
        favorite.showSong();

        // Add songs to the library
        songManager.addSong(a);
        songManager.addSong(b);
        songManager.addSong(c);
        songManager.addSong(d);
        songManager.addSong(e);

        System.out.println("All songs in the library:");
        songManager.showSongs();
        System.out.println();

        // Create a new playlist and add songs
        songManager.createPlayList("Classic Hits");
        songManager.addSongToPlayList(a, "Classic Hits");
        songManager.addSongToPlayList(b, "Classic Hits");
        songManager.addSongToPlayList(d, "Classic Hits");

        // Show songs in the 'Classic Hits' playlist
        System.out.println("Songs in 'Classic Hits' playlist:");
        songManager.showPlayList("Classic Hits");
        System.out.println();

        // Add another playlist
        songManager.createPlayList("Pop Songs");
        songManager.addSongToPlayList(c, "Pop Songs");
        songManager.addSongToPlayList(e, "Pop Songs");

         // Show songs in 'Pop Songs' playlist
         System.out.println("Songs in 'Pop Songs' playlist:");
         songManager.showPlayList("Pop Songs");
         System.out.println();
 
         // Queue up some songs
         songManager.enqueueSong(a);
         songManager.enqueueSong(b);
         songManager.enqueueSong(c);
 
         // Play the queued songs one by one
         System.out.println("Playing songs from the queue:");
         songManager.playNextSong();                                                                // Plays "My Way"
         songManager.playNextSong();                                                                // Plays "Fly me to the Moon"
         songManager.playNextSong();                                                                // Plays "That's What I Like"
         songManager.playNextSong();                                                                // Queue should be empty now

         System.out.println("\nDoublyLinkedList operations:");

    }

    //     // TESTING OUT COMMAND LINE INTERFACE
    //     System.out.println("Welcome to the Song Manager!");
    //     System.out.println("Commands: play, next, previous, shuffle, exit");
    //     Scanner scanner = new Scanner(System.in);

    //     while (true) {
    //         clearConsole();
    //         System.out.println("\033[32mWelcome to the Terminal Page!\033[0m"); // Green text
    //         System.out.println("\033[33mType 'exit' to quit.\033[0m"); // Yellow text
    //         System.out.println("\033[34mYour options are:\033[0m"); // Blue text
    //         System.out.println("\033[36m1. Option A\033[0m"); // Cyan text
    //         System.out.println("\033[36m2. Option B\033[0m"); // Cyan text
    //         System.out.print("\033[35mChoose an option: \033[0m"); // Magenta text

    //         String command = scanner.nextLine();

    //         if (command.equalsIgnoreCase("exit")) {
    //             break;
    //         }

    //     }
    //     System.out.println("\033[31mGoodbye!\033[0m"); // Red text for goodbye
    //     scanner.close();
    // }

    // public static void clearConsole() {
    //     //print out a sort of new page
    //     System.out.print("\033[H\033[2J");
    //     System.out.flush();
    // }

}
