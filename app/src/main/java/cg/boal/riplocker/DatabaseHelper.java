package cg.boal.riplocker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cgboal on 17/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "songs.db"
    private static final String TABLE_NAME = "songs";
    private static final String COLUMN_ID = "id";
    private static final String COULUMN_TITLE = "title";
    private static final String COLUMN_ARTIST = "artist";

    private static final String TABLE_CREATE = "CREATE TABLE songs (id integer primary key not null " +
            "auto_increment, title text not null, artist text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }


}