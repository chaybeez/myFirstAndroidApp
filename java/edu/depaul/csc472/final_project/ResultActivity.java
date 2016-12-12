package edu.depaul.csc472.final_project;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    public static final String PREFS_NAME = "edu.depaul.csc472.final_project";
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView one = (TextView) findViewById(R.id.one);
        Button b1 = (Button) findViewById(R.id.next);
        final MediaPlayer mp = new MediaPlayer();

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
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
                Intent intent = new Intent(ResultActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });


        //Set Font
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/KOMIKAX_.ttf");
        TextView txt1 = (TextView) findViewById(R.id.one);
        TextView txt2 = (TextView) findViewById(R.id.two);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);

        Intent intent = getIntent();
        String res = intent.getStringExtra("result");
        String ans = intent.getStringExtra("a");
        String message;

        if (res.equals("correct")) {
            message = "Correct! " + ans + ". You Earned One Gold! Keep Going... You're Doing Great!";
            if (mp.isPlaying()) {
                mp.stop();
            }
            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("tada.mp3");
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            message = "Ooops! " + ans + ". Better Luck Next Time! You Can Do It!";
            if (mp.isPlaying()) {
                mp.stop();
            }
            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("poke.mp3");
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        txt1.setText(message);
        txt2.setText("Shake me or hit the button to try again!!!");


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                Singleton s = Singleton.getInstance();
                if (((Button) v).getTag().toString().matches("next")) {

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

                    Intent intent = new Intent(ResultActivity.this, TestActivity.class);
                    startActivity(intent);
                }
            }
        };

        b1.setOnClickListener(listener);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
