package OnlineRadioDatabase;

public class InvalidSongException extends Exception {
    public InvalidSongException() {
        super("Invalid song.");
    }
    public InvalidSongException(String message) {
        super(message);
    }
}
