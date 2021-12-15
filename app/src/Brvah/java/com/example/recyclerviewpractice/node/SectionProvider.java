package com.example.recyclerviewpractice.node;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewpractice.R;

/**
 * Created by luyiling on 2021/12/14
 * Modified by
 * <p>
 * TODO:
 * Description: 1st nodes's adapter
 *
 * @params
 * @params
 */
class SectionProvider extends BaseNodeProvider {
    private final String TAG = SectionProvider.class.getSimpleName();

    public SectionProvider() {
        addChildClickViewIds(R.id.tVheader);
    }

    @Override
    public int getItemViewType() {
        return NodeData.HEADER;
    }

    @Override
    public int getLayoutId() {
        return R.layout.header;
    }

    @Override
    public void convert(BaseViewHolder helper, BaseNode baseNode) {
        Log.e(TAG, "this is the header");
        //要用數據強轉
        NodeData entity = (NodeData) baseNode;
        helper.setText(R.id.tVheader, entity.getTitle());
    }

    @Override
    public void onChildClick(BaseViewHolder helper, View view, BaseNode data, int position) {
        Log.e(TAG, "onChildClick: "+view);
        if (view.getId() == R.id.tVheader){
            Toast.makeText(
                    context,
                    "Node Header:"+position,
                    Toast.LENGTH_SHORT
            ).show();
        }

    }
}
