package uta.utap;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 * Created by emiko on 11/29/2017.
 */

public class LotController
{
    private static final LotController lotController = new LotController();
    private static HashMap<Lot, Polygon> m_StatusColors = new HashMap<>();
    private ArrayList<Lot> m_Lots;
    private ArrayList<Lot> m_RecommendedLots;

    private LotController()
    {
        m_Lots = new ArrayList<>();
        m_RecommendedLots = new ArrayList<>();
        createLots();
    }

    public static LotController getInstance()
    {
        return lotController;
    }

    public ArrayList<Lot> getLots()
    {
        return m_Lots;
    }

    public ArrayList<Lot> getRecommendedLots(Location loc)
    {
        m_RecommendedLots.clear();
        for(int i = 0; i < m_Lots.size(); i++)
        {
            if(loc.distanceTo(m_Lots.get(i).getLocation()) < AccountController.getInstance().getUser().getUserSettings().getLotSettings().getMaxLotDistance())
            {
                m_RecommendedLots.add(m_Lots.get(i));
            }
        }
        return m_RecommendedLots;
    }

    public Polygon getLotPoly(Lot lot)
    {
        return m_StatusColors.get(lot);
    }

    public void addLotPoly(Lot lot, Polygon poly)
    {
        m_StatusColors.put(lot, poly);
    }

    private void createLots()
    {
        Lot.Status defaultStatus = Lot.Status.AVAILABLE;
        Location location = new Location("");

        // TODO add some faculty lots too
        double[][] studentLotLoc =
                {
                        {32.724090, -97.130127},
                        {32.727163, -97.126912},
                        {32.729737, -97.119459},
                        {32.731351, -97.119639},
                        {32.734474, -97.113214},
                        {32.733420, -97.116782},
                        {32.733223, -97.111548},
                        {32.729441, -97.111623}
                };

        // TODO finish adding lot poly points
        double[][][] polyPoints =
                {
                        {{32.724229, -97.129930}, {32.723561, -97.129938}, {32.723581, -97.130314}, {32.724226, -97.130306}},
                        {{32.727767, -97.127544}, {32.727749, -97.126283}, {32.726291, -97.126350}, {32.726314, -97.127555}},
                        {{32.729917, -97.118823}, {32.729670, -97.118819}, {32.729656, -97.120147}, {32.729911, -97.120152}},
                        {{32.731326, -97.120462}, {32.731322, -97.118968}, {32.731004, -97.118949}, {32.731017, -97.120476}},
                        {{32.734993, -97.113477}, {32.734993, -97.112935}, {32.733878, -97.112956}, {32.733887, -97.113466}},
                        {{32.734012, -97.117674}, {32.734012, -97.116606}, {32.732699, -97.116585}, {32.732722, -97.117669}},
                        {{32.733566, -97.111918}, {32.733575, -97.111188}, {32.732686, -97.111183}, {32.732695, -97.111929}},
                        {{32.729579, -97.112055}, {32.729579, -97.111165}, {32.729245, -97.111173}, {32.729245, -97.112069}}

                };

        Vector<LatLng> points = new Vector<>();

        for(int i = 0; i < studentLotLoc.length && i < polyPoints.length; i++)
        {
            points = new Vector<>();
            location = new Location("");

            for(int j = 0; j < polyPoints[i].length; j++)
            {
                points.addElement(new LatLng(polyPoints[i][j][0], polyPoints[i][j][1]));
            }

            location.setLatitude(studentLotLoc[i][0]);
            location.setLongitude(studentLotLoc[i][1]);

            m_Lots.add(new Lot(location, points, false, defaultStatus));
        }

        fakeStatus();
    }

    private void fakeStatus()
    {
        String[] lotNames =
                {
                        "Lot 25",
                        "Lot 26",
                        "Trinity West Parking",
                        "Lot 30",
                        "Lot 36",
                        "West Campus Parking Garage",
                        "Lot F12",
                        "Maverick Parking Garage"
                };

        for(int i = 0; i < m_Lots.size(); i++)
        {
            Random rand = new Random();

            switch(rand.nextInt(3))
            {
                case 0:
                    m_Lots.get(i).setStatus(Lot.Status.AVAILABLE);
                break;
                case 1:
                    m_Lots.get(i).setStatus(Lot.Status.BUSY);
                    break;
                case 2:
                    m_Lots.get(i).setStatus(Lot.Status.UNAVAILABLE);
                    break;
                default:
                    break;
            }

            m_Lots.get(i).setName(lotNames[i]);

        }
    }


}
