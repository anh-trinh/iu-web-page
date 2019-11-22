package com.tnta.iuwebpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Preferences preference;

    public static boolean netCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        netCheck = isNetworkAvaliable(getApplicationContext());
        if (netCheck==false) {
            Toast.makeText(getApplicationContext(), "You should turn on the Internet", Toast.LENGTH_LONG).show();
        }

        preference = new Preferences(getApplication());

        if (preference.getID()!=null){
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, TabChange.class);
                    startActivity(i);
                }

            }, 1350L);
        }
        else {
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, EnterID.class);
                    startActivity(i);
                }

            }, 1350L);
        }
    }

    public static boolean isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }
}
