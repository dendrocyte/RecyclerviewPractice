package com.example.recyclerviewpractice.loadmore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewpractice.R;

import org.jetbrains.annotations.NotNull;

/**
 * Created by luyiling on 2021/12/15
 * Modified by
 * <p>
 * TODO:
 * Description:
 *
 * @params
 * @params
 */
public class LoadMoreView extends BaseLoadMoreView {


    @NotNull
    @Override
    public View getLoadComplete(@NotNull BaseViewHolder baseViewHolder) {
        // 布局中 “当前一页加载完成”的View
        return baseViewHolder.getView(R.id.fLloadMore);
    }

    @NotNull
    @Override
    public View getLoadEndView(@NotNull BaseViewHolder baseViewHolder) {
        // 布局中 “全部加载结束，没有数据”的View
        return baseViewHolder.getView(R.id.fLnoMore);
    }

    @NotNull
    @Override
    public View getLoadFailView(@NotNull BaseViewHolder baseViewHolder) {
        // 布局中 “加载失败”的View
        return baseViewHolder.getView(R.id.fLloadFail);
    }

    @NotNull
    @Override
    public View getLoadingView(@NotNull BaseViewHolder baseViewHolder) {
        // 布局中 “加载中”的View
        return baseViewHolder.getView(R.id.lLloading);
    }

    @NotNull
    @Override
    public View getRootView(@NotNull ViewGroup viewGroup) {
        // 整个 LoadMore 布局
        return LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.views_load_more, viewGroup, false);
    }
}
