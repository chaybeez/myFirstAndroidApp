package edu.depaul.csc472.final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class StartSessionActivity extends AppCompatActivity {

    private static final String TAG = "StartSessionActivity";
    public static final String PREFS_NAME = "edu.depaul.csc472.final_project";
    String un = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_session);
        final MediaPlayer mp = new MediaPlayer();

        Button br = (Button) findViewById(R.id.change_user);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);
        Button b9 = (Button) findViewById(R.id.button9);
        Button b10 = (Button) findViewById(R.id.button10);
        Button b11 = (Button) findViewById(R.id.button11);
        Button b12 = (Button) findViewById(R.id.button12);
        Button b13 = (Button) findViewById(R.id.change_user);

        //Set gold image
        ImageView icon = (ImageView) findViewById(R.id.image);
        icon.setImageResource(R.drawable.gold);

        //get username from intent
        Intent intent = getIntent();
        un = intent.getStringExtra("username");

        //Set Font
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/KOMIKAX_.ttf");
        TextView txt1 = (TextView) findViewById(R.id.ass_title);
        TextView txt2 = (TextView) findViewById(R.id.ass_gold);
        TextView txt3 = (TextView) findViewById(R.id.message);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);
        txt3.setTypeface(tf);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int gold = settings.getInt(un, 0);

        final TextView title = (TextView) findViewById(R.id.ass_title);
        final TextView gld = (TextView) findViewById(R.id.ass_gold);
        title.setText("Welcome " + un + "!");
        gld.setText("You Have " + gold + " Gold!");


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                if (((Button) v).getText().toString().matches("1|2|3|4|5|6|7|8|9|10|11|12")) {
                    if (mp.isPlaying()) {
                        mp.stop();
                    }
                    try {
                        mp.reset();
                        AssetFileDescriptor afd;
                        afd = getAssets().openFd("blip.mp3");
                        mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        mp.prepare();
                        mp.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Singleton s = Singleton.getInstance();
                    s.counter = 0;
                    s.correct = 0;
                    s.name = un;
                    s.num = ((Button) v).getTag().toString();
                    Intent intent = new Intent(StartSessionActivity.this, TestActivity.class);
                    startActivity(intent);
                }
                if (((Button) v).getTag().toString().matches("change_user")) {
                    Intent intent = new Intent(StartSessionActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
        b3.setOnClickListener(listener);
        b4.setOnClickListener(listener);
        b5.setOnClickListener(listener);
        b6.setOnClickListener(listener);
        b7.setOnClickListener(listener);
        b8.setOnClickListener(listener);
        b9.setOnClickListener(listener);
        b10.setOnClickListener(listener);
        b11.setOnClickListener(listener);
        b12.setOnClickListener(listener);
        b13.setOnClickListener(listener);
    }

}

















