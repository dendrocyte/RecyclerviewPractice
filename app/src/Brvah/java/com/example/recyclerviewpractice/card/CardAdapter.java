package com.example.recyclerviewpractice.card;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewpractice.R;

import java.util.List;

/**
 * Created by luyiling on 2019/4/16
 * <p>
 *
 *
 * <IMPORTANT>可以自定義 Holder</IMPORTANT>
 */
public class CardAdapter extends BaseQuickAdapter<CardData, BaseViewHolder> {
    private String TAG = CardAdapter.class.getSimpleName();
    public CardAdapter(@Nullable List<CardData> data) {
        super(R.layout.item_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CardData item) {
        //use helper's setText()
        helper.setText(R.id.tVitem, item.getId())
                .setBackgroundColor(R.id.rLframe, Color.RED);
        CardView cardView = helper.getView(R.id.cVframe);
        cardView.setCardBackgroundColor(item.getBackgroundColor());
        cardView.setOnClickListener(v -> Log.e(TAG,"click:"+item.getId()));

    }


}
