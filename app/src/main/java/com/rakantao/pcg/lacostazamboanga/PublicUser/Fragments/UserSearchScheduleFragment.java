package com.rakantao.pcg.lacostazamboanga.PublicUser.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rakantao.pcg.lacostazamboanga.PublicUser.Activities.UserScheduleResultActivity;
import com.rakantao.pcg.lacostazamboanga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSearchScheduleFragment extends Fragment {

    private EditText searchSchedFrom;
    private EditText searchSchedTo;
    private EditText searchSchedDate;
    private Button btnSearchSched;

    private String from;
    private String to;
    private String date;

    public UserSearchScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_search_schedule, container, false);

        searchSchedFrom = (EditText) view.findViewById(R.id.searchSchedFrom);
        searchSchedTo = (EditText) view.findViewById(R.id.searchSchedTo);
        searchSchedDate = (EditText) view.findViewById(R.id.searchSchedDate);
        btnSearchSched = (Button) view.findViewById(R.id.btnSearchSched);

        searchSchedFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items2 = {
                        "Zamboanga City", "Isabela City","Lamitan City","Pagadian City","Ipil City","Margossatubig City","Malangas City"
                };
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                builder2.setTitle("Make your selection");
                builder2.setItems(items2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        searchSchedFrom.setText(items2[item]);
                    }
                });
                AlertDialog alert2 = builder2.create();
                alert2.show();
            }
        });

        searchSchedTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items2 = {
                        "Zamboanga City", "Isabela City","Lamitan City","Pagadian City","Ipil City","Margossatubig City","Malangas City"
                };
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                builder2.setTitle("Make your selection");
                builder2.setItems(items2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        searchSchedTo.setText(items2[item]);
                    }
                });
                AlertDialog alert2 = builder2.create();
                alert2.show();
            }
        });

        searchSchedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items2 = {
                        "Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"
                };
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                builder2.setTitle("Make your selection");
                builder2.setItems(items2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        searchSchedDate.setText(items2[item]);
                    }
                });
                AlertDialog alert2 = builder2.create();
                alert2.show();
            }
        });

        btnSearchSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchSched();
            }
        });

        return view;
    }

    private void searchSched() {

        from = searchSchedFrom.getText().toString().trim();
        to = searchSchedTo.getText().toString().trim();
        date = searchSchedDate.getText().toString().trim();

        if (TextUtils.isEmpty(from) ||
                TextUtils.isEmpty(to) ||
                TextUtils.isEmpty(date)){

            Toast.makeText(getContext(), "Please fill the empty fields", Toast.LENGTH_SHORT).show();

        } else {

            Intent intent = new Intent(getActivity(), UserScheduleResultActivity.class);
            intent.putExtra("from", from);
            intent.putExtra("to", to);
            intent.putExtra("date", date);
            startActivity(intent);

        }
    }
}
