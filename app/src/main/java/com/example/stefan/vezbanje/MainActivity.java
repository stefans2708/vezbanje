package com.example.stefan.vezbanje;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_READ_CONTACTS = 1;
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

        Button btnSendIntent = findViewById(R.id.btn_custom_intent);
        btnSendIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getPackageName()+".CUSTOM_INTENT_ASDF");
//                intent.setAction(getPackageName()+".CUSTOM_INTENT_ASDF");
                sendBroadcast(intent);
            }
        });
        Button btnRss = findViewById(R.id.btn_rss_activity);
        btnRss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RssfeedActivity.class);
                startActivity(intent);
            }
        });

        Button btnContacts = findViewById(R.id.btn_contacts);
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactButtonClick();
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

        IntentFilter intentFilter1 = new IntentFilter(getPackageName()+".CUSTOM_INTENT_ASDF");
        registerReceiver(customBroadcastReceiver,intentFilter1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiBroadcastReceiver);
        unregisterReceiver(customBroadcastReceiver);
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

    private BroadcastReceiver customBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            TextView textView = findViewById(R.id.txt_custom_intent);
            textView.setText(action);
        }
    };

    //Permissions

    private void readContacts() {
        Toast.makeText(this,"Contacts read",Toast.LENGTH_LONG).show();
    }


    String[] permissions = { Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION};
    final int ALL_PERMISSIONS = 1;


    private void contactButtonClick() {
        if(hasAllPermissions(MainActivity.this,permissions))
            readContacts();
        else{
            //VAZNOO!!! uvek se salju sve permisije u request, a on sam zna koje nedostaju i za njih ce da pita!!!!
            ActivityCompat.requestPermissions(this,permissions,ALL_PERMISSIONS);
        }
    }
    //ovde, ako nema permisiju odma mu se trazi...
    //bolji slucaj je da ako nema permisiju, prvo se vidi zasto je nema (da li je odbio npr.)
    //i ako je odbio da mu prikaze neku poruku zbog toga da ga ubedi..
    //znaci ovde u else grani se poziva f-ja koja ce da ima if i else granu. u if ce da stoji shouldShowRequest..,
    //i da mu prikaze alert dijalog sa objasnjenjem pa ako klikne na yes da se zove request, a u else ce da stoji request diretkno (prvi put ga pita)
    //https://codinginflow.com/tutorials/android/run-time-permission-request

    //samo je razlika sto ovde ima vise request-ova. Medjutim, to moze da se resi tako sto ce u tu f-ju koja se poziva
    //da se stavi for, a shouldShowRequest sama zna da li treba ili ne da se prikaze.....
    //MADA, MOZDA BI BILO VECE OPTERECENJE ZA KORISNIKA AKO BI SE RADILO NA TAJ NACIN, JER TREBA VISE ALERT DIJALOGA DA CITA
    //A U TOM SLUCAJU OVAJ NACIN IZ OVOG FAJLA JE BOLJI

    private boolean hasAllPermissions(Context context, String... permissions) {
        if(context != null && permissions != null){
            for (String permission : permissions) {
                if(ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED)
                    return false;
            }
        }
        return true;
    }

    //za sve tri permisije odjednom se poziva ova metoda...
    //KLJUCNO PITANJE: Kako znati da imas dont ask again selektovane, pa treba da prikazes dijalog??? Sledi odgovor u f-ji :D
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode != ALL_PERMISSIONS) return;

        if(permissions.length == 0) return;

        boolean allPermissionsGranted = true;
        for(int grantResult : grantResults){
            if(grantResult != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }
        //if(allPermissionGranted){ readContacts(); return;}

        boolean somePermissionsForeverDenied = false;
        if(!allPermissionsGranted){
            for(String permission: permissions){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                    Log.e("Senta - request denied",permission);
                    //Toast.makeText(this, "Prethodno si odbio. Dodatno ti objasnjavam da treba da prihvatis!", Toast.LENGTH_LONG).show();
                }else{
                    //vratila f-ja false, znaci ili je prethodno dozvoljen ili je izabrano don't ask again
                    //dakle prethodno, u prethodnom krugu!!! kliknuto na allow ili na deny sa dont ask again
                    if(ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED){
                        Log.e("Senta - request granted",permission);
                    }else{
                        Log.e("Senta - don't ask again",permission);
                        somePermissionsForeverDenied = true;        //zbog ovoga se ustvari poziva shouldShowPermissionRationale
                    }                                                   //da bi kasnije mogli da prikazemo dijalog....
                }
            }
        }

        //iskopirano
        if(somePermissionsForeverDenied){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Permissions Required")
                    .setMessage("You have forcefully denied some of the required permissions " +
                            "for this action. Please open settings, go to permissions and allow them.")
                    .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", getPackageName(), null));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        }

    }
}
