package yaran.com.lecturenoteskeeper.Database;


import android.provider.BaseColumns;

public class CardsContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty private constructor.
    private CardsContract() {}

    public static abstract class CardsEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
    }
}
