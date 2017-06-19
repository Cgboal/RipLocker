package cg.boal.riplocker;

/**
 * Created by Cgboal on 28/03/2017.
 */

public class Song {

    private int id;
    private String title, artist;

    public Song() {

    }

    public Song(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }



    @Override
    public String toString(){
        return this.title;
    }

    public String getTitle() {return title;}
    public String getArtist() {return artist;}

    public void setTitle(String title) {this.title = title;}
    public void setArtist(String artist) {this.artist = artist;}
}
