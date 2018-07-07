package com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.Fragments;


import android.content.Intent;
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
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.ArrivedDetails;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataArrivedInfo;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.ArrivedViewHolder;
import com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.Datas.DataStationAdminNotif;
import com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.ViewHolders.StationNotifViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class StationAdminNotif extends Fragment {


    View view;
    private RecyclerView Recyclerview;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;
    String userID;
    public String Origin;

    public StationAdminNotif() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_station_admin_notif, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        linearLayoutManager = new LinearLayoutManager(getContext());
        Recyclerview = view.findViewById(R.id.recyclerNotification);
        Recyclerview.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        userID = currentUser.getUid();

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();

        databaseReference1.child("Users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    Origin = dataSnapshot.child("Station").getValue().toString();


                    mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                    childRef = mDatabaseRef.child("StationNotifHeader").child(Origin);


                    FirebaseRecyclerAdapter<DataStationAdminNotif, StationNotifViewHolder> firebaseRecyclerAdapter =
                            new FirebaseRecyclerAdapter<DataStationAdminNotif, StationNotifViewHolder>(

                                    DataStationAdminNotif.class,
                                    R.layout.notif_listrow,
                                    StationNotifViewHolder.class,
                                    childRef

                            ) {
                                @Override
                                protected void populateViewHolder(final StationNotifViewHolder viewHolder, final DataStationAdminNotif model, int position) {

                                    viewHolder.stationname.setText(model.getVesselName());
                                    viewHolder.notifdesc.setText(model.getDistressDescription());

                                    if (!model.getNotifStatus().equals("unread")){
                                        viewHolder.notifLayout.setBackgroundColor(getResources().getColor(R.color.white));
                                    }




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
}
