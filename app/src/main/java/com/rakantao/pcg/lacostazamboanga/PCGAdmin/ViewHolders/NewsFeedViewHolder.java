package com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rakantao.pcg.lacostazamboanga.R;

public class NewsFeedViewHolder  extends RecyclerView.ViewHolder{

    public TextView tvNewsFeedTitle;
    public TextView tvNewsFeedContent;
    public TextView tvNewsFeedDate;
    public ImageView imgNewsFeedThumb;
    public View view;

    public NewsFeedViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;

        tvNewsFeedTitle = itemView.findViewById(R.id.tvNewsFeedTitle);
        tvNewsFeedContent = itemView.findViewById(R.id.tvNewsFeedContent);
        tvNewsFeedDate = itemView.findViewById(R.id.tvNewsFeedDate);
        imgNewsFeedThumb = itemView.findViewById(R.id.imgNewsFeedThumb);

    }
}
