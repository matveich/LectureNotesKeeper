package yaran.com.lecturenoteskeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static yaran.com.lecturenoteskeeper.StaticInfo.stringToDate;

public class DatabaseWrapper {
    private CardsDbHelper dbHelper;
    private SQLiteDatabase db;

    private Context context;

    public DatabaseWrapper(Context context) {
        this.context = context;
        dbHelper = new CardsDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void put(Card card) {
        ContentValues values = new ContentValues();
        values.put(Card.Entry.COLUMN_NAME_FILEPATH, card.getFilepath());
        values.put(Card.Entry.COLUMN_NAME_TITLE, card.getTitle());
        values.put(Card.Entry.COLUMN_NAME_DESCRIPTION, card.getDescription());
        values.put(Card.Entry.COLUMN_NAME_TYPE, card.getType());
        values.put(Card.Entry.COLUMN_NAME_SUBJECT, card.getSubject());
        if (card.getDatetime() != null)
            values.put(Card.Entry.COLUMN_NAME_DATETIME, card.getDatetime());
        if (card.getDeadlineDatetime() != null)
            values.put(Card.Entry.COLUMN_NAME_DEADLINE_DATETIME, card.getDeadlineDatetime());
        values.put(Card.Entry.COLUMN_NAME_NOTIFICATION_FLAG, card.getNotificationFlag());
        db.insert(Card.Entry.TABLE_NAME, null, values);
    }

    //the wrapper of db.query which returns values from db in more convenient form
    public List<Card> get(String[] columns, String selection, String[] selectionArgs,
                   String groupBy, String having, String orderBy) {
        Cursor cursor = db.query(Card.Entry.TABLE_NAME, columns, selection,
                selectionArgs, groupBy, having, orderBy);

        if (cursor == null) {
            Toast.makeText(context, "Cursor is null", Toast.LENGTH_SHORT).show();
            return null;
        }
        ArrayList<Card> cards = new ArrayList<>();
        while (cursor.moveToNext()) {
            HashMap<String, String> tmp_dict = new HashMap<>();
            for (String col : cursor.getColumnNames()) {
                tmp_dict.put(col, cursor.getString(cursor.getColumnIndex(col)));
            }
            cards.add(convertMapToCard(tmp_dict));
        }
        cursor.close();
        return cards;
    }

    public void delete(String selection, String[] selectionArgs) {
        db.delete(Card.Entry.TABLE_NAME, selection, selectionArgs);
    }

    private Card convertMapToCard(HashMap<String, String> map) {
        Card card = new Card();
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case Card.Entry.COLUMN_NAME_FILEPATH:
                    card.setFilepath(entry.getValue());
                    break;
                case Card.Entry.COLUMN_NAME_TITLE:
                    card.setTitle(entry.getValue());
                    break;
                case Card.Entry.COLUMN_NAME_DESCRIPTION:
                    card.setDescription(entry.getValue());
                    break;
                case Card.Entry.COLUMN_NAME_TYPE:
                    card.setType(entry.getValue());
                    break;
                case Card.Entry.COLUMN_NAME_SUBJECT:
                    card.setSubject(entry.getValue());
                    break;
                case Card.Entry.COLUMN_NAME_DATETIME:
                    card.setDatetime(entry.getValue());
                    break;
                case Card.Entry.COLUMN_NAME_DEADLINE_DATETIME:
                    card.setDeadlineDatetime(entry.getValue());
                    break;
                case Card.Entry.COLUMN_NAME_NOTIFICATION_FLAG:
                    card.setNotificationFlag(Integer.parseInt(entry.getValue()));
                    break;
            }
        }
        return card;
    }
}
