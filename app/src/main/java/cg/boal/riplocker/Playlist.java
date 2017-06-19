package cg.boal.riplocker;

/**
 * Created by Cgboal on 28/03/2017.
 */

public class Playlist {

    private int id;
    private String name;


    public Playlist() {

    }
    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getId() { return this.id;}
    public String getName() {return this.name;}

    public void setId(int id) { this.id = id;}
    public void setName(String n) {this.name = n;}

}
