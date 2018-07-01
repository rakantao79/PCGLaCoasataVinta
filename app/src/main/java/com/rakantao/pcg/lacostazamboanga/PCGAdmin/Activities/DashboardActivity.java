package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments.SchedulesDashBoard;
import com.rakantao.pcg.lacostazamboanga.R;

public class DashboardActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.dashContainer,new SchedulesDashBoard());
        fragmentTransaction.commit();

    }
}
