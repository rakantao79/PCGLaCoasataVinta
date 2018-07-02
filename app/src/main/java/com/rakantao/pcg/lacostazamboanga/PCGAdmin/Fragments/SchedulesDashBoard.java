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
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef,childRef2;
    private LinearLayoutManager linearLayoutManager;


    public SchedulesDashBoard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_schedules_dash_board, container, false);

        linearLayoutManager = new LinearLayoutManager(getContext());


        depart = view.findViewById(R.id.RecyclerDepartures);



        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child("Sunday");


                break;
            case Calendar.MONDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Monday"));

                break;
            case Calendar.TUESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Tuesday"));

                break;
            case Calendar.WEDNESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Wednesday"));

                break;
            case Calendar.THURSDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Thursday"));


                break;
            case Calendar.FRIDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Friday"));

                break;
            case Calendar.SATURDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselsDashBoardAdmin").child(String.valueOf("Saturday"));

                break;
        }

        depart.setLayoutManager(linearLayoutManager);


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
                        childRef

                ) {
                    @Override
                    protected void populateViewHolder(DeparturesDashBoardViewHolder viewHolder, DataVesselSched model, int position) {

                        viewHolder.tvdes.setText(model.getDestination());
                        viewHolder.tvorigin.setText(model.getOrigin());
                        viewHolder.tvTime.setText(model.getDepartureTime());
                        viewHolder.tvvesNme.setText(model.getVesselName());
                        viewHolder.tvRemarks.setText(model.getVesselStatus());

                    }
                };
        depart.setAdapter(firebaseRecyclerAdapter);
    }
}
