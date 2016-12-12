package edu.depaul.csc472.final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String PREFS_NAME = "edu.depaul.csc472.final_project";
    final ArrayList<String> uNames = new ArrayList<String>();
    String un = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer mp = new MediaPlayer();
        Button b1 = (Button) findViewById(R.id.start_sesh_button);
        Button b2 = (Button) findViewById(R.id.create_user_button);

        //for debugging: delete all shared preferences:
        //SharedPreferences tings = getSharedPreferences(PREFS_NAME, 0);
        //SharedPreferences.Editor editor = tings.edit();
        //editor.clear();
        //editor.commit();

        //Get usernames...
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Map<String, ?> users = settings.getAll();
        //add user names to uNames
        if (users.isEmpty()) {
            uNames.add("Add A New User!");
            b1.setEnabled(false);
        } else {
            for (Map.Entry<String, ?> entry : users.entrySet()) {
                Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
                uNames.add(entry.getKey());
            }
        }
        //initialize spinner
        final Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, uNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, "Spinner1: " + uNames.get(position) + " position=" + position + " id=" + id);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Log.d(TAG, "Spinner1: unselected");
                    }
                });


        //Set Font
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/KOMIKAX_.ttf");
        TextView txt1 = (TextView) findViewById(R.id.main_title);
        TextView txt2 = (TextView) findViewById(R.id.create_u);
        TextView txt3 = (TextView) findViewById(R.id.select_u);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);
        txt3.setTypeface(tf);

        if (mp.isPlaying()) {
            mp.stop();
        }
        try {
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd("gong.mp3");
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                if (((Button) v).getTag().toString().equals("create_user_button")) {
                    //Play mp3 when button clicked...
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

                    Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                    startActivity(intent);
                }
                if (((Button) v).getTag().toString().equals("start_sesh_button")) {
                    //Play mp3 when button clicked...
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

                    un = s1.getSelectedItem().toString();
                    Intent intent = new Intent(MainActivity.this, StartSessionActivity.class);
                    intent.putExtra("username", un);
                    startActivity(intent);
                }

            }
        };


        b1.setOnClickListener(listener);
        b2.setOnClickListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        uNames.clear();
        //Get usernames...
        Button b1 = (Button) findViewById(R.id.start_sesh_button);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Map<String, ?> users = settings.getAll();
        //add user names to uNames
        if (users.isEmpty()) {
            uNames.add("Add A New User!");
            b1.setEnabled(false);
        } else {
            for (Map.Entry<String, ?> entry : users.entrySet()) {
                Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
                uNames.add(entry.getKey());
            }
        }
    }
}
