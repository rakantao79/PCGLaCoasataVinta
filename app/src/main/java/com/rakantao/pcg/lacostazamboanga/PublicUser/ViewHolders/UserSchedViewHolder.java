package com.rakantao.pcg.lacostazamboanga.PublicUser.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class UserSchedViewHolder extends RecyclerView.ViewHolder{

    public TextView tvUserSchedVesselName;
    public TextView tvUserSchedOrigin;
    public TextView tvUserSchedDestination;
    public TextView tvUserSchedTimeArrival;
    public TextView tvUserSchedTimeDepart;

    public UserSchedViewHolder(View itemView) {
        super(itemView);

        tvUserSchedVesselName = itemView.findViewById(R.id.tvUserSchedVesselName);
        tvUserSchedOrigin = itemView.findViewById(R.id.tvUserSchedResulOrigin);
        tvUserSchedDestination = itemView.findViewById(R.id.tvUserSchedResultDestination);
        tvUserSchedTimeDepart = itemView.findViewById(R.id.tvUserSchedResultTimeDepart);
        tvUserSchedTimeArrival = itemView.findViewById(R.id.tvUserSchedResultTimeArrive);

    }
}
