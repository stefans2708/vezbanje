package com.example.stefan.vezbanje;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DialogFragmentExample extends android.support.v4.app.DialogFragment{

    public DialogFragmentExample(){

    }

    //  @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Pick a color");
//        builder.setItems(R.array.colors, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(),"You've selected "+which+" item.",Toast.LENGTH_SHORT).show();
//            }
//        });
//        return builder.create();
//    }
//
//    ======================================================================================
//
//    ArrayList<Integer> selectedItems = new ArrayList<Integer>();
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
//        builder.setTitle("Chose multiple colors:");
//        builder.setMultiChoiceItems(R.array.colors, null, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                     if(isChecked){
//                         selectedItems.add(which);
//                     }else if(selectedItems.contains(which)){
//                         selectedItems.remove(Integer.valueOf(which));
//                     }
//            }
//        });
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String selected = "";
//                for(int i = 0; i < selectedItems.size(); i++)
//                    selected += (selectedItems.get(i)).toString()+" ";
//                Toast.makeText(getActivity(), "You selected "+selected, Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setNegativeButton("Cancel",null);
//
//        return builder.create();
//    }
//    =====================================================================

    String username;
    String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public interface DialogListener{
        public void onPositiveButtonClick(DialogFragment dialog);
        public void onNegativeButtonClick(DialogFragment dialog);
    }

    DialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mListener = (DialogListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
        Log.v("DialogFragmentSenta","onAttach()");
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_signin,null);
// setStyle(); moze i ovako da se postavi stil
        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText etUsername = view.findViewById(R.id.username);
                String username = etUsername.getText().toString();
                EditText etPassword = view.findViewById(R.id.password);
                String password = etPassword.getText().toString();
                DialogFragmentExample df = new DialogFragmentExample();
                df.setUsername(username);
                df.setPassword(password);
                mListener.onPositiveButtonClick(df);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onNegativeButtonClick(null);
            }
        });

        Log.v("DialogFragmentSenta","onCreateDialog()");

        return builder.create();
    }




    //region Logovanje
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("DialogFragmentSenta","onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v("DialogFragmentSenta","onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("DialogFragmentSenta","onViewCreated()");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("DialogFragmentSenta","onStart()");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("DialogFragmentSenta","onResume()");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("DialogFragmentSenta","onPause()");

    }


    @Override
    public void onStop() {
        super.onStop();
        Log.v("DialogFragmentSenta","onStop()");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v("DialogFragmentSenta","onDestroyView()");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("DialogFragmentSenta","onDestroy()");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("DialogFragmentSenta","onDetach()");
    }
    //endregion
}
