package com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.ViewDetailedVessels;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataVesselSched;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.DepartedViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class StationArrivingFragment extends Fragment {

    private RecyclerView Recyclerview;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mUserDatabase,databaseReference;
    View view;
    private FirebaseAuth firebaseAuth;
    String userID;
    public String Origin;

    public StationArrivingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_station_arriving, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        linearLayoutManager = new LinearLayoutManager(getContext());
        Recyclerview = view.findViewById(R.id.recyclerArrivingStationAdmin);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child("Sunday").child("Departed");
                break;
            case Calendar.MONDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Monday")).child("Departed");
                break;
            case Calendar.TUESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Tuesday")).child("Departed");
                break;
            case Calendar.WEDNESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Wednesday")).child("Departed");
                break;
            case Calendar.THURSDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Thursday")).child("Departed");
                break;
            case Calendar.FRIDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Friday")).child("Departed");
                break;
            case Calendar.SATURDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Saturday")).child("Departed");
                break;
        }

        Recyclerview.setLayoutManager(linearLayoutManager);



        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        userID =  currentUser.getUid();

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();

        databaseReference1.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    Origin = dataSnapshot.child("Station").getValue().toString();

                        FirebaseRecyclerAdapter<DataVesselSched, DepartedViewHolder> firebaseRecyclerAdapter =
                                new FirebaseRecyclerAdapter<DataVesselSched, DepartedViewHolder>(

                                        DataVesselSched.class,
                                        R.layout.departed_listrow,
                                        DepartedViewHolder.class,
                                        childRef.orderByChild("DestinationStation").equalTo(Origin)

                                ) {
                                    @Override
                                    protected void populateViewHolder(final DepartedViewHolder viewHolder, final DataVesselSched model, int position) {

                                        viewHolder.vesseltype.setText(model.getVesselType());
                                        viewHolder.vesselname.setText(model.getVesselName());
                                        viewHolder.vesselorigin.setText(model.getOrigin());
                                        viewHolder.vesseldesination.setText(model.getDestination());
                                        viewHolder.vesseldeparttime.setText(model.getDepartureTime());
                                        viewHolder.vesselarrivetime.setText(model.getArrivalTime());
                                        viewHolder.vesselschedday.setText(model.getScheduleDay());
                                        viewHolder.ATD.setText(model.getActualDepartedTime());


                                        final Handler handler = new Handler();
                                        final int delay = 1000; //milliseconds

                                        handler.postDelayed(new Runnable(){
                                            public void run(){
                                                //do something
                                                SimpleDateFormat format = new SimpleDateFormat("h:mm a");
                                                DateFormat df = new SimpleDateFormat("h:mm a");
                                                String date = df.format(Calendar.getInstance().getTime());
                                                String actualTime = viewHolder.ATD.getText().toString();
                                                Date time1;
                                                Date time2;

                                                try {

                                                    time2 = format.parse(date);
                                                    time1 = format.parse(actualTime);

                                                    long diff = time2.getTime() - time1.getTime()  ;
                                                    long secondsInMilli = 1000;
                                                    long minutesInMilli = secondsInMilli * 60;
                                                    long hoursInMilli = minutesInMilli * 60;

                                                    long elapsedHours = diff / hoursInMilli;
                                                    diff = diff % hoursInMilli;

                                                    long elapsedMinutes = diff / minutesInMilli;

                                                    viewHolder.vesselhourstravelled.setText(elapsedHours+ " Hr(s) : "+ elapsedMinutes+" Min(s)");


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

                                        viewHolder.btnarrive.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                DateFormat df = new SimpleDateFormat("h:mm a");
                                                String date = df.format(Calendar.getInstance().getTime());

                                                //Details
                                                databaseReference = FirebaseDatabase.getInstance()
                                                        .getReference("VesselDetails")
                                                        .child((String) viewHolder.vesselname.getText())
                                                        .child("VesselStatus");
                                                databaseReference.setValue("Arrived");

                                                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance()
                                                        .getReference("VesselDetails")
                                                        .child((String) viewHolder.vesselname.getText())
                                                        .child("TravelledTime");
                                                databaseReference1.setValue(viewHolder.vesselhourstravelled.getText());

                                                DatabaseReference databaseReference2 = FirebaseDatabase.getInstance()
                                                        .getReference("VesselDetails")
                                                        .child((String) viewHolder.vesselname.getText())
                                                        .child("ActualTimeArrived");
                                                databaseReference2.setValue(date);
                                                //Details
                                                //Schedule
                                                DatabaseReference databaseReference3 = FirebaseDatabase.getInstance()
                                                        .getReference("VesselSchedule")
                                                        .child(model.getScheduleDay())
                                                        .child("Departed")
                                                        .child(model.getKey())
                                                        .child("VesselStatus");
                                                databaseReference3.setValue("Arrived");

                                                DatabaseReference databaseReference4 = FirebaseDatabase.getInstance()
                                                        .getReference("VesselSchedule")
                                                        .child(model.getScheduleDay())
                                                        .child("Departed")
                                                        .child(model.getKey())
                                                        .child("TravelledTime");
                                                databaseReference4.setValue(viewHolder.vesselhourstravelled.getText());

                                                DatabaseReference databaseReference5 = FirebaseDatabase.getInstance()
                                                        .getReference("VesselSchedule")
                                                        .child(model.getScheduleDay())
                                                        .child("Departed")
                                                        .child(model.getKey())
                                                        .child("ActualTimeArrived");
                                                databaseReference5.setValue(date);
                                                //Schedule
                                                //Move Query
                                                DatabaseReference From = FirebaseDatabase.getInstance()
                                                        .getReference("VesselSchedule")
                                                        .child(model.getScheduleDay())
                                                        .child("Departed")
                                                        .child(model.getKey());

                                                DatabaseReference To = FirebaseDatabase.getInstance()
                                                        .getReference("VesselSchedule")
                                                        .child(model.getScheduleDay())
                                                        .child("Arrived")
                                                        .child(model.getKey());

                                                moveFirebaseRecord1(From ,To);
                                                //Move Query

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
                                                                .into(viewHolder.imagevessel , new Callback() {
                                                                    @Override
                                                                    public void onSuccess() {

                                                                    }

                                                                    @Override
                                                                    public void onError() {
                                                                        Picasso.with(getContext())
                                                                                .load(image)
                                                                                .placeholder(R.drawable.zz)
                                                                                .into(viewHolder.imagevessel);
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void moveFirebaseRecord1(final DatabaseReference fromPath, final DatabaseReference toPath) {
        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                        if (firebaseError != null) {
                            Toast.makeText(getContext(), "Copy failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            fromPath.removeValue();
                        }
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Toast.makeText(getContext(), "Copy failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
