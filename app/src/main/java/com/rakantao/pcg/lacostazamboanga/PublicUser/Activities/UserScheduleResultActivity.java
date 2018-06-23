package com.rakantao.pcg.lacostazamboanga.PublicUser.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class UserScheduleResultActivity extends AppCompatActivity {

    private TextView tvUserSchedFrom;
    private TextView tvUserSchedTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule_result);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        String date = getIntent().getStringExtra("date");


        tvUserSchedFrom = (TextView) findViewById(R.id.userSchedResultFrom);
        tvUserSchedTo = (TextView) findViewById(R.id.userSchedResultTo);

        tvUserSchedFrom.setText(from);
        tvUserSchedTo.setText(to);

    }
}
