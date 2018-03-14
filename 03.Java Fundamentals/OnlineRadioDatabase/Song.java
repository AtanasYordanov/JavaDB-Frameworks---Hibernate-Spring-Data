package OnlineRadioDatabase;

class Song{
    private String artist;
    private String name;
    private int minutes;
    private int seconds;

    public Song(String artist, String name, String length)
            throws InvalidArtistException, InvalidSongNameException,
            InvalidSongLengthException {
        this.setArtist(artist);
        this.setName(name);
        this.setSongLength(length);
    }

    public void setSongLength(String length) throws InvalidSongLengthException {
        String[] tokens = length.split(":");
        if (tokens.length != 2) {
            throw new InvalidSongLengthException();
        }
        int minutes;
        int seconds;
        try{
            minutes = Integer.parseInt(tokens[0]);
            seconds = Integer.parseInt(tokens[1]);
        }catch (NumberFormatException ex){
            throw new InvalidSongLengthException();
        }
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) throws InvalidArtistException {
        if (artist.length() < 3 || artist.length() > 20){
            throw new InvalidArtistException();
        }
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidSongNameException {
        if (name.length() < 3 || name.length() > 30){
            throw new InvalidSongNameException();
        }
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) throws InvalidSongMinutesException {
        if (minutes < 0 || minutes > 14){
            throw new InvalidSongMinutesException();
        }
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) throws InvalidSongSecondsException {
        if (seconds < 0 || seconds > 59){
            throw new InvalidSongSecondsException();
        }
        this.seconds = seconds;
    }
}
