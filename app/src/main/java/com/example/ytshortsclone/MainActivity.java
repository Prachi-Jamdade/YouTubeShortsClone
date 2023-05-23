package com.example.ytshortsclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ytshortsclone.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private ShortsAdapter shortsAdapter;
    private ArrayList<VideoData> postsList;
    private String BASE_URL = "https://internship-service.onrender.com/videos/";
    private int pageCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        this.getTheme().applyStyle(R.style.FullScreen, false);

        String data = getIntent().getStringExtra("short");
        VideoData videoData = new Gson().fromJson(data, VideoData.class);
        Log.e("E", videoData.getPostId());
        postsList = new ArrayList<>();
        postsList.add(videoData);

        shortsAdapter = new ShortsAdapter(MainActivity.this, postsList);
        mBinding.videoVP.setAdapter(shortsAdapter);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        String url = BASE_URL + "?page=" + pageCount;
        makeAPICall(retrofitAPI, url);

        mBinding.videoVP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    if(mBinding.videoVP.getCurrentItem() == postsList.size() - 1) {
                        if(pageCount < 10) {
                            String url = BASE_URL + "?page=" + pageCount;
                            makeAPICall(retrofitAPI, url);
                        }
                    }
                }
            }
        });
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

                shortsAdapter.notifyDataSetChanged();

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