package com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class DetailedVesselViewHolder extends RecyclerView.ViewHolder {

    public ImageView vImage;
    public TextView ImageDesc,TimeStamp;

    public DetailedVesselViewHolder(View itemView) {
        super(itemView);

        vImage = itemView.findViewById(R.id.DetailedVesselImage);
        ImageDesc = itemView.findViewById(R.id.DetailedVesselDescription);
        TimeStamp = itemView.findViewById(R.id.DetailedVesselDate);
    }
}
