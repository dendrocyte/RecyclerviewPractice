package com.example.recyclerviewpractice.loadmore;

import android.widget.Toast;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewpractice.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by luyiling on 2019/4/16
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class LoadMoreAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements LoadMoreModule {

    public LoadMoreAdapter(@Nullable List<String> data) {
        super(R.layout.item, data);
    }
    public LoadMoreAdapter() {
        super(R.layout.item);
    }
        @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        helper.setText(R.id.tVitem, item);

        helper.getView(R.id.card).setOnClickListener( v-> {
            Toast.makeText(
                    getContext(),
                    "click:"+item+" "+helper.getLayoutPosition(),
                    Toast.LENGTH_SHORT
            ).show();
        });
    }


    @NotNull
    @Override
    public BaseLoadMoreModule addLoadMoreModule(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter) {
        return new BaseLoadMoreModule(baseQuickAdapter);
    }
}


