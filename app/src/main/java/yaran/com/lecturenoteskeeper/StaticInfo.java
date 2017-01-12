package yaran.com.lecturenoteskeeper;


import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static yaran.com.lecturenoteskeeper.MainActivity.context;

//a class with static fields which can be used from other classes in the project
public class StaticInfo {
    public static final String DEBUG_TAG = "123";

    public static String genStr(int len) {
        String str = "";
        String symb = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        for (int i = 0; i < len; i++) {
            str += symb.charAt(new Random().nextInt(symb.length()));
        }
        return str;
    }

    public static Date stringToDate(String str) {
        Date date = null;
        try {
            DateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.US);
            date = format.parse(str);
        }
        catch (ParseException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return date;
    }
}
