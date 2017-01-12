package yaran.com.lecturenoteskeeper.RecyclerViewClasses;

public class RVCard {
    String title;
    String subject;
    String imagePath;
    String comment;
    int type;
    boolean needNotification;

    public RVCard(String title, String subject, String imagePath, int type, String comment, Boolean needNotification) {
        this.title = title;
        this.subject = subject;
        this.imagePath = imagePath;
        this.type = type;
        this.comment = comment;
        this.needNotification = needNotification;
    }
}
