package OnlineRadioDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InvalidSongException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                String[] tokens = reader.readLine().split(";");
                if (tokens.length != 3) {
                    throw new InvalidSongException();
                }
                String artist = tokens[0];
                String name = tokens[1];
                String length = tokens[2];
                Song song = new Song(artist, name, length);
                songs.add(song);
                System.out.println("Song added.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.printf("Songs added: %d%n", songs.size());

        int totalSeconds = songs.stream()
                .map(s -> s.getMinutes() * 60 + s.getSeconds())
                .mapToInt(Integer::valueOf)
                .sum();

        int hours = totalSeconds / 3600;
        totalSeconds %= 3600;
        int minutes = totalSeconds / 60;
        totalSeconds %= 60;

        System.out.printf("Playlist length: %dh %dm %ds%n",
                hours, minutes, totalSeconds);
    }
}
