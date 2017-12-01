package uta.utap;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//      EditText searchBar = (EditText) findViewById(R.id.searchBox);
//      searchBar.setBackgroundColor(Color.WHITE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager.beginTransaction().replace(R.id.main_container, new MapFragment()).commit();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint("Enter destination");
        autocompleteFragment.setBoundsBias(new LatLngBounds(new LatLng(32.721236, -97.131974),new LatLng(32.735572, -97.102780)));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Location destination = new Location("");
                destination.setLatitude(place.getLatLng().latitude);
                destination.setLongitude(place.getLatLng().longitude);

                AccountController.getInstance().getUser().setDestination(destination);
                centerMap(place);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_profile)
        {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_setting)
        {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Login(View view) {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void SignUp(View view) {
        Intent intent=new Intent(this, SIgnup.class);
        startActivity(intent);
    }

//  public void onMapSearch(View view) {
//  }

//  public void onEnterDestination(View view)
//  {
//      try {
//          Intent intent =
//                  new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
//                          .build(this);
//          startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
//      } catch (GooglePlayServicesRepairableException e) {
//          // TODO: Handle the error.
//      } catch (GooglePlayServicesNotAvailableException e) {
//          // TODO: Handle the error.
//      }
//  }

    private void centerMap(final Place place)
    {
        MapView mapView = (MapView) findViewById(R.id.mapView);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.i("DEBUG", "onMapReady");

                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(place.getLatLng())
                                                       .title(place.getName().toString()));
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
            }
        });

    }

}
