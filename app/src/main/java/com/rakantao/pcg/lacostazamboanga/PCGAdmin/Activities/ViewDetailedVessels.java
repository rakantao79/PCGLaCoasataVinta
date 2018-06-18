package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataVesselSched;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.PendingViewholder;
import com.rakantao.pcg.lacostazamboanga.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ViewDetailedVessels extends AppCompatActivity {

    ImageView vImage;
    TextView vName,vType,vOrigin,vDestination,vETA,vETD,vInvestigator,vTimeStamp;
    RecyclerView recyclerView;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mUserDatabase;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detailed_vessels);


        vImage = findViewById(R.id.TVDetailedvImage);

        vName = findViewById(R.id.TVDetailedvName);
        vType = findViewById(R.id.TVDetailedvType);
        vOrigin = findViewById(R.id.TVDetailedvOrigin);
        vDestination = findViewById(R.id.TVDetailedvDestination);
        vETA = findViewById(R.id.TVDetailedvETA);
        vETD = findViewById(R.id.TVDetailedvETD);
        vInvestigator = findViewById(R.id.TVDetailedvInspector);
        vTimeStamp = findViewById(R.id.TVDetailedvTimeStamp);

        recyclerView = findViewById(R.id.recyclerVesselDetail);


        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        childRef = mDatabaseRef.child("VesselDetails");

        recyclerView.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DataVesselSched, PendingViewholder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<DataVesselSched, PendingViewholder>(

                        DataVesselSched.class,
                        R.layout.pending_listrow,
                        PendingViewholder.class,
                        childRef.orderByChild("VesselStatus").equalTo("Pending")
                ) {
                    @Override
                    protected void populateViewHolder(final PendingViewholder viewHolder, DataVesselSched model, int position) {

                        viewHolder.vesseltype.setText(model.getVesselType());
                        viewHolder.vesselname.setText(model.getVesselName());
                        viewHolder.origin.setText(model.getOrigin());
                        viewHolder.destination.setText(model.getDestination());
                        viewHolder.departime.setText(model.getDepartureTime());
                        viewHolder.arrivaltime.setText(model.getArrivalTime());
                        viewHolder.schedday.setText(model.getScheduleDay());


                        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("VesselImage").child(model.getVesselName());

                        mUserDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    final String image = dataSnapshot.child("image").getValue().toString();

                                    if (!image.equals("default")){
                                        Picasso.with(ViewDetailedVessels.this)
                                                .load(image)
                                                .fit().centerCrop()
                                                .networkPolicy(NetworkPolicy.OFFLINE)
                                                .placeholder(R.drawable.zz)
                                                .into(viewHolder.vesselimage , new Callback() {
                                                    @Override
                                                    public void onSuccess() {

                                                    }

                                                    @Override
                                                    public void onError() {
                                                        Picasso.with(ViewDetailedVessels.this)
                                                                .load(image)
                                                                .placeholder(R.drawable.zz)
                                                                .into(viewHolder.vesselimage);
                                                    }
                                                });
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
