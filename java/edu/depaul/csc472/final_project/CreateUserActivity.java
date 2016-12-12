package edu.depaul.csc472.final_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CreateUserActivity extends AppCompatActivity {

    private static final String TAG = "CreateUserActivity";
    public static final String PREFS_NAME = "edu.depaul.csc472.final_project";
    String eS = "";
    String newName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        final MediaPlayer mp = new MediaPlayer();

        //Set Font
        Typeface tf = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/KOMIKAX_.ttf");
        TextView txt1 = (TextView) findViewById(R.id.main_title);
        TextView txt2 = (TextView) findViewById(R.id.u_name);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);

        Button b1 = (Button) findViewById(R.id.submit_user_button);

        final EditText message = (EditText) findViewById(R.id.label);

        message.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    EditText cl = (EditText) findViewById(R.id.label);
                    String res = cl.getText().toString();
                    newName = res;
                    cl.setText(newName);
                    handled = true;

                }
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return handled;
            }
        });


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {

                if (((Button) v).getText().toString().equals("Create New User:")) {
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

                    if (newName.length() > 10 || newName.length() < 3) {
                        Toast.makeText(CreateUserActivity.this, "OnClickListener - Uername must be between 3-10 letters in length.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (settings.contains(newName)) {
                        Toast.makeText(CreateUserActivity.this, "Username already taken!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt(newName, 0);
                        editor.commit();
                        Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

            }
        };
        b1.setOnClickListener(listener);
    }


}
