import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;

import javax.sound.midi.Soundbank;
import java.util.*;

public class PlayList {
    private String name;
    private LinkedList<Song> playListSongs;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Album> albumArrayList;

    public PlayList(String name, LinkedList<Song> playListSongs, ArrayList<Album> albumArrayList) {
        this.name = name;
        this.playListSongs = playListSongs;
        this.albumArrayList = albumArrayList;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Song> getPlayListSongs() {
        return playListSongs;
    }

    public static PlayList createPlayList(String name, LinkedList<Song> playListSongs, ArrayList<Album> albumArrayList) {
        PlayList playList = new PlayList(name, playListSongs, albumArrayList);
        return playList;
    }


    public boolean addSong() {
        System.out.println("Adding new song");
        System.out.println("Please input song name");
        String name = scanner.nextLine();
        System.out.println("Please input song duration");
        int duration;
        //Checking that input is integer number
        while (!scanner.hasNextInt()) {
            System.out.println("Wrong value, Please input integer number");
            scanner.nextLine(); //to avoid endless loop
        }
        duration = scanner.nextInt();
        scanner.nextLine();
        Song song = Song.createSong(name, duration);
        for (Song checkedSong: this.playListSongs){
           if(checkedSong.getName().equals(name)) {
                System.out.println("Could no add song, cause it already in your play list");
                return false;
            }
        }
        if (!findSongAlbum(song)) {
            System.out.println("could not add song, cause you do not have it in your albums");
            return false;
        }
    //   this.playListSongs.addLast(song);
        this.playListSongs.add(song);
        System.out.println("Song was added");
        return true;
    }

    public boolean removeSong(Song song) {
        if (!this.playListSongs.contains(song)) return false;
        this.playListSongs.remove(song);
        System.out.println("Song was removed from play list");
        return true;
    }

    public void printPlayList() {
        Iterator<Song> iterator = this.playListSongs.iterator();
        System.out.println("Playlist " + this.name + " includes songs:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getName());
        }

    }

//    public int playSong(Song song) {
//        if (!this.playListSongs.contains(song)) {
//            System.out.println("You do not have this song in your playlist");
//            return -1;
//        }
//        System.out.println("Now playing + " + song.getName());
//        return this.playListSongs.indexOf(song);
//    }


    public void playing() {

        ListIterator<Song> lisIterator = this.playListSongs.listIterator();
        Song currentSong;
        long songStarts;
        long songEnds;
        int choice;

        while (lisIterator.hasNext()) {
            currentSong = lisIterator.next();
            System.out.println("Now playing " + currentSong.getName());
            System.out.println("Choose the options");
            System.out.println("0 - to quite");
            System.out.println("1 - next song");
            System.out.println("2 - previous song");
            System.out.println("3 - replay current song");
            System.out.println("4 - show songs in playlist");
            System.out.println("5 - add song to playlist");
            System.out.println("6 - remove song from playlist");

            // waiting for user decision for song.duration time
            songStarts = System.currentTimeMillis();
            songEnds = songStarts + (long) (currentSong.getDuration() * 1000);
            while (System.currentTimeMillis() < songEnds) {
                //Checking that input is integer number
                while (!scanner.hasNextInt() && System.currentTimeMillis() < songEnds) {
                    System.out.println("Wrong value, Please input integer number");
                    scanner.nextLine(); //to avoid endless loop
                }
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        System.out.println("Exit");
                        scanner.close();
                        System.exit(0);
                    case 1:
                        songEnds=songStarts;
                        break;
                    case 2:
                        if (lisIterator.hasPrevious()) lisIterator.previous();
                        if (lisIterator.hasPrevious()) lisIterator.previous();
                        songEnds=songStarts;
                        break;
                    case 3:
                        lisIterator.previous();
                        songEnds=songStarts;
                        break;
                    case 4:
                        printPlayList();
                        break;
                    case 5:
                        addSong();
//                        if (lisIterator.hasPrevious()) {
//                            currentSong=lisIterator.previous();
//                        }
                     //   songEnds=songStarts;
                        break;
                    case 6:
                  //      removeSong(lisIterator.previous());
                        lisIterator.remove();
                        System.out.println("Song was removed from playlist");
                        if (lisIterator.hasPrevious()) lisIterator.previous();
                        else if (lisIterator.hasNext()) lisIterator.next();
                        else System.exit(0);
                        songEnds=songStarts;
                        break;
                    default:
                        break;
                }
            }

        }

        scanner.close();
        System.out.println("All songs in playlist were played");
    }

    public boolean findSongAlbum(Song currentSong) {
        Iterator<Album> iterator = this.albumArrayList.iterator();
        ArrayList<Song> songList;
        while (iterator.hasNext()){
            songList = iterator.next().getAlbumSongs();
            for (int i=0; i< songList.size();i++){
                System.out.println(songList.get(i).getName());
                if (songList.get(i).getName().equals(currentSong.getName()) &&
                        songList.get(i).getDuration()== currentSong.getDuration()) return true;
            }
        }
        return false;
    }
}
