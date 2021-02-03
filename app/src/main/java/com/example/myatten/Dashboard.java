package com.example.myatten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    public void closeDrawer()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout=findViewById(R.id.drawer_layout);

        actionBarDrawerToggle=new ActionBarDrawerToggle(Dashboard.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView=findViewById(R.id.navigation_view);
        View navView=navigationView.inflateHeaderView(R.layout.navigation_header);
        navigationView.getMenu().findItem(R.id.logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                logout();
                return true;
            }
        });

        navigationView.bringToFront();
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UsersMenuSelected(menuItem);

                return false;
            }



        });
        //actionBarDrawerToggle.getDrawerArrowDrawable().setColor();
        dashboard_fragment dashboard_fragment= new dashboard_fragment();
        setMyFragment(dashboard_fragment);
    }
    private void logout() {
        Intent intent=new Intent(Dashboard.this,LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void UsersMenuSelected(MenuItem menuItem)
    {

        Fragment newFragment=null;
        switch(menuItem.getItemId())
        {
            case R.id.navHome:
                newFragment= new dashboard_fragment();
                closeDrawer();
                break;
            case R.id.attendance:
                newFragment=new attendance_fragment();
                closeDrawer();
                break;
            case R.id.holidays:
                newFragment=new holidays_fragment();
                closeDrawer();
                break;
            case R.id.profile:
                newFragment=new profile_fragment();
                closeDrawer();
                break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_controller,newFragment).commit();

    }
    private void setMyFragment(Fragment fragment)
    {
        //get current fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //get fragment transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //set new fragment in fragment_container (FrameLayout)
        fragmentTransaction.replace(R.id.fragment_controller, fragment);
        fragmentTransaction.commit();
    }
}
