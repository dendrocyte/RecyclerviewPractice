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
class FootProvider extends BaseNodeProvider {
    final String TAG = FootProvider.class.getSimpleName();

    public FootProvider() {
        addChildClickViewIds(R.id.tVheader);
    }

    @Override
    public int getItemViewType() {
        return SectionData.FOOTER;
    }

    @Override
    public int getLayoutId() {
        return R.layout.header;
    }

    @Override
    public void convert(BaseViewHolder helper, BaseNode baseNode) {
        //數據要強轉
        FootData entity = (FootData) baseNode;
        helper.setText(R.id.tVheader, entity.getTitle());

    }

    @Override
    public void onChildClick(BaseViewHolder helper, View view, BaseNode data, int position) {
        if (view.getId() == R.id.tVheader){
            Toast.makeText(context, "foot node data:"+position, Toast.LENGTH_SHORT).show();
        }
    }
}
