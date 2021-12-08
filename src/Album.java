import java.util.ArrayList;

public class Album {
    private String name;
    private ArrayList<Song> albumSongs;

    public Album(String name, ArrayList<Song> albumSongs) {
        this.name = name;
        this.albumSongs = albumSongs;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getAlbumSongs() {
        return albumSongs;
    }

    public static Album createAlbum (String name, ArrayList<Song> albumSongs ) {
        Album album = new Album(name,albumSongs);
        return album;
    }

    public boolean findSong (Song song){
       return this.albumSongs.contains(song);
    }
}


