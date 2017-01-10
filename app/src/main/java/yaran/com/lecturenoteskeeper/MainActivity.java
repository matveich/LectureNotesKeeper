package yaran.com.lecturenoteskeeper;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int width, height;
    public static Context context;
    ImageView imageField;
    MaterialEditText titleField, dateField, timeField;

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.requestLayout();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.calendar);
        Calendar cal = Calendar.getInstance();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle);
                RelativeLayout addNoteLayout = (RelativeLayout) getLayoutInflater()
                        .inflate(R.layout.add_note, null);
                builder.setView(addNoteLayout);
                builder.setCancelable(false);
                builder.setPositiveButton("Выбрать", null);
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.show();

                imageField = (ImageView) addNoteLayout.findViewById(R.id.image_field);
                imageField.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        intent.putExtra("return-data", true);
                        startActivityForResult(intent, 1);
                    }
                });

                titleField = (MaterialEditText) addNoteLayout.findViewById(R.id.title_field);
                dateField = (MaterialEditText) addNoteLayout.findViewById(R.id.date_field);
                timeField = (MaterialEditText) addNoteLayout.findViewById(R.id.time_field);
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        try {
            drawer.closeDrawer(GravityCompat.START);
        } catch (java.lang.NullPointerException gf) {
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.d("picturePath = ", picturePath);
            imageField.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            File file = new File(picturePath);
            if (file.exists()) {
                Date lastModDate = new Date(file.lastModified());
                Calendar cal = Calendar.getInstance();
                cal.setTime(lastModDate);
                Log.i("pictureDate = ", lastModDate.toString());
                dateField.setText(cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "." + cal.get(Calendar.YEAR));
                timeField.setText(cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));
                titleField.setText(file.getName());
                Log.d("pictureTitle = ", picturePath.substring(picturePath.lastIndexOf("/") + 1));
            }
        }
    }
}
