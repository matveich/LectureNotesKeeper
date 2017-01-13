package yaran.com.lecturenoteskeeper;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static yaran.com.lecturenoteskeeper.MainActivity.context;

//the class with static fields which can be used from other classes in the project

public class StaticInfo {
    public static final String DEBUG_TAG = "123";

    public static final String picDir = getAppFolder();

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

    public static void moveFileToAppFolder(String filepath) {
        //Toast.makeText(context, picDir, Toast.LENGTH_SHORT).show();
        File from = new File(filepath);
        String filename = from.getName();
        String path = picDir + "/" + filename;
        File to = new File(path);
        //Toast.makeText(context, path, Toast.LENGTH_SHORT).show();
        from.renameTo(to);
    }

    private static String getAppFolder() {
        File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                + "Pictures/LectureNotesKeeper");
        path.mkdirs();
        return path.getAbsolutePath();
    }
}
