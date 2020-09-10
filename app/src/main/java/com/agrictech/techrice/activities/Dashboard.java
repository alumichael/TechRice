package com.agrictech.techrice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.agrictech.techrice.R;
import com.agrictech.techrice.UserPreferences;
import com.agrictech.techrice.frgament.DashBoardFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private ActionBarDrawerToggle actionBarDrawerToggle;


    /** ButterKnife Code **/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView mTitleTxt;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    /** ButterKnife Code **/




    Fragment fragment;
    CircleImageView mProfileIcon;
    TextView nav_lastname;
    UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled( false );
        getSupportActionBar().setTitle(null);

        fragment = new DashBoardFragment();
        showFragment(fragment);

        userPreferences = new UserPreferences(this);

        //Set up Navigation Drawer
        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setItemIconTintList( null );
        navigationView.setNavigationItemSelectedListener( this );


        actionBarDrawerToggle = new ActionBarDrawerToggle( this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer( GravityCompat.START);
            }
        });
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_nav_icon_24);
        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.syncState ();


        mProfileIcon=navigationView.getHeaderView(0).findViewById(R.id.profile_photo);
        nav_lastname=navigationView.getHeaderView(0).findViewById(R.id.username);
        nav_lastname.setText(userPreferences.getUserLastname());
    }


    //Method to set fragment immediately Onclick
    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    //Overriding Back Press button to close an already open Navigation
    //View
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment = new DashBoardFragment();
                showFragment(fragment);
                break;

            case R.id.nav_cropping:

                startActivity(new Intent(Dashboard.this,CropCalendarActivity.class));
                break;

            case R.id.nav_buy_input:
                startActivity(new Intent(Dashboard.this,BuyInputActivity.class));
                break;

            case R.id.nav_transact:
                startActivity(new Intent(Dashboard.this,TransactHistoryActivity.class));
                break;

            case R.id.nav_consult:
                startActivity(new Intent(Dashboard.this,ConsultantActivity.class));
                break;

            case R.id.nav_setting:
                startActivity(new Intent(Dashboard.this,SettingsActivity.class));
                break;


        }
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }
}