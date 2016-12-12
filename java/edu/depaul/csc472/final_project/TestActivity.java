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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    public static final String PREFS_NAME = "edu.depaul.csc472.final_project";
    private String newNum = "";

    HashMap<String, Integer> one = new HashMap<>();
    HashMap<String, Integer> two = new HashMap<>();
    HashMap<String, Integer> three = new HashMap<>();
    HashMap<String, Integer> four = new HashMap<>();
    HashMap<String, Integer> five = new HashMap<>();
    HashMap<String, Integer> six = new HashMap<>();
    HashMap<String, Integer> seven = new HashMap<>();
    HashMap<String, Integer> eight = new HashMap<>();
    HashMap<String, Integer> nine = new HashMap<>();
    HashMap<String, Integer> ten = new HashMap<>();
    HashMap<String, Integer> eleven = new HashMap<>();
    HashMap<String, Integer> twelve = new HashMap<>();
    HashMap<String, Integer> hm = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        populateHashMaps();
        final MediaPlayer mp = new MediaPlayer();
        Singleton s = Singleton.getInstance();
        String number = s.num;
        final String q;
        final TextView question = (TextView) findViewById(R.id.question);
        final TextView answer = (TextView) findViewById(R.id.answer);

        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);
        Button b9 = (Button) findViewById(R.id.button9);
        Button b0 = (Button) findViewById(R.id.button0);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        Button buttonAnswer = (Button) findViewById(R.id.buttonAnswer);
        Button buttonQuit = (Button) findViewById(R.id.quit);

        //Set Font
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/KOMIKAX_.ttf");
        TextView txt1 = (TextView) findViewById(R.id.main_title);
        TextView txt2 = (TextView) findViewById(R.id.question);
        TextView txt3 = (TextView) findViewById(R.id.answer);
        TextView txt4 = (TextView) findViewById(R.id.your_answer);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);
        txt3.setTypeface(tf);
        txt4.setTypeface(tf);

        //If ten questions have been asked...
        if (s.counter >= 10) {
            Intent intent = new Intent(TestActivity.this, FinishedActivity.class);
            startActivity(intent);
            return;
        }

        if (s.counter == 0) {
            if (number.equals("one")) {
                s.hm = one;
                List<String> keys = new ArrayList<>(one.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("two")) {
                s.hm = two;
                List<String> keys = new ArrayList<>(two.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("three")) {
                s.hm = three;
                List<String> keys = new ArrayList<>(three.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("four")) {
                s.hm = four;
                List<String> keys = new ArrayList<>(four.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("five")) {
                s.hm = five;
                List<String> keys = new ArrayList<>(five.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("six")) {
                s.hm = six;
                List<String> keys = new ArrayList<>(six.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("seven")) {
                s.hm = seven;
                List<String> keys = new ArrayList<>(seven.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("eight")) {
                s.hm = eight;
                List<String> keys = new ArrayList<>(eight.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("nine")) {
                s.hm = nine;
                List<String> keys = new ArrayList<>(nine.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("ten")) {
                s.hm = ten;
                List<String> keys = new ArrayList<>(ten.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
            if (number.equals("eleven")) {
                s.hm = eleven;
                List<String> keys = new ArrayList<>(eleven.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            } else if (number.equals("twelve")) {
                s.hm = twelve;
                List<String> keys = new ArrayList<>(twelve.keySet());
                Collections.shuffle(keys);
                s.questions = keys;
            }
        }

        //get the question and delete it from the singleton list...
        q = s.questions.remove(0);
        question.setText(q);

        //create answer string for next screen...
        final String a = (q + " is " + s.hm.get(q));

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                //String newNum = "";
                Singleton s = Singleton.getInstance();
                if (((Button) v).getText().toString().matches("1|2|3|4|5|6|7|8|9|0")) {

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

                    newNum = (newNum + ((Button) v).getText().toString());
                    //if (newNum.length() > 8) {
                    //    newNum = "";
                    //}
                    answer.setText(newNum);
                }
                if (((Button) v).getTag().toString().equals("clear")) {
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

                    newNum = "";
                    answer.setText(newNum);
                }
                if (((Button) v).getTag().toString().equals("quit")) {
                    Intent intent = new Intent(TestActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (((Button) v).getTag().toString().equals("answer")) {
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

                    if (newNum.equals("")) {
                        newNum = "0";
                    }


                    //If answer is correct...
                    if (Integer.parseInt(newNum) == s.hm.get(q)) {
                        //Toast.makeText(TestActivity.this, "CORRECT!", Toast.LENGTH_SHORT).show();

                        //update user Gold amount...
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        int gold = settings.getInt(s.name, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt(s.name, gold + 1);
                        editor.commit();

                        s.counter++;
                        s.correct++;
                        Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                        intent.putExtra("result", "correct");
                        intent.putExtra("a", a);
                        startActivity(intent);
                        //Answer is incorrect...
                    } else {
                        //Toast.makeText(TestActivity.this, "OOOPS!", Toast.LENGTH_SHORT).show();
                        s.counter++;
                        Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                        intent.putExtra("result", "incorrect");
                        intent.putExtra("a", a);
                        startActivity(intent);
                    }
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
        b0.setOnClickListener(listener);
        buttonAnswer.setOnClickListener(listener);
        buttonClear.setOnClickListener(listener);
        buttonQuit.setOnClickListener(listener);


    }


    private void populateHashMaps() {
        one.put("1 x 1", 1);
        one.put("1 x 2", 2);
        one.put("1 x 3", 3);
        one.put("1 x 4", 4);
        one.put("1 x 5", 5);
        one.put("1 x 6", 6);
        one.put("1 x 7", 7);
        one.put("1 x 8", 8);
        one.put("1 x 9", 9);
        one.put("1 x 10", 10);

        two.put("2 x 1", 2);
        two.put("2 x 2", 4);
        two.put("2 x 3", 6);
        two.put("2 x 4", 8);
        two.put("2 x 5", 10);
        two.put("2 x 6", 12);
        two.put("2 x 7", 14);
        two.put("2 x 8", 16);
        two.put("2 x 9", 18);
        two.put("2 x 10", 20);
        two.put("2 x 11", 22);
        two.put("2 x 12", 24);

        three.put("3 x 1", 3);
        three.put("3 x 2", 6);
        three.put("3 x 3", 9);
        three.put("3 x 4", 12);
        three.put("3 x 5", 15);
        three.put("3 x 6", 18);
        three.put("3 x 7", 21);
        three.put("3 x 8", 24);
        three.put("3 x 9", 27);
        three.put("3 x 10", 30);
        three.put("3 x 11", 33);
        three.put("3 x 12", 36);

        four.put("4 x 1", 4);
        four.put("4 x 2", 8);
        four.put("4 x 3", 12);
        four.put("4 x 4", 16);
        four.put("4 x 5", 20);
        four.put("4 x 6", 24);
        four.put("4 x 7", 28);
        four.put("4 x 8", 32);
        four.put("4 x 9", 36);
        four.put("4 x 10", 40);
        four.put("4 x 11", 44);
        four.put("4 x 12", 48);

        five.put("5 x 1", 5);
        five.put("5 x 2", 10);
        five.put("5 x 3", 15);
        five.put("5 x 4", 20);
        five.put("5 x 5", 25);
        five.put("5 x 6", 30);
        five.put("5 x 7", 35);
        five.put("5 x 8", 40);
        five.put("5 x 9", 45);
        five.put("5 x 10", 50);
        five.put("5 x 11", 55);
        five.put("5 x 12", 60);

        six.put("6 x 1", 6);
        six.put("6 x 2", 12);
        six.put("6 x 3", 18);
        six.put("6 x 4", 24);
        six.put("6 x 5", 30);
        six.put("6 x 6", 36);
        six.put("6 x 7", 42);
        six.put("6 x 8", 48);
        six.put("6 x 9", 54);
        six.put("6 x 10", 60);
        six.put("6 x 11", 66);
        six.put("6 x 12", 72);

        seven.put("7 x 1", 7);
        seven.put("7 x 2", 14);
        seven.put("7 x 3", 21);
        seven.put("7 x 4", 28);
        seven.put("7 x 5", 35);
        seven.put("7 x 6", 42);
        seven.put("7 x 7", 49);
        seven.put("7 x 8", 56);
        seven.put("7 x 9", 63);
        seven.put("7 x 10", 70);
        seven.put("7 x 11", 77);
        seven.put("7 x 12", 84);

        eight.put("8 x 1", 8);
        eight.put("8 x 2", 16);
        eight.put("8 x 3", 24);
        eight.put("8 x 4", 32);
        eight.put("8 x 5", 40);
        eight.put("8 x 6", 48);
        eight.put("8 x 7", 56);
        eight.put("8 x 8", 64);
        eight.put("8 x 9", 72);
        eight.put("8 x 10", 80);
        eight.put("8 x 11", 88);
        eight.put("8 x 12", 96);

        nine.put("9 x 1", 9);
        nine.put("9 x 2", 18);
        nine.put("9 x 3", 27);
        nine.put("9 x 4", 36);
        nine.put("9 x 5", 45);
        nine.put("9 x 6", 54);
        nine.put("9 x 7", 63);
        nine.put("9 x 8", 72);
        nine.put("9 x 9", 81);
        nine.put("9 x 10", 90);
        nine.put("9 x 11", 99);
        nine.put("9 x 12", 108);

        ten.put("10 x 1", 10);
        ten.put("10 x 2", 20);
        ten.put("10 x 3", 30);
        ten.put("10 x 4", 40);
        ten.put("10 x 5", 50);
        ten.put("10 x 6", 60);
        ten.put("10 x 7", 70);
        ten.put("10 x 8", 80);
        ten.put("10 x 9", 90);
        ten.put("10 x 10", 100);
        ten.put("10 x 11", 110);
        ten.put("10 x 12", 120);

        eleven.put("11 x 1", 11);
        eleven.put("11 x 2", 22);
        eleven.put("11 x 3", 33);
        eleven.put("11 x 4", 44);
        eleven.put("11 x 5", 55);
        eleven.put("11 x 6", 66);
        eleven.put("11 x 7", 77);
        eleven.put("11 x 8", 88);
        eleven.put("11 x 9", 99);
        eleven.put("11 x 10", 110);
        eleven.put("11 x 11", 121);
        eleven.put("11 x 12", 132);

        twelve.put("12 x 1", 12);
        twelve.put("12 x 2", 24);
        twelve.put("12 x 3", 36);
        twelve.put("12 x 4", 48);
        twelve.put("12 x 5", 60);
        twelve.put("12 x 6", 72);
        twelve.put("12 x 7", 84);
        twelve.put("12 x 8", 96);
        twelve.put("12 x 9", 108);
        twelve.put("12 x 10", 120);
        twelve.put("12 x 11", 132);
        twelve.put("12 x 12", 144);
    }


}
