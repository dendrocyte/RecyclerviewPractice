package com.example.recyclerviewpractice.group.multidata;

import android.util.Log;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewpractice.R;

import java.util.List;

/**
 * Created by luyiling on 2019/4/17
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class GroupAdapter extends BaseMultiItemQuickAdapter<GroupMultiData, BaseViewHolder> { //different parent class
    private String TAG = GroupAdapter.class.getSimpleName();
    public GroupAdapter(List<GroupMultiData> data) {
        super(data);
        addItemType(GroupMultiData.HEADER, R.layout.header);
        addItemType(GroupMultiData.ITEM_TXT, R.layout.item_ad);
    }

    @Override
    protected void convert(final BaseViewHolder helper, GroupMultiData item) {
        switch (item.getItemType()){
            case GroupMultiData.HEADER:
                Log.e(TAG, "this is the header");
                helper.getView(R.id.tVheader).getLayoutParams().height = ViewGroup.MarginLayoutParams.MATCH_PARENT;
                helper.setText(R.id.tVheader, "Header! oh yeah");
                break;
            case GroupMultiData.ITEM_TXT:
                helper.setText(R.id.tVitem, item.getId());
                helper.getView(R.id.rLframe).setOnClickListener( v -> {
                        int data_index=helper.getLayoutPosition()-1;
                        Log.e(TAG,"不規則的frame click! data position:"+ data_index);
                });
                break;
        }


    }
}
