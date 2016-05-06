package com.example.martinsaad.hackidc;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.main_frag_container, loginFragment, "loginFragment");
        fragmentTransaction.show(loginFragment).addToBackStack("loginFragment").commit();
    }

    public String getActiveFragmentTag() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        return tag;
    }

    @Override
    public String passString(String text) {
        switch (getActiveFragmentTag()){
            case "loginFragment":
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frag_container,studentEditDetailsFragment, "studentEditDetailsFragment");
                fragmentTransaction.addToBackStack("studentEditDetailsFragment").commit();
                break;
        }
        return null;
    }
}
