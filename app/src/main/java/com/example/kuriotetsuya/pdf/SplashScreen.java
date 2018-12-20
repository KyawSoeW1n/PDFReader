package com.example.kuriotetsuya.pdf;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

public class SplashScreen extends Activity {
    String[] perms = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    int permsRequestCode = 200;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("CODE",""+requestCode);
        if (requestCode == 200) {
            Intent i = new Intent(SplashScreen.this, Main.class);
            startActivity(i);

            // close this activity
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    requestPermissions(perms, permsRequestCode);
            }
        }, SPLASH_TIME_OUT);
    }

}