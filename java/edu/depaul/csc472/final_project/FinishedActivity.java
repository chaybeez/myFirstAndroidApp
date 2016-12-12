package edu.depaul.csc472.final_project;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class FinishedActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    public static final String PREFS_NAME = "edu.depaul.csc472.final_project";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        final MediaPlayer mp = new MediaPlayer();
        Singleton s = Singleton.getInstance();
        String username = s.name;
        int correct = s.correct;
        String message = ("Congratulations " + username + " you got " + String.valueOf(correct) + " out of 10 questions correct! Good Work!");
        final TextView one = (TextView) findViewById(R.id.one);
        Button b1 = (Button) findViewById(R.id.back);

        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/KOMIKAX_.ttf");
        TextView txt1 = (TextView) findViewById(R.id.one);
        txt1.setTypeface(tf);

        txt1.setText(message);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                Singleton s = Singleton.getInstance();
                if (((Button) v).getTag().toString().matches("back")) {

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

                    Intent intent = new Intent(FinishedActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        b1.setOnClickListener(listener);


    }
}
