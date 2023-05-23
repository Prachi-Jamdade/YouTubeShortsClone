package com.example.ytshortsclone;

public class Submission {
    private String mediaUrl;
    private String thumbnail;
    private String hyperlink;
    private String placeholderUrl;

    public Submission(String mediaUrl, String thumbnail, String hyperlink, String placeholderUrl) {
        this.mediaUrl = mediaUrl;
        this.thumbnail = thumbnail;
        this.hyperlink = hyperlink;
        this.placeholderUrl = placeholderUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public String getPlaceholderUrl() {
        return placeholderUrl;
    }

    public void setPlaceholderUrl(String placeholderUrl) {
        this.placeholderUrl = placeholderUrl;
    }
}
