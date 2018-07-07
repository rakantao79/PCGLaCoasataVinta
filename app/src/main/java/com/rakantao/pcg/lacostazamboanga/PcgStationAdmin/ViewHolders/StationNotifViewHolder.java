package com.rakantao.pcg.lacostazamboanga.PcgStationAdmin.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class StationNotifViewHolder extends RecyclerView.ViewHolder {

    public TextView stationname,notifdesc,notifduration;
    View view;
    public LinearLayout notifLayout;

    public StationNotifViewHolder(View itemView) {
        super(itemView);

        view = itemView;
        notifLayout = itemView.findViewById(R.id.notifLayout);
        stationname = itemView.findViewById(R.id.TVStationNameNotif);
        notifdesc = itemView.findViewById(R.id.TVNotifMessage);
        notifduration = itemView.findViewById(R.id.TVNotifDuration);
    }
}
