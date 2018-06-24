package com.rakantao.pcg.lacostazamboanga.PublicUser.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rakantao.pcg.lacostazamboanga.PublicUser.Datas.DataUserSchedList;
import com.rakantao.pcg.lacostazamboanga.PublicUser.ViewHolders.UserSchedViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;

import java.util.Calendar;

public class UserScheduleResultActivity extends AppCompatActivity {

    private TextView tvUserSchedFrom;
    private TextView tvUserSchedTo;
    private TextView tvUserSchedDay;

    private RecyclerView Recyclerview;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mUserDatabase;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule_result);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        date = getIntent().getStringExtra("date");


        tvUserSchedFrom = (TextView) findViewById(R.id.userSchedResultFrom);
        tvUserSchedTo = (TextView) findViewById(R.id.userSchedResultTo);
        tvUserSchedDay = (TextView) findViewById(R.id.userSchedResultDay);

        tvUserSchedFrom.setText(from);
        tvUserSchedTo.setText(to);
        tvUserSchedDay.setText(date);

        linearLayoutManager = new LinearLayoutManager(this);
        Recyclerview = findViewById(R.id.userSchedRecyclerView);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child("Sunday");
                break;
            case Calendar.MONDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Monday"));
                break;
            case Calendar.TUESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Tuesday"));
                break;
            case Calendar.WEDNESDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Wednesday"));
                break;
            case Calendar.THURSDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Thursday"));
                break;
            case Calendar.FRIDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Friday"));
                break;
            case Calendar.SATURDAY:
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                childRef = mDatabaseRef.child("VesselSchedule").child(String.valueOf("Saturday"));
                break;
        }

        Recyclerview.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DataUserSchedList, UserSchedViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DataUserSchedList, UserSchedViewHolder>(

                DataUserSchedList.class,
                R.layout.user_schedule_list,
                UserSchedViewHolder.class,
                childRef.orderByChild("ScheduleDay").equalTo(date)

        ) {
            @Override
            protected void populateViewHolder(UserSchedViewHolder viewHolder, DataUserSchedList model, int position) {

                viewHolder.tvUserSchedVesselName.setText(model.getVesselName());
                viewHolder.tvUserSchedOrigin.setText(model.getOrigin());
                viewHolder.tvUserSchedDestination.setText(model.getDestination());
                viewHolder.tvUserSchedTimeDepart.setText(model.getDepartureTime());
                viewHolder.tvUserSchedTimeArrival.setText(model.getArrivalTime());

            }
        };
        Recyclerview.setAdapter(firebaseRecyclerAdapter);
    }
}
