package com.example.stefan.vezbanje;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);


        final EditText editText = findViewById(R.id.editdetail_txt);

        Intent intent = getIntent();
        if(intent != null){
            String text = intent.getStringExtra("currentTime");
            editText.setText(text);
        }

        Button btnUpdate = findViewById(R.id.btn_editdetail);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTime = editText.getText().toString();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("newTime",newTime);

                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        Log.e("EditDetailActivitySenta","onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("EditDetailActivitySenta","onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("EditDetailActivitySenta","onResume()");

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("EditDetailActivitySenta","onPause()");

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e("EditDetailActivitySenta","onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("EditDetailActivitySenta","onDestroy()");

    }
}
