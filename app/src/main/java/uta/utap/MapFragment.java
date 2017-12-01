package uta.utap;

import android.app.Fragment;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

                ArrayList<Lot> lots = LotController.getInstance().getLots();
                Lot lot;

                for(int i = 0; i < lots.size(); i++)
                {
                    lot = lots.get(i);
                    int color = AccountController.getInstance().getUser().getUserSettings()
                                  .getColorSettings().getColor(lot.getStatus());

                    // TODO set fill color based on lot status
                    Polygon lotPoly =
                            googleMap.addPolygon(new PolygonOptions()
                            .addAll(lots.get(i).getPolyPoints())
                            .strokeWidth(5)
                            .strokeColor(color)
                            .fillColor(color));

                    LotController.getInstance().addLotPoly(lots.get(i), lotPoly);
                }

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
