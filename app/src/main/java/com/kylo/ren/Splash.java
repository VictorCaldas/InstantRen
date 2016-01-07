package com.kylo.ren;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Splash extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Button will = (Button) findViewById(R.id.will);
        final Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final ImageView img = (ImageView) findViewById(R.id.imageView1);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);

        linearLayout.setVisibility(View.INVISIBLE);
        final MediaPlayer mPlayer = MediaPlayer.create(this,R.raw.will);



        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                img.animate().y(390).setDuration(550);
                                linearLayout.startAnimation(fadeInAnimation);
                                linearLayout.setVisibility(View.VISIBLE);

                                will.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mPlayer.start();
                                    }
                                });


                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();
    }

}