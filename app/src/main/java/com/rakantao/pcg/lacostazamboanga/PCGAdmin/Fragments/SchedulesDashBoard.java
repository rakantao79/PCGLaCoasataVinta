package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataVesselSched;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.ArrivalDashBoardViewHolder;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.DeparturesDashBoardViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;

import java.util.Calendar;

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
                childRef = mDatabaseRef.child("VesselSchedule").child("Sunday").child("Departed");

                childRef2 = mDatabaseRef.child("VesselSchedule").child("Sunday").child("Departed");
                break;
            case Calendar.MONDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Monday")).child("Departed");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Monday")).child("Departed");
                break;
            case Calendar.TUESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Tuesday")).child("Departed");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Tuesday")).child("Departed");
                break;
            case Calendar.WEDNESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Wednesday")).child("Departed");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Wednesday")).child("Departed");
                break;
            case Calendar.THURSDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Thursday")).child("Departed");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Thursday")).child("Departed");
                break;
            case Calendar.FRIDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Friday")).child("Departed");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Friday")).child("Departed");
                break;
            case Calendar.SATURDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Saturday")).child("Departed");

                childRef2 = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Saturday")).child("Departed");
                break;
        }

        depart.setLayoutManager(linearLayoutManager);
        arrival.setLayoutManager(linearLayoutManager2);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DataVesselSched, DeparturesDashBoardViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<DataVesselSched, DeparturesDashBoardViewHolder>(

                        DataVesselSched.class,
                        R.layout.departed_dashboard_listrow,
                        DeparturesDashBoardViewHolder.class,
                        childRef.orderByChild("DestinationStation").equalTo("CGS ZAMBOANGA")

                ) {
                    @Override
                    protected void populateViewHolder(DeparturesDashBoardViewHolder viewHolder, DataVesselSched model, int position) {

                        viewHolder.tvdes.setText(model.getDestination());
                        viewHolder.tvTime.setText(model.getDepartureTime());
                        viewHolder.tvvesNme.setText(model.getVesselName());
                    }
                };
        depart.setAdapter(firebaseRecyclerAdapter);

        FirebaseRecyclerAdapter<DataVesselSched, ArrivalDashBoardViewHolder> firebaseRecyclerAdapter2 =
                new FirebaseRecyclerAdapter<DataVesselSched, ArrivalDashBoardViewHolder>(

                        DataVesselSched.class,
                        R.layout.arrivals_dashboard_listrow,
                        ArrivalDashBoardViewHolder.class,
                        childRef2.orderByChild("OriginStation").equalTo("CGS ZAMBOANGA")

                ) {
                    @Override
                    protected void populateViewHolder(ArrivalDashBoardViewHolder viewHolder, DataVesselSched model, int position) {

                        viewHolder.tvorig.setText(model.getOrigin());
                        viewHolder.tvTime.setText(model.getArrivalTime());
                        viewHolder.tvvesNme.setText(model.getVesselName());
                    }
                };
        arrival.setAdapter(firebaseRecyclerAdapter2);


    }
}
