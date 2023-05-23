package com.example.ytshortsclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;

import com.example.ytshortsclone.databinding.ActivityListThumbnailBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListThumbnailActivity extends AppCompatActivity {

    private ActivityListThumbnailBinding mBinding;

    private ThumbnailAdapter thumnailAdapter;
    private ArrayList<VideoData> postsList;
    private String BASE_URL = "https://internship-service.onrender.com/videos/";
    private int pageCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBinding = ActivityListThumbnailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        postsList = new ArrayList<>();

        thumnailAdapter = new ThumbnailAdapter(ListThumbnailActivity.this, postsList);
        mBinding.thumbnailRV.setAdapter(thumnailAdapter);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        for(int i=0; i<9; i++) {
            String url = BASE_URL + "?page=" + i;
            makeAPICall(retrofitAPI, url);
        }

    }

    private void makeAPICall(RetrofitAPI retrofitAPI, String url) {
        Call<Result> call = retrofitAPI.getData(url);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {
                pageCount++;
                Result result = response.body();
                Data data = result.getData();
                ArrayList<VideoData> posts = data.getPosts();

                for(int i=0; i<posts.size(); i++) {
                    postsList.add(new VideoData(posts.get(i).getPostId(),
                            posts.get(i).getCreator(),
                            posts.get(i).getSubmission()));
                }

                thumnailAdapter.notifyDataSetChanged();

                for(int i=0; i<postsList.size(); i++) {
                    Log.d("List", postsList.get(i).getPostId() + " " +
                            postsList.get(i).getCreator().getId() + " " +
                            postsList.get(i).getCreator().getHandle() + " " +
                            postsList.get(i).getCreator().getName() + " " +
                            postsList.get(i).getCreator().getPic() + " " +
                            postsList.get(i).getSubmission().getHyperlink() + " " +
                            postsList.get(i).getSubmission().getMediaUrl() + " " +
                            postsList.get(i).getSubmission().getPlaceholderUrl() + " " +
                            postsList.get(i).getSubmission().getThumbnail());

                }

                Log.e("RESULT", posts.get(5).getCreator().getHandle(), null);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}