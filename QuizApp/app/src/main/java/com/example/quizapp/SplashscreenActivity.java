package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

                setContentView(R.layout.activity_splashscreen2);
                getSupportActionBar().hide();

                logoLauncher logoLauncher=new logoLauncher();
                logoLauncher.start();
            }

            private class logoLauncher extends Thread{
                public void run(){
                    try {
                        sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Intent intent=new Intent(com.example.quizapp.SplashscreenActivity.this,MainActivity.class);
                    startActivity(intent);
                    com.example.quizapp.SplashscreenActivity.this.finish();
                }
            }

        }