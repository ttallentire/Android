package Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteHelper
        extends SQLiteOpenHelper
{
    public static final String TABLE_COMMENTS;
    public static final String COLUMN_ID;
    public static final String COLUMN_LATITUDE;
    public static final String COLUMN_LONGITUDE;
    private static final String DATABASE_NAME;
    private static final int DATABASE_VERSION;
    private static final String DATABASE_CREATE;

    static
    {
        TABLE_COMMENTS   = "comments";
        COLUMN_ID        = "_id";
        COLUMN_LATITUDE   = "latitude";
        COLUMN_LONGITUDE = "longitude";
        DATABASE_NAME    = "commments.db";
        DATABASE_VERSION = 1;
        DATABASE_CREATE  = "create table " +
                TABLE_COMMENTS + "(" + COLUMN_ID +
                " integer primary key autoincrement, " + COLUMN_LATITUDE +
                " integer not null, " + COLUMN_LONGITUDE + " integer not null);";

    }

    public SQLiteHelper(final Context context)
    {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database)
    {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db,
                          final int            oldVersion,
                          final int            newVersion)
    {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}
