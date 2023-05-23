package com.example.ytshortsclone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<Result> getData(@Url String url);
}
