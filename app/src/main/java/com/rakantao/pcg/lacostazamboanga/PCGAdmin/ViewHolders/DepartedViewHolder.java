package com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class DepartedViewHolder extends RecyclerView.ViewHolder {

    public Button btnarrive;
    public Button btnDistress;
    public ImageView imagevessel;
    public TextView vesselname,ATD,vesseltype,vesselorigin,vesseldesination,vesseldeparttime,vesselarrivetime,vesselschedday,vesselhourstravelled,timefornotif,minfornotif,distressnotifieradmin;
    View view;


    public DepartedViewHolder(View itemView) {
        super(itemView);
        view = itemView;

        btnarrive = itemView.findViewById(R.id.BTNArrived);
        btnDistress = itemView.findViewById(R.id.btnDistress);
        imagevessel = itemView.findViewById(R.id.TVDepartedImageVessel);

        ATD = itemView.findViewById(R.id.actualtimeDeparted);
        vesselname = itemView.findViewById(R.id.TVDepartedVesselName);
        vesseltype = itemView.findViewById(R.id.TVDepartedVesselType);
        vesselorigin = itemView.findViewById(R.id.TVDepartedOrigin);
        vesseldesination = itemView.findViewById(R.id.TVDepartedDestination);
        vesseldeparttime = itemView.findViewById(R.id.TVDepartedOriginTime);
        vesselarrivetime = itemView.findViewById(R.id.TVDepartedArrivalTime);
        vesselschedday = itemView.findViewById(R.id.TVDepartedSchedDay);
        vesselhourstravelled = itemView.findViewById(R.id.TVDepartedHoursTravelled);
        distressnotifieradmin = itemView.findViewById(R.id.TvDistressNotifierad);

        timefornotif = itemView.findViewById(R.id.hourfornotif);
        minfornotif = itemView.findViewById(R.id.minfornotif);

    }
}
