package com.aeiton.adventro.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.aeiton.adventro.Fragments.NewsFeedFragment;
import com.aeiton.adventro.Fragments.UserHomeFragment;
import com.aeiton.adventro.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    static Toolbar toolbar;
    boolean doublepressonce = false;
    int fragmentStatus = 0;
    boolean fabOpen = false;


    FragmentManager fragmentManager;
    Fragment fragment;
    Class fragmentClass;
    static Context context;

    Animation rotate_forward, rotate_backward;

    FloatingActionButton fab, fab_photo, fab_journal, fab_timeline, fab_invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Adventro");

        context = HomeActivity.this;

        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

                if (fragmentStatus == 1) {
                    fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
                    fragmentStatus = 0;
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setting the default fragment to view in user home
        fragment = new Fragment();
        fragmentClass = UserHomeFragment.class;

        FragmentManager fragmentManager = getSupportFragmentManager();
        try {
            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();



        fab = (FloatingActionButton) findViewById(R.id.FAButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HomeActivity.this,"on it",Toast.LENGTH_SHORT).show();

                if (fabOpen){
                    hideFabs();
                    fab.startAnimation(rotate_forward);
                    fabOpen = false;
                }else{

                    openFabs();
                    fab.startAnimation(rotate_backward);
                    fabOpen = true;

                }
            }
        });

        fab_invite = (FloatingActionButton) findViewById(R.id.FABinvite);
        fab_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        fab_photo = (FloatingActionButton) findViewById(R.id.FABjournal);
        fab_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fab_journal = (FloatingActionButton) findViewById(R.id.FABtimeline);
        fab_journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(HomeActivity.this,CreateTimeLine.class));
            }
        });
        fab_timeline = (FloatingActionButton) findViewById(R.id.FABphoto);
        fab_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        hideFabs();
        showFab();


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create_spot) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hideFab(){

        if (fab.isShown())
            fab.hide();
    }

    public void showFab(){
        if (!fab.isShown())
            fab.show();
    }
    public static void changeTitle(String title) {
        toolbar.setTitle(title);
    }

    public void openFabs(){

        fab_invite.show();
        fab_journal.show();
        fab_timeline.show();
        fab_photo.show();
    }

    public void hideFabs(){
        fab_invite.hide();
        fab_timeline.hide();
        fab_journal.hide();
        fab_photo.hide();
    }

}
