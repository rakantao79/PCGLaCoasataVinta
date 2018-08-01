package com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.PostContentActivity;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Datas.DataNewsFeed;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.ViewHolders.NewsFeedViewHolder;
import com.rakantao.pcg.lacostazamboanga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedAdminFragment extends Fragment implements View.OnClickListener {

    TextView tvGo;
    View view;

    private DatabaseReference mDatabase;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView mRecyclerView;

    public NewsFeedAdminFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news_feed_admin, container, false);

        tvGo = view.findViewById(R.id.TVGoCreateContent);

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current_user.getUid();

        mRecyclerView = view.findViewById(R.id.recycler_viewAdminFeeds);
        linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("NewsFeedPosts");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    long post = dataSnapshot.getChildrenCount();

                    Log.d("post", "onDataChange: " + post);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tvGo.setOnClickListener(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.TVGoCreateContent:
                startActivity(new Intent(getActivity(), PostContentActivity.class));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter <DataNewsFeed, NewsFeedViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DataNewsFeed, NewsFeedViewHolder>(

                DataNewsFeed.class,
                R.layout.list_newsfeed,
                NewsFeedViewHolder.class,
                mDatabase.orderByChild("counter")
        ) {
            @Override
            protected void populateViewHolder(NewsFeedViewHolder viewHolder, DataNewsFeed model, int position) {

//                viewHolder.tvNewsFeedTitle.setText(model.getArticle_title());
//                viewHolder.tvNewsFeedContent.setText(model.getArticle_content());
//                viewHolder.tvNewsFeedDate.setText(model.getDate_posted());

//                Log.d("news feed", model.getArticle_title());

            }
        };
        //mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
