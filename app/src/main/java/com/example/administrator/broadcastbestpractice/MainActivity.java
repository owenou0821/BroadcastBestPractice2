package com.example.administrator.broadcastbestpractice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BasicActivity {

    private LocalBroadcastManager localBroadcastManager;
    private ForceOfflineBroadcastReceiver offlineBroadcastReceiver;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        intentFilter = new IntentFilter("com.example.broadcast_MY_FORCE_OFFINE");
        offlineBroadcastReceiver = new ForceOfflineBroadcastReceiver();
        localBroadcastManager.registerReceiver(offlineBroadcastReceiver, intentFilter);

        Button force_offline_button = (Button) findViewById(R.id.force_offline_button);
        force_offline_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcast_MY_FORCE_OFFINE");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(offlineBroadcastReceiver);
    }

    public static void toMainActivity(Activity activity){
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
        activity.finish();


    }

    class ForceOfflineBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Force Offline");
            alertDialog.setMessage("Your account login in another place."+'\n'+"Please try to login against.");
            alertDialog.setCancelable(true);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.allFinish();
                    Toast.makeText(MainActivity.this, "Relogin", Toast.LENGTH_LONG).show();
                    LoginActivity.toLoginActivity(MainActivity.this);
//                    Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
//                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent1);


                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog.show();
        }
    }




}
