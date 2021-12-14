package com.example.recyclerviewpractice.node;


import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;

import java.util.List;

/**
 * Created by luyiling on 2019/4/17
 * <p> 2.9.30 Expandable 的功能不是那麼好用，
 * 他只能用expand all 在最開始的時候，要不然就不大能做事
 *
 * <IMPORTANT>0.3.x
 * 則能使每一個node 都有footer
 * 且也有expandable 的功能
 *
 *
 * </IMPORTANT>
 *
 * @ref https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/readme/6-BaseNodeAdapter.md
 */
public class NodeAdapter extends BaseNodeAdapter { //different parent class
    private String TAG = NodeAdapter.class.getSimpleName();

    public NodeAdapter() {
        super();

        // 需要占满一行的，使用此方法（例如section）
        addFullSpanNodeProvider(new SectionProvider());
        // 普通的item provider: 隨著spanCount 做改變
        addNodeProvider(new ContentProvider());
        // 脚布局的 provider:占满一行
        addFooterNodeProvider(new FootProvider());

    }


    @Override
    protected int getItemType(List<? extends BaseNode> list, int position) {
        BaseNode node = list.get(position);
        if (node instanceof SectionData) {
            return ((SectionData) node).getItemType();
        } else if (node instanceof ContentData) {
            return ((ContentData) node).getItemType();
        } else if (node instanceof FootData) {
            return ((FootData) node).getItemType();
        }
        return -1;
    }
}
