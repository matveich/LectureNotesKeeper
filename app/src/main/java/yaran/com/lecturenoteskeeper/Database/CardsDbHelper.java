package yaran.com.lecturenoteskeeper.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class CardsDbHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "NotesKeeper.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            Card.Entry.TABLE_NAME + " (" + Card.Entry._ID + INT_TYPE + " PRIMARY KEY," +
            Card.Entry.COLUMN_NAME_FILEPATH + TEXT_TYPE + "," +
            Card.Entry.COLUMN_NAME_TITLE + TEXT_TYPE + "," +
            Card.Entry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + "," +
            Card.Entry.COLUMN_NAME_TYPE + TEXT_TYPE + "," +
            Card.Entry.COLUMN_NAME_SUBJECT + TEXT_TYPE + "," +
            Card.Entry.COLUMN_NAME_DATETIME + TEXT_TYPE + "," +
            Card.Entry.COLUMN_NAME_DEADLINE_DATETIME + TEXT_TYPE + "," +
            Card.Entry.COLUMN_NAME_NOTIFICATION_FLAG + TEXT_TYPE + " )";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Card.Entry.TABLE_NAME;

    CardsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
