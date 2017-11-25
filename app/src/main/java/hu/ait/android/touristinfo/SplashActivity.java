package hu.ait.android.touristinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/**
 * Created by oliviakim on 11/25/17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        final RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.splashLayout);
        final Animation splashAnim = AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.splash_anim);

        splashLayout.startAnimation(splashAnim);


        new Handler().postDelayed (new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish ();
            }
        }, 3000);


        splashAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }


            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
