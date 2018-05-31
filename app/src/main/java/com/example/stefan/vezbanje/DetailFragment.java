package com.example.stefan.vezbanje;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class DetailFragment extends Fragment {

    public static final String EXTRA_TEXT = "text";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("FragmentSenta","onAtach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d("FragmentSenta","onCreate()");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            String newText = data.getStringExtra("newTime");
            TextView textView = getView().findViewById(R.id.detailfragment_text);
            textView.setText(newText);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("FragmentSenta","onCreateView()");

        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            TextView tvTime = getView().findViewById(R.id.detailfragment_text);
            tvTime.setText(savedInstanceState.getString("timeTextView"));
        }

        Bundle bundle = getArguments();
        if(bundle != null){
            String text = bundle.getString(EXTRA_TEXT);
            setText(text);
        }
        Log.d("FragmentSenta","onActivityCreated()");
    }

    //region Logovanje
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnOpenEdit = getView().findViewById(R.id.btn_fragment_detail);
        btnOpenEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditDetailActivity.class);

                TextView textView = getView().findViewById(R.id.detailfragment_text);
                String currentTime = textView.getText().toString();

                intent.putExtra("currentTime",currentTime);
                startActivityForResult(intent,1);           //getActivity().startAc... se koristi kad treba da se hvata
                                                                        //u activity rezultat
            }
        });

        Log.d("FragmentSenta","onViewCreated()");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("FragmentSenta","onStart()");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentSenta","onResume()");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("FragmentSenta","onPause()");

    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d("FragmentSenta","onStop()");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FragmentSenta","onDestroyView()");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FragmentSenta","onDestroy()");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("FragmentSenta","onDetach()");

    }
    //endregion

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView timeTv = getView().findViewById(R.id.detailfragment_text);
        String time = timeTv.getText().toString();
        outState.putString("timeTextView",time);

        Log.d("FragmentSenta","onSaveInstanceState()");

    }



    public void setText(String text){
        TextView textView = getView().findViewById(R.id.detailfragment_text);
        textView.setText(text);
    }
}
