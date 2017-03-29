package cg.boal.riplocker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cgboal on 17/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "songs.db";
    private static final String TABLE_NAME = "songs";
    private static final String COLUMN_ID = "id";
    private static final String COULUMN_TITLE = "title";
    private static final String COLUMN_ARTIST = "artist";

    private static final String TABLE_CREATE_SONGS = "CREATE TABLE songs (id integer primary key not null, " +
            "title text not null, artist text not null, playlist integer not null);";

    private static final String TABLE_CREATE_PLAYLISTS = "CREATE TABLE playlists (id integer primary key not null, " +
            "title text not null)";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_SONGS);
        db.execSQL(TABLE_CREATE_PLAYLISTS);
        this.db = db;
    }

    public void InsertSong(String title, String artist) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL = "INSERT INTO songs (title, artist) VALUES ('" + title + "', '" + artist + "');";
        db.execSQL(insertSQL);
    }

    public void InsertPlaylist(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        String insertSQL = "INSERT INTO playlists (title) VALUES('" + title + "');";
        db.execSQL(insertSQL);
    }

    public List<Playlist> GetPlaylists(){
        List<Playlist> pList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String SQL = "SELECT * FROM playlists";
        Cursor c = db.rawQuery(SQL, null);

        c.moveToFirst();
        while(c.moveToNext()) {
            int id = c.getInt(0);
            String title = c.getString(1);
            pList.add(new Playlist(id, title));
        }

        return pList;

    }

    public List<Song> GetSongs(int pId) {
        List<Song> songs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String SQL = "SELECT * FROM songs WHERE playlist = " + pId + ";";
        Cursor c = db.rawQuery(SQL, null);

        c.moveToFirst();
        while(c.moveToNext()) {
            int id = c.getInt(0);
            String title = c.getString(1);
            String artist = c.getString(2);
            songs.add(new Song(id, title, artist));
        }

        return songs;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

