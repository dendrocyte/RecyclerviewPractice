package com.example.recyclerviewpractice.group.multidata;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by luyiling on 2019/4/17
 * <p>
 *
 * <IMPORTANT>this is for multi data handler</IMPORTANT>
 */
public class GroupMultiData implements MultiItemEntity {
    public static final int HEADER = 1;
    public static final int ITEM_TXT = 2;
    public static final int ITEM_IMG = 3;
    public static final int FOOTER = 3;

    private String id;
    private String fig_url;
    private String dest_url;
    private int type;

    public GroupMultiData(String id, String fig_url, String dest_url) {
        this(id, fig_url, dest_url, ITEM_TXT);
    }

    public GroupMultiData(String id, String fig_url, String dest_url, int type) {
        this.id = id;
        this.fig_url = fig_url;
        this.dest_url = dest_url;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getFig_url() {
        return fig_url;
    }

    public String getDest_url() {
        return dest_url;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
