package yaran.com.lecturenoteskeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;


public class DatabaseWrapper {
    private CardsDbHelper dbHelper;
    private SQLiteDatabase db;

    private Context context;

    public DatabaseWrapper(Context context) {
        this.context = context;
        dbHelper = new CardsDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public Observable<Void> put(final Card card) {
        return Observable.fromCallable(() -> {
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
            return null;
        });
    }


    //the wrapper of db.query which returns values from db in more convenient form
    public Observable<List<Card>> get(final String[] columns, final String selection,
                                      final String[] selectionArgs, final String groupBy,
                                      final String having, final String orderBy) {
        return Observable.fromCallable(() -> {
            Cursor cursor = db.query(Card.Entry.TABLE_NAME, columns, selection,
                    selectionArgs, groupBy, having, orderBy);

            if (cursor == null) {
                Toast.makeText(context, "Cursor is null", Toast.LENGTH_SHORT).show();
                return null;
            }
            List<Card> cards = new ArrayList<>();
            while (cursor.moveToNext()) {
                Map<String, String> tmp_dict = new HashMap<>();
                for (String col : cursor.getColumnNames()) {
                    tmp_dict.put(col, cursor.getString(cursor.getColumnIndex(col)));
                }
                cards.add(convertMapToCard(tmp_dict));
            }
            cursor.close();
            return cards;
        });
    }

    public Observable<Void> delete(String selection, String[] selectionArgs) {
        return Observable.fromCallable(() -> {
            db.delete(Card.Entry.TABLE_NAME, selection, selectionArgs);
            return null;
        });
    }

    public Observable<Void> drop() {
        return Observable.fromCallable(() -> {
            db.execSQL(CardsDbHelper.SQL_DELETE_ENTRIES);
            return null;
        });
    }

    private Card convertMapToCard(Map<String, String> map) {
        Card card = new Card();
        for (Map.Entry<String, String> entry : map.entrySet()) {
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
