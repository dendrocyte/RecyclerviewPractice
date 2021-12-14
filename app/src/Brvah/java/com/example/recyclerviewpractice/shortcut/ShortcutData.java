package com.example.recyclerviewpractice.shortcut;


import androidx.annotation.ColorInt;

/**
 * Created by luyiling on 2019/4/16
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class ShortcutData {
    private String id;
    private String title;
    private String dest_url;
    private String thumbnail;
    private @ColorInt
    int backgroundColor;

    public ShortcutData(String id, String title, String thumbnail, String dest_url, int backgroundColor) {
        this.id = id;
        this.title = title;
        this.dest_url = dest_url;
        this.thumbnail = thumbnail;
        this.backgroundColor = backgroundColor;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDest_url() {
        return dest_url;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
