package com.example.stefan.vezbanje;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class RssfeedActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener,
        DialogFragmentExample.DialogListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("RssActivitySenta","onCreate()");

        if(savedInstanceState != null){
            getSupportFragmentManager().executePendingTransactions();       //TODO: ZASTO???
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if(fragment != null){
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }
        ListFragment listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,listFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_detail_fragment:
                DetailFragment detailFragment = new DetailFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,detailFragment).addToBackStack("detail").commit();
                break;
            case R.id.menu_item_button_fragment:
                ListFragment listFragment = new ListFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,listFragment).addToBackStack("button").commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("RssActivitySenta","onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("RssActivitySenta","onResume()");

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("RssActivitySenta","onPause()");

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("RssActivitySenta","onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("RssActivitySenta","onDestroy()");

    }


//    @Override
//    public void onBackPressed() {
//        FragmentManager fm = getSupportFragmentManager();
//        if(fm.getBackStackEntryCount() > 0){
//            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1);
//            if(entry.getName() == "detail")
//                super.onBackPressed();
//            else {
//                fm.popBackStack("detail", 0);
//            }
//        }else{
//            super.onBackPressed();
//        }
//
//    }

    @Override
    public void onRssItemSelected(String text) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle paramsBundle = new Bundle();
        paramsBundle.putString(DetailFragment.EXTRA_TEXT,text);
        detailFragment.setArguments(paramsBundle);
        //DetailFragment detailFragment = DetailFragment.newInstance(text);	ali mora da se implementira odg metoda tamo
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container,detailFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }



    @Override
    public void onPositiveButtonClick(DialogFragment dialog) {
        DialogFragmentExample df = (DialogFragmentExample)dialog;
        Toast.makeText(this,"Username: "+df.getUsername()+ "\nPassword: "+df.getPassword(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNegativeButtonClick(DialogFragment dialog) {
        Toast.makeText(this,"Cancel clicked",Toast.LENGTH_SHORT).show();
    }
}
