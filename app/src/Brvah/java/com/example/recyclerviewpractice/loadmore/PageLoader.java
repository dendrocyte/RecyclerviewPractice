package com.example.recyclerviewpractice.loadmore;

import android.content.Context;
import android.util.Log;

import androidx.annotation.ArrayRes;

import com.example.recyclerviewpractice.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luyiling on 2021/12/15
 * Modified by
 * <p>
 * TODO:
 * Description: PageLoader 應該在repo 端
 *
 * @params
 * @params
 */
public class PageLoader {
    /**
     * record load which page
     * @param pageId
     * R.array.more1
     * R.array.more2
     * R.array.more3
     * R.array.more4
     */
    int pageId = -1;

    List<Integer> pages = Arrays.asList(
            R.array.more1,
            R.array.more2,
            R.array.more3,
            R.array.more4,
            R.array.more5
    );

    public int getPageId() {
        return pageId;
    }

    public int getPageSize() {
        return pages.size();
    }

    public boolean isFirstPage() {
        return pageId == 0;
    }

    public ArrayList<String> loadData(Context context){
        pageId++;
        //load fail
        if (pageId <0){
            throw new IndexOutOfBoundsException("Practice: Page id hit IOOB");
        }
        if (pageId >= pages.size()){
            return new ArrayList<>();
        }
        //load more
        return new ArrayList<>(Arrays.asList(
                context.getResources().getStringArray(pages.get(pageId))
        ));
    }
}
