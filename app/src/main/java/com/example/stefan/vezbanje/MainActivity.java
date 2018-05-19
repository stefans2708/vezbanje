package com.example.stefan.vezbanje;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch wifiSwitch;
    WifiManager wifiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiSwitch = findViewById(R.id.wifi_switch);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    wifiManager.setWifiEnabled(true);
                    wifiSwitch.setText("WiFi is ON");
                }else{
                    wifiManager.setWifiEnabled(false);
                    wifiSwitch.setText("WiFi is OFF");
                }
            }
        });

//        if(wifiManager.isWifiEnabled()){
//            wifiSwitch.setEnabled(true);
//            wifiSwitch.setText("WiFI is ON");
//        }else{
//            wifiSwitch.setEnabled(false);
//            wifiSwitch.setText("WiFI is OFF");
//        }
//        ovaj deo moze da se obrise zato sto kad god se startuje activity i registruje se receiver
//        automatski se poziva onReceive jer je Wifi tzv. STICKY BROADCAST sto znaci da kad se postavi event
//        OSTAJE(lepi se) u sistemu, tako da odma moze da se pozove onReceive cim se registruje...
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private BroadcastReceiver wifiBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                                                    WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("WiFI is ON");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("WiFI is OFF");
                    break;
            }
        }
    };

}
