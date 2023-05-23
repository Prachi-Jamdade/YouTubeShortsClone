package com.example.ytshortsclone;

public class Creator {
    private String name;
    private String id;
    private String handle;
    private String pic;

    public Creator(String name, String id, String handle, String pic) {
        this.name = name;
        this.id = id;
        this.handle = handle;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
