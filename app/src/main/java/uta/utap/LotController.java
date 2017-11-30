package uta.utap;

import android.app.FragmentManager;
import android.graphics.Color;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emiko on 11/29/2017.
 */

public class LotController
{
    private static final LotController lotController = new LotController();
    private static HashMap<Lot, Polygon> m_StatusColors = new HashMap<>();

    private LotController()
    {

    }

    private void createLots()
    {
        Lot.Status defaultStatus = Lot.Status.AVAILABLE;
        Location location = new Location("");

        double[][] studentLotLoc =
                {
                        {32.724090, -97.130127},
                        {32.727163, -97.126912},
                        {32.729737, -97.119459},
                        {32.731351, -97.119639},
                        {32.730115, -97.117255},
                        {32.733420, -97.116782},
                        {32.733384, -97.115862},
                        {32.732822, -97.115446},
                        {32.734437, -97.113204},
                        {32.733237, -97.107688},
                        {32.732648, -97.107868},
                        {32.731721, -97.107793},
                        {32.728395, -97.107978},
                        {32.727400, -97.108053},
                        {32.724974, -97.108431},
                        {32.726312, -97.109209},
                        {32.725667, -97.110555},
                        {32.726037, -97.112886},
                        {32.724523, -97.112097},
                        {32.723546, -97.112033},
                        {32.723125, -97.110576}
                };

//      for(int i = 0; i < studentLotLocations.length; i++)
//      {

//      }
        location.setLatitude(studentLotLoc[0][0]);
        location.setLongitude(studentLotLoc[0][1]);
    }


}
