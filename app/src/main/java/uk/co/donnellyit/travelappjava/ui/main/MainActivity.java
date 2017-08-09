package uk.co.donnellyit.travelappjava.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import uk.co.donnellyit.travelappjava.R;
import uk.co.donnellyit.travelappjava.ui.departures.DeparturesFragment;
import uk.co.donnellyit.travelappjava.ui.neartrain.NearStationFragment;
import uk.co.donnellyit.travelappjava.ws.StationJson;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
            NearStationFragment.OnListFragmentInteractionListener {

    private final static String DEPARTURE_TAG = "departures";
    private final static String NEAR_STATION_TAG = "nearStation";

    private String mCurrentFragment = DEPARTURE_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayFragments();
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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
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
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        if (id == R.id.nav_depart_live) {
            if(!mCurrentFragment.equals(DEPARTURE_TAG)) {
                fragment = getSupportFragmentManager().findFragmentByTag(DEPARTURE_TAG);
                if(fragment == null) {
                    fragment = DeparturesFragment.newInstance();
                    replaceFragment(fragment, DEPARTURE_TAG);
                }
            }
        } else if (id == R.id.nav_nearest_stations) {
            if(!mCurrentFragment.equals(NEAR_STATION_TAG)) {
                fragment = getSupportFragmentManager().findFragmentByTag(NEAR_STATION_TAG);
                if(fragment == null) {
                    fragment = NearStationFragment.getInstance();
                    replaceFragment(fragment, NEAR_STATION_TAG);
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayFragments() {
        Fragment fragment;
        if(mCurrentFragment.equals(DEPARTURE_TAG)) {
            fragment = getSupportFragmentManager().findFragmentByTag(DEPARTURE_TAG);
            if(fragment == null) {
                fragment = DeparturesFragment.newInstance();
                displayFragment(fragment, DEPARTURE_TAG);
            }
        } else {
            fragment = getSupportFragmentManager().findFragmentByTag(NEAR_STATION_TAG);
            if(fragment == null) {
                fragment = NearStationFragment.getInstance();
                displayFragment(fragment, NEAR_STATION_TAG);
            }
        }
    }

    private void displayFragment(Fragment fragment, String tag) {
        mCurrentFragment = tag;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, tag)
                .commit();
    }

    private void replaceFragment(Fragment fragment, String tag) {
        mCurrentFragment = tag;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }

    @Override
    public void onListFragmentInteraction(StationJson station) {
        mCurrentFragment = DEPARTURE_TAG;
        Fragment departureFragment = DeparturesFragment.newInstance(station.getStation_code());
        replaceFragment(departureFragment, DEPARTURE_TAG);
    }
}
