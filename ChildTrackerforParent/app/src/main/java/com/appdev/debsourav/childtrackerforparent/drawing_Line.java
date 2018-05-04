package com.appdev.debsourav.childtrackerforparent;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import static com.appdev.debsourav.childtrackerforparent.ChildList.childID;


public class drawing_Line extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static ArrayList<LatLng> point;
    DatabaseReference routePolyDB,mref;
    Marker marker;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing__line);

        setTitle("Location History");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMaxZoomPreference(15.0f);
        drawBusRoute();
    }

    void drawBusRoute(){

        point = new ArrayList<>();
        routePolyDB = FirebaseDatabase.getInstance().getReference().child(childID).child("Location");
        routePolyDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot tempDataSnap : dataSnapshot.getChildren()){

                    Sample sample= tempDataSnap.getValue(Sample.class);
                    double lat= sample.getLat();
                    double lng= sample.getLong();


                    LatLng aPoint = new LatLng(lat,lng);
                    if (flag==0)
                    {
                        mMap.addMarker(new MarkerOptions().position(aPoint).title("Marker in Start Position"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(aPoint));
                        mMap.animateCamera( CameraUpdateFactory.zoomTo( 21.0f ) );
                        flag=1;

                    }
                    point.add(aPoint);
                }
                drawPolyline(point);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void drawPolyline(ArrayList<LatLng> arg){
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(arg);
        polylineOptions.width(6).color(Color.BLUE);
        mMap.addPolyline( polylineOptions );
    }
}

