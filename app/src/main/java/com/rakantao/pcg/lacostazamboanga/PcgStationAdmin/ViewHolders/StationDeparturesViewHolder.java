package com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class StationDeparturesViewHolder extends RecyclerView.ViewHolder {

    public TextView tvdes,tvorigin,tvvesNme,tvTime,tvRemarks;

    public StationDeparturesViewHolder(View itemView) {
        super(itemView);

        tvdes = itemView.findViewById(R.id.StationDeparturesDashboardDestination);
        tvorigin = itemView.findViewById(R.id.StationDeparturesDashboardOrigin);
        tvvesNme = itemView.findViewById(R.id.StationDeparturesDashboardVessel);
        tvTime = itemView.findViewById(R.id.StationDeparturesDashboardTime);
        tvRemarks = itemView.findViewById(R.id.StationDeparturesDashboardRemarks);
    }
}
