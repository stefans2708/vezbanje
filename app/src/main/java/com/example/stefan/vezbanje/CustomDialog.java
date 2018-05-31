package com.example.stefan.vezbanje;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CustomDialog extends Dialog implements View.OnClickListener {

    public Context context;
    public Button btnYes, btnNo;

    public CustomDialog(@NonNull Context context) {
        super(context,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_singin_buttons);
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                dismiss();
                Toast.makeText(context, "YES", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_no:
                dismiss();
                Toast.makeText(context, "NO", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
