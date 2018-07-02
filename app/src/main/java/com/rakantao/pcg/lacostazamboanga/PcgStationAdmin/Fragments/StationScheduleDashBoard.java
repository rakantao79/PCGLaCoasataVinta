package com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataVesselSched;
import com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.ViewHolders.StationArrivalsViewHolder;
import com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.ViewHolders.StationDeparturesViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;

import java.util.Calendar;

public class StationScheduleDashBoard extends Fragment {

    View view;
    RecyclerView depart;
    RecyclerView arrival;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef,childRef2;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager2;
    String userID;
    public String Origin;
    private FirebaseAuth firebaseAuth;

    public StationScheduleDashBoard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_station_schedule_dash_board, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager2 = new LinearLayoutManager(getContext());

        depart = view.findViewById(R.id.recyclerStationDepart);
        arrival = view.findViewById(R.id.recyclerStationArrivals);


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child("Sunday");

                childRef2 = mDatabaseRef.child("VesselsDashBoardAdmin").child("Sunday");
                break;
            case Calendar.MONDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Monday"));

                childRef2 = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Monday"));
                break;
            case Calendar.TUESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Tuesday"));

                childRef2 = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Tuesday"));
                break;
            case Calendar.WEDNESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Wednesday"));

                childRef2 = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Wednesday"));
                break;
            case Calendar.THURSDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Thursday"));

                childRef2 = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Thursday"));
                break;
            case Calendar.FRIDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Friday"));

                childRef2 = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Friday"));
                break;
            case Calendar.SATURDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Saturday"));

                childRef2 = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Saturday"));
                break;
        }

        depart.setLayoutManager(linearLayoutManager);
        arrival.setLayoutManager(linearLayoutManager2);

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

                    FirebaseRecyclerAdapter<DataVesselSched, StationDeparturesViewHolder> firebaseRecyclerAdapter =
                            new FirebaseRecyclerAdapter<DataVesselSched, StationDeparturesViewHolder>(

                                    DataVesselSched.class,
                                    R.layout.station_schedule_dashboard_listrow,
                                    StationDeparturesViewHolder.class,
                                    childRef.orderByChild("OriginStation").equalTo(Origin)

                            ) {
                                @Override
                                protected void populateViewHolder(StationDeparturesViewHolder viewHolder, DataVesselSched model, int position) {

                                    viewHolder.tvdes.setText(model.getDestination());
                                    viewHolder.tvorigin.setText(model.getOrigin());
                                    viewHolder.tvTime.setText(model.getDepartureTime());
                                    viewHolder.tvvesNme.setText(model.getVesselName());
                                    viewHolder.tvRemarks.setText(model.getVesselStatus());

                                }
                            };
                    depart.setAdapter(firebaseRecyclerAdapter);

                    FirebaseRecyclerAdapter<DataVesselSched, StationArrivalsViewHolder> firebaseRecyclerAdapter2 =
                            new FirebaseRecyclerAdapter<DataVesselSched, StationArrivalsViewHolder>(

                                    DataVesselSched.class,
                                    R.layout.station_arrival_dashboard_listrow,
                                    StationArrivalsViewHolder.class,
                                    childRef2.orderByChild("DestinationStation").equalTo(Origin)

                            ) {
                                @Override
                                protected void populateViewHolder(StationArrivalsViewHolder viewHolder, DataVesselSched model, int position) {

                                    viewHolder.tvdes.setText(model.getDestination());
                                    viewHolder.tvorigin.setText(model.getOrigin());
                                    viewHolder.tvTime.setText(model.getDepartureTime());
                                    viewHolder.tvvesNme.setText(model.getVesselName());
                                    viewHolder.tvRemarks.setText(model.getVesselStatus());
                                }
                            };
                    arrival.setAdapter(firebaseRecyclerAdapter2);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
