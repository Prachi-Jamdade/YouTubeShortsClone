package com.example.ytshortsclone;

public class VideoData {
    private String postId;
    private Creator creator;
    private Submission submission;

    public VideoData(String postId, Creator creator, Submission submission) {
        this.postId = postId;
        this.creator = creator;
        this.submission = submission;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
