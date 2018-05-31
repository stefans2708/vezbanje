package com.example.stefan.vezbanje;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class ListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnItemSelectedListener mListener;

    public ListFragment() {
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.v("FragmentSenta","onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        Button btn = view.findViewById(R.id.listfragmet_btn_update);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PRVI
//                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updateDetail();
//                    }
//                };
//                openAlertDialog(listener);

                //DRUGI
//                DialogFragmentExample dialogFragmentExample = new DialogFragmentExample();
//                dialogFragmentExample.show(getFragmentManager(),"dialog");

                //TRECI
                CustomDialog customDialog = new CustomDialog(getActivity());
                customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //customDialog.getWindow().setLayout(WindowManager.);
                customDialog.show();
            }
        });

        Log.v("FragmentSenta","onCreateView()");
        return view;
    }

    private void updateDetail() {
        String text = String.valueOf(System.currentTimeMillis());
        mListener.onRssItemSelected(text);
    }

    private void openAlertDialog(DialogInterface.OnClickListener yesButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setTitle("Chose your answer:");
        builder.setMessage("Are you sure you want to send time data?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(),"You clicked yes!",Toast.LENGTH_SHORT).show();
//            }
//        });
        builder.setPositiveButton("Yes",yesButton);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "You clicked no!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }






    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            mListener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        Log.v("FragmentSenta","onAttach()");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.v("FragmentSenta","onDetach()");
    }

    public interface OnItemSelectedListener {
        void onRssItemSelected(String text);
    }


    //region Logovanje
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("FragmentSenta","onViewCreated()");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("FragmentSenta","onStart()");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("FragmentSenta","onResume()");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("FragmentSenta","onPause()");

    }


    @Override
    public void onStop() {
        super.onStop();
        Log.v("FragmentSenta","onStop()");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("FragmentSenta","onDestroyView()");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("FragmentSenta","onDestroy()");

    }

    //endregion
}
