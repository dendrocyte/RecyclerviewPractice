package com.example.recyclerviewpractice.card;


import androidx.annotation.ColorInt;

/**
 * Created by luyiling on 2019/4/16
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class CardData {
    private String id;
    private String title;
    private String url;
    private @ColorInt
    int backgroundColor;

    public CardData(String id, String title, String url, int backgroundColor) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.backgroundColor = backgroundColor;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
