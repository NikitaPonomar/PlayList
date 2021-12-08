import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //creating my test Album list

        ArrayList<Album> testAlbumList = new ArrayList<Album>();
        Album testAlbum;
        Song song;
        ArrayList<Song> testAlbumSongs;
//Preparing test inout for AlbumList
        for (int i=0; i<2;i++){
            testAlbumSongs = new ArrayList<Song>();
            for (int j=0;j<3;j++){
                song=Song.createSong("Song"+i+j,((i+j)*10) );
                testAlbumSongs.add(song);

            }
            testAlbum=Album.createAlbum("Album name"+i, testAlbumSongs);
            testAlbumList.add(testAlbum);
        }
//Print test example of AlbumList
        for (Album currentAlbum: testAlbumList){
            System.out.println(currentAlbum.getName());
            for (Song currentSong: currentAlbum.getAlbumSongs()){
                System.out.println(currentSong.getName()+"duration"+currentSong.getDuration());
            }

        }

        // create test PlayListSongs
        LinkedList<Song> playListSongs = new LinkedList<Song>();
        playListSongs.add(Song.createSong("Song30", 30));
        playListSongs.add(Song.createSong("Song90", 90));
        playListSongs.add(Song.createSong("Song50", 50));
        PlayList playList=PlayList.createPlayList("myPlayList", playListSongs, testAlbumList);
        playList.playing();
    }


}
