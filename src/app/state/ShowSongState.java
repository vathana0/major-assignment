package app.state;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import app.*;
import app.manager.*;
import app.util.*;
import app.model.*;

/**
 * ShowSongState displays the list of songs available in the library and allows
 * the user to perform actions such as adding new songs, playing songs, and managing 
 * the queue.
 */
public class ShowSongState implements State {
    public MusicSimulator simulator;
    public SongManager songManager;
    public ConsoleManager console;

    // Constructor
    public ShowSongState(MusicSimulator simulator) {
        this.simulator = simulator;
        this.songManager = simulator.songManager;
        this.console = simulator.console;
    }

    /**
     * Displays the song library, the current queue, and the available commands for the user. 
     * Commands include options to play songs, add songs, go back, or exit.
     */
    @Override
    public void display() {
        console.clearConsole();
        ArrayList<Song> listOfSongs = songManager.songLibrary.listOfSongs;
        console.displaySongList(listOfSongs);

        // General commands for library management
        console.showMessage("\033[33mCommands:\033[0m");
        console.showMessage("\033[33m'play <number>'                   - Add song to queue.\033[0m");
        console.showMessage("\033[33m'play' or 'p'                     - Play the queue.\033[0m");
        console.showMessage("\033[33m'add \"<title>\" by \"<artist>\"'     - Add new song to library.\033[0m");
        console.showMessage("\033[33m'back' or 'q'                     - Go Back.\033[0m");
        console.showMessage("\033[33m'exit'                            - Return to main menu.\033[0m");
        console.showMessage("\nCurrent Queue:");

        // Display the queue and current song
        CircularDoublyLinkedList songQueue = songManager.getSongQueue();
        Node curSongNode = songManager.player.currentSongNode;
        console.displayQueue(songQueue, curSongNode);
    }

    /**
     * Handles user input, routing commands to actions related to displaying songs, playing songs,
     * adding songs, and queue management. This method checks command validity before executing actions.
     *
     * @param input The command input from the user.
     */
    @Override
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "q":
            case "back":
                simulator.goBack();
                break;

            case "exit": // Return to main menu
                simulator.clearStateStack();
                simulator.setState(new MainMenuState(simulator));
                break;

            case "play":
            case "p":
                simulator.setState(new PlayQueueState(simulator, null)); // Play the queue without a playlist
                break;

            default:
                // expecting add command
                String[] parts = input.split(" ");
                if (parts.length < 1) {
                    simulator.console.showMessage("\033[31mInvalid command format. Use 'add \"<title>\" by \"<artist>\"' or 'play <number>'.\033[0m");
                    simulator.console.waitForEnter();
                    return;
                }

                String action = parts[0].toLowerCase();
            
                switch (action) {
                    case "add":
                    case "a":
                        handleAddSong(input); // add command have different format
                        break;
                    case "play":
                    case "p":
                        // validated our play <number> command
                        if (!CommandParserUtil.validCommand(input,console)){
                            return;
                        }
                        String indexPart = parts[1];
                        handlePlaySong(indexPart);
                        break;
                    default:
                        simulator.console.showMessage("\033[31mInvalid Input.\033[0m");
                        simulator.console.waitForEnter();
                        break;
                }
        }
        display(); // continue displaying the states
    }

    /**
     * Adds a new song to the song library based on user input in the format 
     * 'add "title" by "artist"'. Using Regex to split into 3 groups
     * @param addCommand The user input command for adding a song.
     */
    public void handleAddSong(String addCommand) {
        Pattern pattern = Pattern.compile("(add|a)\\s+\"([^\"]+)\"\\s+by\\s+\"([^\"]+)\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(addCommand);

        if (matcher.matches()) {
            String title = matcher.group(2); 
            String artist = matcher.group(3);

            Song newSong = new Song(title, artist); // make the new songs
            songManager.addSongToLibrary(newSong); 
            console.showMessage("\033[32mSong '" + title + "' by '" + artist + "' has been added to the library.\033[0m");
        } else {
            console.showMessage("\033[31mInvalid format. Use 'add \"<song title>\" by \"<artist>\"'.\033[0m");
        }
        console.waitForEnter();
    }

    /**
     * Adds a song to the queue based on the song's index in the song library.
     * @param indexPart The index of the song to play, provided as a string.
     */
    public void handlePlaySong(String indexPart) {
        Integer songIndex = CommandParserUtil.parseIndexCommand(indexPart, simulator.console);
        if (songIndex == null){
            return; // Incorrect Input
        } 
        Song songToPlay = songManager.getSongAtIndex(songIndex);
        if (songToPlay != null) {
            songManager.enqueueSong(songToPlay);
        }
        console.waitForEnter(); // to display message
    }
}
