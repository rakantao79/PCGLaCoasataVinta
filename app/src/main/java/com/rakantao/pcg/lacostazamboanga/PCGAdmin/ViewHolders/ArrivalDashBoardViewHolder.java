package com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class ArrivalDashBoardViewHolder extends RecyclerView.ViewHolder {


    public TextView tvorig,tvvesNme,tvTime,tvRemarks;

    public ArrivalDashBoardViewHolder(View itemView) {
        super(itemView);

        tvorig = itemView.findViewById(R.id.ArrivalsDashboardOrigin);
        tvvesNme = itemView.findViewById(R.id.ArrivalsDashboardVessel);
        tvTime = itemView.findViewById(R.id.ArrivalsDashboardTime);
        tvRemarks = itemView.findViewById(R.id.ArrivalsDashboardRemarks);

    }
}
