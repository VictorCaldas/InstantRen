package com.kylo.ren;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Splash extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{


    //creates a Gesture Detector

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    int egg=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        final Button will = (Button) findViewById(R.id.will);
        final Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final ImageView img = (ImageView) findViewById(R.id.imageView1);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        final MediaPlayer mPlayer = MediaPlayer.create(this,R.raw.will);
        final MediaPlayer mPlayer2 = MediaPlayer.create(this,R.raw.will2);
        linearLayout.setVisibility(View.INVISIBLE);

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

                                img.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        Runnable runnable = new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(3000);
                                                    mPlayer2.start();

                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                //Your code after the long click goes here
                                            }
                                        };
                                        runnable.run();

                                        return true;
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

    public void eaterEggKylo(){

    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        egg = egg + 1;

        if (egg == 5){
            Intent intent = new Intent(getApplicationContext(), Chewbacca_transitions.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in_transition, R.anim.fade_out);
            finish();

        }

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {

        return true;
    }
}