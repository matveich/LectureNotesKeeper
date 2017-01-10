package yaran.com.lecturenoteskeeper.Database;


import android.graphics.Bitmap;

public class Card {
    Bitmap photo;
    String title;
    String description;

    public Card(Bitmap photo, String title, String description) {
        this.photo = photo;
        this.title = title;
        this.description = description;
    }
}
