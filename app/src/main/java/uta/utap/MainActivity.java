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
import android.view.Menu;
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
    private static boolean displayList = false;
    FragmentManager fragmentManager = getFragmentManager();
    ArrayList<Marker> markerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        markerList.clear();

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

        boolean close = true;

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
        else if(id == R.id.nav_recommended)
        {
            displayList = !displayList;
            close = false;

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            ArrayList<Lot> lots = LotController.getInstance()
                                  .getRecommendedLots(AccountController.getInstance().
                                                      getUser().getDestination());

            Geocoder geo = new Geocoder(getApplicationContext());

            navigationView.getMenu().removeGroup(R.id.recommended_lots);
            navigationView.getMenu().getItem(2).setChecked(false);

            if(displayList)
            {
                Lot lot;
                for (int i = 0; i < lots.size(); i++) {
                    lot = lots.get(i);
                    try {
                        navigationView.getMenu().add(R.id.recommended_lots, i, Menu.NONE, (i + 1) + ". " + geo.getFromLocation(lot.getLocation().getLatitude(),
                                lot.getLocation().getLongitude(), 1).get(0)
                                .getFeatureName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            Geocoder geo = new Geocoder(getApplicationContext());
            ArrayList<Lot> lots = LotController.getInstance()
                    .getRecommendedLots(AccountController.getInstance().
                            getUser().getDestination());
            for(int i = 0; i < navigationView.getMenu().size(); i++)
            {
                if(id == i)
                {
                    LatLng latLng = new LatLng(lots.get(i).getLocation().getLatitude(),
                                               lots.get(i).getLocation().getLongitude());
                    try {
                        centerMap(latLng, geo.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getFeatureName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(close)
        {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
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

    private void centerMap(final Place place)
    {
        MapView mapView = (MapView) findViewById(R.id.mapView);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.i("DEBUG", "onMapReady");

                // Clear prev markers
                for(int i = 0; i < markerList.size(); i++)
                {
                    markerList.get(i).remove();
                }

                markerList.add(googleMap.addMarker(new MarkerOptions().position(place.getLatLng())
                                                       .title(place.getName().toString())));
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
            }
        });
    }

    private void centerMap(final LatLng latLng, final String name)
    {
        MapView mapView = (MapView) findViewById(R.id.mapView);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.i("DEBUG", "onMapReady");

                // Clear prev markers
                for(int i = 0; i < markerList.size(); i++)
                {
                    markerList.get(i).remove();
                }

                markerList.add(googleMap.addMarker(new MarkerOptions().position(latLng)
                        .title(name)));
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
    }

}
