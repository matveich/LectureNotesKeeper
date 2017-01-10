package yaran.com.lecturenoteskeeper;


import java.util.Random;

//the class with static fields which can be used from other classes in the project
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
}
