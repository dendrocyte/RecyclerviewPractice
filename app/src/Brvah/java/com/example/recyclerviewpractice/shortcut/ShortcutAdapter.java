package com.example.recyclerviewpractice.shortcut;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewpractice.R;

import java.util.List;

/**
 * Created by luyiling on 2019/4/16
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class ShortcutAdapter extends BaseQuickAdapter<ShortcutData, BaseViewHolder>{
    private String TAG = ShortcutAdapter.class.getSimpleName();
    public ShortcutAdapter(@Nullable List<ShortcutData> data) {
        super(R.layout.item_shortcut, data);
    }
    @Override
    protected void convert(final BaseViewHolder helper, final ShortcutData item) {
        helper.setText(R.id.tVitem, item.getId())
//                .setImageResource(R.id.iVicon, R.drawable.sample_footer_loading_progress)
                .setBackgroundColor(R.id.rLframe, Color.GRAY);

        helper.getView(R.id.rLframe).setOnClickListener( v-> {
                Log.e(TAG,"click:"+item.getId());
                //get current position:
                Log.e(TAG, "position:"+helper.getLayoutPosition());
        });
    }
}


