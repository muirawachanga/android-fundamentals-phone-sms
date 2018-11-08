package com.example.bituls.sms_trial;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * This app provides SMS features that enable the user to:
 * - Enter a phone number.
 * - Enter a message and send the message to the phone number.
 * - Receive SMS messages and display them in a toast.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;

    /**
     * Creates the activity, sets the view, and checks for SMS permission.
     *
     * @param savedInstanceState Instance state
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check to see if SMS is enabled.
        myLayout = findViewById(R.id.myLayout);
        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();
        checkForSmsPermission();
    }

    public static final int MULTIPLE_PERMISSIONS = 123; // code you want.

    String[] permissions = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET};

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private  void checkForSmsPermission() {
        if(!hasPermissions(this, permissions)){
            ActivityCompat.requestPermissions(this, permissions, MULTIPLE_PERMISSIONS);
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, getString(R.string.granted_permission),
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, getString(R.string.failure_permission));
                    Toast.makeText(this, getString(R.string.failure_permission),
                            Toast.LENGTH_LONG).show();

                }
            }
        }
    }
    public void NextActivity(View view) {
        Intent intent = new Intent(this, Next_Activity.class);
        startActivity(intent);
    }

}
