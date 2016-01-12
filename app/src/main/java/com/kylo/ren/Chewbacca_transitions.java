package com.kylo.ren;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class Chewbacca_transitions extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_chew);
        final MediaPlayer aarroah = MediaPlayer.create(this,R.raw.chew);
        final MediaPlayer oaaharha = MediaPlayer.create(this,R.raw.chew2);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        oaaharha.start();
                        wait(9000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                aarroah.start();
                                overridePendingTransition(R.anim.fade_in_transition, R.anim.fade_out);
                                finish();
                            }
                        });
                        aarroah.start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();
    }
}
