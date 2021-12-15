package com.example.recyclerviewpractice.node;

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
class ContentProvider extends BaseNodeProvider {
    final String TAG = ContentProvider.class.getSimpleName();

    public ContentProvider() {
        addChildClickViewIds(R.id.tVitem);
    }

    @Override
    public int getItemViewType() {
        return NodeData.ITEM_TXT;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item;
    }

    @Override
    public void convert(BaseViewHolder helper, BaseNode baseNode) {
        //數據要強轉
        NodeData entity = (NodeData) baseNode;
        helper.setText(R.id.tVitem, entity.getTitle());
    }

    @Override
    public void onChildClick(BaseViewHolder helper, View view, BaseNode data, int position) {
        if (view.getId() == R.id.tVitem) {
            Toast.makeText(
                    context,
                    "content node data:" + position,
                    Toast.LENGTH_SHORT
            ).show();
        }

    }
}
