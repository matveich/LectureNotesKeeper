package yaran.com.lecturenoteskeeper.Database;


import android.graphics.Bitmap;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Date;

public class Card {
    private String filepath = "";
    private String filename = "";
    private String title = "";
    private String description = "";
    private String type = "";
    private String subject = "";
    private Date datetime = null;
    private Date deadline_datetime = null;
    private int notification_flag = 0;

    public ArrayList<Object> asArray() {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(filepath);
        obj.add(filename);
        obj.add(title);
        obj.add(description);
        obj.add(type);
        obj.add(subject);
        obj.add(datetime);
        obj.add(deadline_datetime);
        obj.add(notification_flag);
        return obj;
    }

    public Card setFilepath(String filepath) {
        this.filepath = filepath;
        return this;
    }

    public String getFilepath() {
        return filepath;
    }

    public Card setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public Card setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Card setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Card setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public Card setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Card setDatetime(Date datetime) {
        this.datetime = datetime;
        return this;
    }

    public Date getDatetime() {
        return datetime;
    }

    public Card setDeadlineDatetime(Date deadlineDatetime) {
        this.deadline_datetime = deadlineDatetime;
        return this;
    }

    public Date getDeadlineDatetime() {
        return deadline_datetime;
    }

    public Card setNotificationFlag(int notificationFlag) {
        this.notification_flag = notificationFlag;
        return this;
    }

    public int getNotificationFlag() {
        return notification_flag;
    }

    public abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "cards";
        public static final String COLUMN_NAME_FILEPATH = "filepath";
        public static final String COLUMN_NAME_FILENAME = "filename";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_SUBJECT = "subject";
        public static final String COLUMN_NAME_DATETIME = "datetime";
        public static final String COLUMN_NAME_DEADLINE_DATETIME = "deadline_datetime";
        public static final String COLUMN_NAME_NOTIFICATION_FLAG = "notification_flag";
    }
}
