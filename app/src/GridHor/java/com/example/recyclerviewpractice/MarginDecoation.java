package com.example.recyclerviewpractice;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by luyiling on 2018/10/18
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class MarginDecoation extends RecyclerView.ItemDecoration {
    int margin;

    public MarginDecoation(@IntRange(from=0) int margin) {
        super();
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = margin;
        }
        outRect.left = margin;
        outRect.right = margin;
        outRect.bottom = margin;
    }
}
