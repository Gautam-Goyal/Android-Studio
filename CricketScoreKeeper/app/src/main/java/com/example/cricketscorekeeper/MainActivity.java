package com.example.cricketscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int RunTeamA=0;
    int RunTeamB=0;
    int WicketTeamA=0;
    int WicketTeamB=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final LinearLayout linearTeamA=(LinearLayout) findViewById(R.id.linearTeamA);
        Button WicketTeamAButton=(Button) findViewById(R.id.buttonWicketTeamA);

        WicketTeamAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startColorAnimation(linearTeamA); addWicketForTeamA(v);
            }
        });

        final LinearLayout linearTeamB=(LinearLayout) findViewById(R.id.linearTeamB);
        Button WicketTeamBButton=(Button) findViewById(R.id.buttonWicketTeamB);

        WicketTeamBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startColorAnimation(linearTeamB); addWicketForTeamB(v);
            }
        });
    }

    private void startColorAnimation(View v)
    {
        int colorStart=v.getSolidColor();
        int colorEnd=0xFFFF0000;

        ValueAnimator ColorAnim= ObjectAnimator.ofInt(v,"backgroundColor",colorStart,colorEnd);

        ColorAnim.setDuration(1000);
        ColorAnim.setEvaluator(new ArgbEvaluator());
        ColorAnim.setRepeatCount(1);
        ColorAnim.setRepeatMode(ValueAnimator.REVERSE);
        ColorAnim.start();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*It will add one run to Team A board*/
    public void addOneForTeamA(View v) {
        RunTeamA+=1;
        displayRunTeamA(RunTeamA);
    }

    /*It will add two run to Team A board*/
    public void addTwoForTeamA(View v)
    {
        RunTeamA+=2;
        displayRunTeamA(RunTeamA);
    }

    /*It will add three run to Team A board*/
    public void addThreeForTeamA(View v) {
        RunTeamA+=3;
        displayRunTeamA(RunTeamA);
    }

    /*It will add four run to Team A board*/
    public void addFourForTeamA(View v) {
        RunTeamA+=4;
        displayRunTeamA(RunTeamA);
    }

    /*It will add six run to Team A board*/
    public void addSixForTeamA(View v)
    {
        RunTeamA+=6;
        MediaPlayer sixCreate=MediaPlayer.create(this,R.raw.cricket);
        sixCreate.start();
        displayRunTeamA(RunTeamA);
    }

    //it will add a wicket to Team A board
    public void addWicketForTeamA(View v) {

        WicketTeamA+=1;
        MediaPlayer wicketCreate=MediaPlayer.create(this,R.raw.wicketcricket);
        wicketCreate.start();

        if(WicketTeamA==10)
        {
            Toast.makeText(this,"TEAM A has got ALL OUT",Toast.LENGTH_SHORT).show();
            TextView scoreView = (TextView) findViewById(R.id.team_a_out);
            scoreView.setText("ALL OUT");
            WicketTeamA=0;
        }
        else
        displayWicketTeamA(WicketTeamA);
    }

    /*It will add one run to Team B board*/
    public void addOneForTeamB(View v) {
        RunTeamB+=1;
        displayRunTeamB(RunTeamB);
    }

    /*It will add two run to Team B board*/
    public void addTwoForTeamB(View v)
    {
        RunTeamB+=2;
        displayRunTeamB(RunTeamB);
    }

    /*It will add three run to Team B board*/
    public void addThreeForTeamB(View v) {
        RunTeamB+=3;
        displayRunTeamB(RunTeamB);
    }

    /*It will add four run to Team B board*/
    public void addFourForTeamB(View v) {
        RunTeamB+=4;
        displayRunTeamB(RunTeamB);
    }

    /*It will add six run to Team B board*/
    public void addSixForTeamB(View v)
    {
        RunTeamB+=6;
        MediaPlayer sixCreate=MediaPlayer.create(this,R.raw.cricket);
        sixCreate.start();
        displayRunTeamB(RunTeamB);
    }

    /*It will add wicket to Team B board*/
    public void addWicketForTeamB(View v) {

       WicketTeamB+=1;

       MediaPlayer wicketCreate=MediaPlayer.create(this,R.raw.wicketcricket);
       wicketCreate.start();
        if(WicketTeamB==10)
        {
            Toast.makeText(this,"TEAM B has got ALL OUT",Toast.LENGTH_SHORT).show();
            TextView scoreView = (TextView) findViewById(R.id.team_b_out);
            scoreView.setText("ALL OUT");
            WicketTeamB=0;
        }
        else
        displayWicketTeamB(WicketTeamB);
    }

    //It activate the reset button
    public void reset(View v)
    {
       RunTeamA=0;
        RunTeamB=0;
        WicketTeamA=0;
        WicketTeamB=0;

        displayRunTeamA(RunTeamA);
        displayRunTeamB(RunTeamB);
        displayWicketTeamA(WicketTeamA);
        displayWicketTeamB(WicketTeamB);
    }

    //It displays runs on score board of team A
    public void displayRunTeamA(int run) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_run);
        scoreView.setText(String.valueOf(run));
    }

    //It displays runs on score board of team B
    public void displayRunTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_run);
        scoreView.setText(String.valueOf(score));
    }

    //It display no. of wickets on score board of Team A
    public void displayWicketTeamA(int Wicket) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_out);
        scoreView.setText(String.valueOf(Wicket));
    }

    //It display no. of wickets on score board of Team B
    public void displayWicketTeamB(int Wicket) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_out);
        scoreView.setText(String.valueOf(Wicket));
    }
}
