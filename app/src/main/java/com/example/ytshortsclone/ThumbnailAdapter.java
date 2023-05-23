package com.example.ytshortsclone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytshortsclone.databinding.ThumbnailItemBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailAdapter.ThumbnailHolder> {

    private Context context;
    private ArrayList<VideoData> shorts;

    public ThumbnailAdapter(Context context, ArrayList<VideoData> shorts) {
        this.context = context;
        this.shorts = shorts;
    }

    @NonNull
    @Override
    public ThumbnailAdapter.ThumbnailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thumbnail_item, parent, false);
        return new ThumbnailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailAdapter.ThumbnailHolder holder, int position) {
        Picasso.get().load(shorts.get(position).getSubmission().getThumbnail()).into(holder.mBinding.thumnailIV);

        holder.mBinding.titleTV.setText(shorts.get(position).getCreator().getName());
        holder.mBinding.descTV.setText(shorts.get(position).getCreator().getHandle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("short", (new Gson().toJson(shorts.get(position))));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return shorts.size();
    }

    public class ThumbnailHolder extends RecyclerView.ViewHolder{

        ThumbnailItemBinding mBinding;

        public ThumbnailHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = ThumbnailItemBinding.bind(itemView);
        }
    }
}
