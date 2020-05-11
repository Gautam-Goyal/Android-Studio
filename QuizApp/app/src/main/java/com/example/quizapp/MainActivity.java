package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int Q1_ANSWER = R.id.question_1_first_logo_radio;
    final int Q2_ANSWER = R.id.question_2_second_logo_radio;
    final String Q4_ANSWER = "norway";
    final int Q5_ANSWER = R.id.question_5_third_radio;
    final int Q6_ANSWER = R.id.question_6_second_radio;
    final String Q7_ANSWER = "new jersey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //It activates on clicking submit button and cross check answers by calling respective functions.
    //The answers which came out to be wrong are stored in ArrayList and then displayed in toast as a result.
    public void checkQuiz(View v) {
        ArrayList<String> incorrectAnswersList = new ArrayList<String>();

        int numberOfQuestionsCorrect = 0;

        if( checkQuestion1() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 1");
        }

        if( checkQuestion2() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 2");
        }

        if( checkQuestion3() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 3");
        }

        if( checkQuestion4() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 4");
        }

        if( checkQuestion5() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 5");
        }

        if( checkQuestion6() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 6");
        }

        if( checkQuestion7() ){
            numberOfQuestionsCorrect++;
        } else {
            incorrectAnswersList.add("Question 7");
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<incorrectAnswersList.size();i++)
        {
            sb.append(incorrectAnswersList.get(i));
            sb.append("\n");
        }

        Context context = getApplicationContext();
        CharSequence text = "You got " + numberOfQuestionsCorrect + "/7 answers right.\n\nRecheck the following:\n" + sb.toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //check answer for question 1.
    private boolean checkQuestion1() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.question_1_radio_group);

        if( rg.getCheckedRadioButtonId() == Q1_ANSWER ) { //here we need to check it with integer value therefore either store id in integer or use id directly.
            return true;
        }

        return false;
    }

    //check answer for Q2.
    private boolean checkQuestion2() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.question_2_radio_group);

        if( rg.getCheckedRadioButtonId() == Q2_ANSWER ) {
            return true;
        }

        return false;
    }

    //check answer for Q3.
    private boolean checkQuestion3() {
        CheckBox c1 = (CheckBox) findViewById(R.id.question_3_first_checkbox);
        CheckBox c2 = (CheckBox) findViewById(R.id.question_3_second_checkbox);
        CheckBox c3 = (CheckBox) findViewById(R.id.question_3_third_checkbox);
        CheckBox c4 = (CheckBox) findViewById(R.id.question_3_fourth_checkbox);
        CheckBox c5 = (CheckBox) findViewById(R.id.question_3_fifth_checkbox);
        CheckBox c6 =(CheckBox) findViewById(R.id.question_3_sixth_checkbox);

        if (c1.isChecked() && c2.isChecked() && !c3.isChecked() && c4.isChecked() && c5.isChecked() && !c6.isChecked()) {
            return true;
        }

        return false;
    }

    //check answer for Q4.
    private boolean checkQuestion4() {
        EditText et = (EditText)findViewById(R.id.question_4_edit_text);

        return et.getText().toString().equalsIgnoreCase(Q4_ANSWER);
    }

    //check answer for Q5.
    private boolean checkQuestion5() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.question_5_radio_group);

        if( rg.getCheckedRadioButtonId() == Q5_ANSWER ) {
            return true;
        }

        return false;
    }

    //check answer for Q6.
    private boolean checkQuestion6() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.question_6_radio_group);

        if( rg.getCheckedRadioButtonId() == Q6_ANSWER ) {
            return true;
        }

        return false;
    }

    //check answer for Q7.
    private boolean checkQuestion7() {
        EditText et = (EditText)findViewById(R.id.question_7_edit_text);

        return et.getText().toString().equalsIgnoreCase(Q7_ANSWER);
    }
}
