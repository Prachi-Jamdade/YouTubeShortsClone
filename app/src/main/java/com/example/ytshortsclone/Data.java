package com.example.ytshortsclone;

import java.util.ArrayList;

public class Data {
    ArrayList<VideoData> posts;
    String page;
    String offset;

    public Data(ArrayList<VideoData> posts, String page, String offset) {
        this.posts = posts;
        this.page = page;
        this.offset = offset;
    }

    public ArrayList<VideoData> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<VideoData> posts) {
        this.posts = posts;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
