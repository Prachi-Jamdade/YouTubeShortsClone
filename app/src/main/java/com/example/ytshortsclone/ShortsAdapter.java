package com.example.ytshortsclone;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytshortsclone.databinding.ReelDesignBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShortsAdapter extends RecyclerView.Adapter<ShortsAdapter.VideoHolder> {
    private Context context;
    private ArrayList<VideoData> shorts;
    private boolean isPlaying = false;
    public ShortsAdapter(Context context, ArrayList<VideoData> shorts) {
        this.context = context;
        this.shorts = shorts;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reel_design, null);

        view.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
//        holder.mBinding.videoView.setVideoPath(reels.get(position).getVideoUrl());
        holder.mBinding.videoView.setVideoURI(Uri.parse(shorts.get(position).getSubmission().getMediaUrl()));

//        holder.mBinding.nameTV.setText(reels.get(position).getName());

        holder.mBinding.nameTV.setText(shorts.get(position).getCreator().getHandle());

//        holder.mBinding.profileImg.setImageResource(reels.get(position).getProfile());

        Picasso.get().load(shorts.get(position).getSubmission().getThumbnail()).into(holder.mBinding.profileImg);

        holder.mBinding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                isPlaying = true;
            }
        });

        holder.mBinding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
                isPlaying = true;
            }
        });

        holder.mBinding.videoView.setOnClickListener(v -> {

            if (isPlaying) {
                holder.mBinding.videoView.pause();
                isPlaying = false;
            } else {
                holder.mBinding.videoView.resume();
                isPlaying = true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return shorts.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        ReelDesignBinding mBinding;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = ReelDesignBinding.bind(itemView);
        }
    }
}
