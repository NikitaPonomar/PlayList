public class Song {
    private String name;
    private int duration;

    public Song(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public static Song createSong (String name, int duration){
        Song song = new Song(name,duration);
        return song;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
