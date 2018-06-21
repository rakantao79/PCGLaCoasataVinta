package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities;

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
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataImageReport;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataVesselSched;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.DetailedVesselViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ArrivedDetails extends AppCompatActivity {

    private TextView tvVesselName;
    private TextView tvVesselType;
    private TextView tvVesselOrigin;
    private TextView tvVesselDestination;
    private TextView tvVesselETD;
    private TextView tvVesselETA;
    private TextView tvInspector;
    private TextView tvTimeStamp;
    private TextView tvActualTimeArrived;
    private TextView tvDelayed;
    private ImageView ImageVessel;
    RecyclerView recyclerView;
    String VesselName;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mUserDatabase,databaseReferencenew;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrived_details);

        tvVesselName = findViewById(R.id.ArrivedDetailVesselName);
        tvVesselType = findViewById(R.id.ArrivedDetailVesselType);
        tvVesselOrigin = findViewById(R.id.ArrivedDetailOrigin);
        tvVesselDestination = findViewById(R.id.ArrivedDetailDestination);
        tvVesselETD = findViewById(R.id.ArrivedDetailETD);
        tvVesselETA = findViewById(R.id.ArrivedDetailETA);
        tvInspector = findViewById(R.id.ArrivedDetailInspector);
        tvTimeStamp = findViewById(R.id.ArrivedDetailTimeStamp);
        tvActualTimeArrived = findViewById(R.id.ArrivedDetailActualTimeArrived);
        tvDelayed = findViewById(R.id.ArrivedDetailActualTimeDelayed);
        ImageVessel = findViewById(R.id.ArrivedDetailImage);
        recyclerView = findViewById(R.id.ArrivedDetailRecyclerView);

        VesselName = getIntent().getStringExtra("vesselName");

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        childRef = mDatabaseRef.child("AdminImagesReport");
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DataImageReport, DetailedVesselViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<DataImageReport, DetailedVesselViewHolder>(

                        DataImageReport.class,
                        R.layout.detailedvessel_listrow,
                        DetailedVesselViewHolder.class,
                        childRef.child(VesselName)
                ) {
                    @Override
                    protected void populateViewHolder(final DetailedVesselViewHolder viewHolder, final DataImageReport model, int position) {
                        viewHolder.vImage(model.getImageUrl(), getApplicationContext());

                        mUserDatabase = FirebaseDatabase.getInstance().getReference();

                        mUserDatabase.child("VesselDetails").child(VesselName).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final DataVesselSched dataVesselSched = dataSnapshot.getValue(DataVesselSched.class);
                                if (dataSnapshot.exists()){

                                    tvVesselName.setText(dataVesselSched.getVesselName());
                                    tvVesselType.setText(dataVesselSched.getVesselType());
                                    tvVesselDestination.setText(dataVesselSched.getDestination());
                                    tvVesselETA.setText(dataVesselSched.getArrivalTime());
                                    tvVesselETD.setText(dataVesselSched.getDepartureTime());
                                    tvVesselOrigin.setText(dataVesselSched.getOrigin());

                                    databaseReferencenew = FirebaseDatabase.getInstance().getReference();

                                    databaseReferencenew.child("ReportAdmin").child(dataVesselSched.getVesselName()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()){

                                                tvInspector.setText(dataSnapshot.child("inspector").getValue().toString());
                                                tvTimeStamp.setText(dataSnapshot.child("timeUploaded").getValue().toString());



                                                DatabaseReference mUserDatabase1 = FirebaseDatabase.getInstance().getReference().child("VesselImage").child(dataVesselSched.getVesselName());

                                                mUserDatabase1.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.exists()){
                                                            final String image = dataSnapshot.child("image").getValue().toString();

                                                            if (!image.equals("default")){
                                                                Picasso.with(ArrivedDetails.this)
                                                                        .load(image)
                                                                        .fit().centerCrop()
                                                                        .networkPolicy(NetworkPolicy.OFFLINE)
                                                                        .placeholder(R.drawable.zz)
                                                                        .into(ImageVessel , new Callback() {
                                                                            @Override
                                                                            public void onSuccess() {

                                                                            }

                                                                            @Override
                                                                            public void onError() {
                                                                                Picasso.with(ArrivedDetails.this)
                                                                                        .load(image)
                                                                                        .placeholder(R.drawable.zz)
                                                                                        .into(ImageVessel);
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
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

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
