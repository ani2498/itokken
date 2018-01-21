package com.itokken.itokken;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rishi on 1/12/2018.
 */

public class start extends Activity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_layout);

            final ImageView splash_image = (ImageView) findViewById(R.id.ivlogo);
            final Animation animation_1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            final Animation animation_2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

            animation_1.reset();
            animation_2.reset();

            splash_image.clearAnimation();
            splash_image.startAnimation(animation_2);




            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        int waited = 0;
                        while (waited < 5000) {
                            sleep(100);
                            waited += 100;
                        }
                        Intent intent = new Intent(start.this, WelcomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        start.this.finish();
                    } catch (InterruptedException e) {

                    } finally {
                        start.this.finish();
                    }

                }
            };
            thread.start();
        }


}
