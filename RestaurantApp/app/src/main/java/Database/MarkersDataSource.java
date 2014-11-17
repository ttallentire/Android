package Database;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class MarkersDataSource
{
    // Database fields
    private final SQLiteHelper dbHelper;
    private final String[] allColumns;
    private SQLiteDatabase database;

    {
        allColumns = new String[]
                {
                        SQLiteHelper.COLUMN_ID,
                        SQLiteHelper.COLUMN_LONGITUDE,
                };
    }

    public MarkersDataSource(Context context)
    {
        dbHelper = new SQLiteHelper(context);
    }

    public void open()
            throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}