package com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.Fragments.StationScheduleDashBoard;
import com.rakantao.pcg.lacostazamboanga.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StationDashBoard extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_dash_board);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.stationContainer, new StationScheduleDashBoard())
                .commit();
    }
}
