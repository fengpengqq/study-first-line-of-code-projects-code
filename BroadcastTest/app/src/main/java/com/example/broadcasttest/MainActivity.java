package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
//发送两次广播比较麻烦
//                Intent intent2 = new Intent("com.example.broadcasttest.MY_BROADCAST");
//                intent.setComponent(new ComponentName("com.example.broadcasttest",
//                        "com.example.broadcasttest.MyBroadcastReceiver"));
//                intent2.setComponent(new ComponentName("com.example.broadcasttest2",
//                        "com.example.broadcasttest2.AnotherBroadcastReceiver"));
//                sendBroadcast(intent);
//                sendBroadcast(intent2);
//突破android8.0对隐式广播的限制
//                if(Build.VERSION.SDK_INT >= 26){
//                    Log.d("fengpeng", "onClick: " + Build.VERSION.SDK_INT);
//                    intent.addFlags(intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
//                }
//                //sendBroadcast(intent);
//                sendOrderedBroadcast(intent,null);

                Intent intent1 = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
//                if(Build.VERSION.SDK_INT >= 26){
//                    Log.d("fengpeng", "onClick: " + Build.VERSION.SDK_INT);
//                    intent1.addFlags(intent1.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
//                }
                localBroadcastManager.sendBroadcast(intent1);
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,"network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"received local broadcast",Toast.LENGTH_SHORT)
                    .show();
        }
    }

}