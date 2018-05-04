package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.appdev.debsourav.childtrackerforparent.ChildList.childID;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference mref;
    double lat;
    double lng;
    public Marker marker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setTitle("Current Location");

        mref = FirebaseDatabase.getInstance().getReference().child(childID).child("Location");
        Button button= (Button) findViewById(R.id.btnid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),drawing_Line.class));
            }
        });



        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                if(marker!= null)
                    marker.remove();

                Sample sample= dataSnapshot.getValue(Sample.class);
                lat= sample.getLat();
                lng= sample.getLong();

                LatLng sydney = new LatLng(lat,lng);
                String str= String.valueOf(lat) +" "+ String.valueOf(lng) ;

                marker = mMap.addMarker(new MarkerOptions().position(sydney).title("Current Location: " + str));
                Toast.makeText(getBaseContext(),"Lat: "+String.valueOf(lat)+", Long: "+String.valueOf(lng),Toast.LENGTH_LONG).show();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 15.0f ) );

    }

}
