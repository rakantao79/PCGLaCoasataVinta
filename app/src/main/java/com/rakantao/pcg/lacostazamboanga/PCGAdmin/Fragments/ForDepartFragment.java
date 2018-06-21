package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.ViewDetailedVessels;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataVesselSched;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.PendingViewholder;
import com.rakantao.pcg.lacostazamboanga.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class ForDepartFragment extends Fragment {


    private RecyclerView Recyclerview;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mUserDatabase,databaseReference;
    View view;

    public ForDepartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_for_depart, container, false);


        linearLayoutManager = new LinearLayoutManager(getContext());
        Recyclerview = view.findViewById(R.id.recyclerPending);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();


        childRef = mDatabaseRef.child("VesselDetails");

        Recyclerview.setLayoutManager(linearLayoutManager);


        return view;
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
                    protected void populateViewHolder(final PendingViewholder viewHolder, final DataVesselSched model, int position) {

                        viewHolder.vesseltype.setText(model.getVesselType());
                        viewHolder.vesselname.setText(model.getVesselName());
                        viewHolder.origin.setText(model.getOrigin());
                        viewHolder.destination.setText(model.getDestination());
                        viewHolder.departime.setText(model.getDepartureTime());
                        viewHolder.arrivaltime.setText(model.getArrivalTime());
                        viewHolder.schedday.setText(model.getScheduleDay());

                        final Handler handler = new Handler();
                        final int delay = 1000; //milliseconds

                        handler.postDelayed(new Runnable(){
                            public void run(){
                                //do something
                                SimpleDateFormat format = new SimpleDateFormat("h:mm");
                                DateFormat df = new SimpleDateFormat("h:mm");
                                String date = df.format(Calendar.getInstance().getTime());
                                String actualTime = viewHolder.departime.getText().toString();
                                Date time1;
                                Date time2;

                                try {
                                    time1 = format.parse(date);
                                    time2 = format.parse(actualTime);

                                    long diff = time2.getTime() - time1.getTime() ;
                                    long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
                                    viewHolder.runnabletime.setText((int) minutes + " MINS");

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                handler.postDelayed(this, delay);
                            }
                        }, delay);


                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), ViewDetailedVessels.class);
                                intent.putExtra("vesselName", model.getVesselName());
                                startActivity(intent);
                            }
                        });


                        viewHolder.btnclear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DateFormat df = new SimpleDateFormat("h:mm a");
                                String date = df.format(Calendar.getInstance().getTime());
                                databaseReference = FirebaseDatabase.getInstance().getReference("VesselDetails").child(model.getVesselName()).child("VesselStatus");
                                databaseReference.setValue("Departed");

                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("VesselDetails").child((String) viewHolder.vesselname.getText()).child("ActualDepartedTime");
                                databaseReference1.setValue(date);

                            }
                        });


                        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("VesselImage").child(model.getVesselName());

                        mUserDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    final String image = dataSnapshot.child("image").getValue().toString();

                                    if (!image.equals("default")){
                                        Picasso.with(getContext())
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
                                                        Picasso.with(getContext())
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
        Recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
