package uta.utap;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;


/**
 * Created by emiko on 11/28/2017.
 */

public class MapFragment extends Fragment
{
    MapView mapView;
    LatLng position = new LatLng(32.736442, -97.117285);
    String markerText = "UTA";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map, container, false);

        //initialize map
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.i("DEBUG", "onMapReady");

                //add marker
                Marker marker  = googleMap.addMarker(new MarkerOptions().position(position).title(markerText));

                //zoom to position with level 16
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 15);
                googleMap.animateCamera(cameraUpdate);

                Polygon[] lotPolys =
                        {
                                googleMap.addPolygon(new PolygonOptions()
                                        .add(new LatLng(32.724229, -97.129930), new LatLng(32.723561, -97.129938), new LatLng(32.723581, -97.130314), new LatLng(32.724226, -97.130306))
                                        .strokeColor(Color.GREEN)
                                        .fillColor(Color.GREEN))
                        };
            }
        });


        return rootView;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    public MapView getMapView()
    {
        return mapView;
    }
}
