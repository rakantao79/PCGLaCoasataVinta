package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments;


import android.content.Intent;
import android.os.Bundle;
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
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.ArrivedDetails;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataArrivedInfo;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataVesselInfo;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.ArrivalDashBoardViewHolder;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.ArrivedViewHolder;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.DeparturesDashBoardViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulesDashBoard extends Fragment {


    View view;
    RecyclerView depart;
    RecyclerView arrival;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef,childRef2;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mUserDatabase;
    private LinearLayoutManager linearLayoutManager2;

    public SchedulesDashBoard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_schedules_dash_board, container, false);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager2 = new LinearLayoutManager(getContext());

        depart = view.findViewById(R.id.RecyclerDepartures);
        arrival = view.findViewById(R.id.RecyclerArrivals);


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child("Sunday").child("Arrived");

                childRef2 = mDatabaseRef.child("VesselSchedule").child("Sunday").child("Arrived");
                break;
            case Calendar.MONDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Monday")).child("Arrived");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Monday")).child("Arrived");
                break;
            case Calendar.TUESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Tuesday")).child("Arrived");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Tuesday")).child("Arrived");
                break;
            case Calendar.WEDNESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Wednesday")).child("Arrived");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Wednesday")).child("Arrived");
                break;
            case Calendar.THURSDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Thursday")).child("Arrived");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Thursday")).child("Arrived");
                break;
            case Calendar.FRIDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Friday")).child("Arrived");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Friday")).child("Arrived");
                break;
            case Calendar.SATURDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Saturday")).child("Arrived");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Saturday")).child("Arrived");
                break;
        }

        depart.setLayoutManager(linearLayoutManager);
        arrival.setLayoutManager(linearLayoutManager2);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DataVesselInfo, DeparturesDashBoardViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<DataVesselInfo, DeparturesDashBoardViewHolder>(

                        DataVesselInfo.class,
                        R.layout.departed_dashboard_listrow,
                        DeparturesDashBoardViewHolder.class,
                        childRef

                ) {
                    @Override
                    protected void populateViewHolder(DeparturesDashBoardViewHolder viewHolder, DataVesselInfo model, int position) {

                    }
                };
        depart.setAdapter(firebaseRecyclerAdapter);

        FirebaseRecyclerAdapter<DataVesselInfo, ArrivalDashBoardViewHolder> firebaseRecyclerAdapter2 =
                new FirebaseRecyclerAdapter<DataVesselInfo, ArrivalDashBoardViewHolder>(

                        DataVesselInfo.class,
                        R.layout.arrivals_dashboard_listrow,
                        ArrivalDashBoardViewHolder.class,
                        childRef2

                ) {
                    @Override
                    protected void populateViewHolder(ArrivalDashBoardViewHolder viewHolder, DataVesselInfo model, int position) {

                    }
                };
        arrival.setAdapter(firebaseRecyclerAdapter2);


    }
}
