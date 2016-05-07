package com.example.martinsaad.hackidc;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCommunicator {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    LoginFragment loginFragment;
    SettingsFragment settingsFragment;
    ExerciseDetailsFragment exerciseDetailsFragment;
    ExerciseListFragment exerciseListFragment;
    PerformanceFragment performanceFragment;
    RegisterFragment registerFragment;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        performanceFragment = new PerformanceFragment();
        fragmentTransaction.add(R.id.main_frag_container, performanceFragment, "performanceFragment");
        fragmentTransaction.show(performanceFragment).addToBackStack("performanceFragment").commit();
/*        loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.main_frag_container, loginFragment, "loginFragment");
        fragmentTransaction.show(loginFragment).addToBackStack("loginFragment").commit();*/
//        loginFragment = new LoginFragment();
//        fragmentTransaction.add(R.id.main_frag_container, loginFragment, "loginFragment");
//        fragmentTransaction.show(loginFragment).addToBackStack("loginFragment").commit();

        //TODO erase stack

//        if (!isUserLoggedIn()){
//            Intent LoginActivity = new Intent(this, LoginActivity.class);
//            startActivity(LoginActivity);
//        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_manage_exercise:
                break;

            case R.id.nav_settings:
                settingsFragment = new SettingsFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frag_container, settingsFragment, "settingsFragment");
                fragmentTransaction.addToBackStack(null).commit();
                break;

            case R.id.nav_logout:
                deleteFile("user_id.txt");
                loginFragment = new LoginFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_frag_container, loginFragment, "loginFragment");
                fragmentTransaction.addToBackStack(null).commit();
                break;

            case R.id.nav_performance:
                performanceFragment = new PerformanceFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frag_container, performanceFragment, "performanceFragment");
                fragmentTransaction.addToBackStack(null).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        switch (text){
            case "loginFragment":
                loginFragment = new LoginFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frag_container,loginFragment, "loginFragment");
                fragmentTransaction.addToBackStack(null).commit();
                break;

            case "cancelDrawer":
                setDrawerState(false);
                break;

            case "enableDrawer":
                setDrawerState(true);
                break;

            case "exerciseDetailsFragment":
                exerciseDetailsFragment = new ExerciseDetailsFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frag_container, exerciseDetailsFragment, "exerciseDetailsFragment");
                fragmentTransaction.addToBackStack(null).commit();
                break;

            case "registerFragment":
                registerFragment = new RegisterFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frag_container, registerFragment, "registerFragment");
                fragmentTransaction.addToBackStack(null).commit();
                break;

            case "exerciseListFragment":
                exerciseListFragment = new ExerciseListFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frag_container, exerciseListFragment, "exerciseListFragment");
                fragmentTransaction.addToBackStack(null).commit();
                break;
        }
        return null;
    }

    public void setDrawerState(boolean isEnabled) {
        if ( isEnabled ) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();

        }
        else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSupportNavigateUp();
                }
            });
            toggle.syncState();
        }
    }
}

