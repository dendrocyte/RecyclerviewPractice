package com.example.recyclerviewpractice.expand;


import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.recyclerviewpractice.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Created by luyiling on 2019/4/17
 * <p> 2.9.30 Expandable 的功能不是那麼好用，
 * 他只能用expand all 在最開始的時候，要不然就不大能做事
 *
 * <IMPORTANT>0.3.x
 * 則能使每一個node 都有footer
 * 且也有expandable 的功能
 * 但他expand資料也無法在每個node只展開他的child nodes
 * NOTE 不能用！
 * </IMPORTANT>
 *
 * @ref https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/readme/6-BaseNodeAdapter.md
 */
public class ExpandNodeAdapter extends BaseNodeAdapter { //different parent class
    private String TAG = ExpandNodeAdapter.class.getSimpleName();

    public ExpandNodeAdapter() {
        super();

        // 需要占满一行的，使用此方法（例如section）
        addFullSpanNodeProvider(new SectionExpandProvider());
        // 普通的item provider: 隨著spanCount 做改變
        addNodeProvider(new ContentExpandProvider());
        // 脚布局的 provider:占满一行
        addFooterNodeProvider(new FootExpandProvider());

    }


    @Override
    protected int getItemType(List<? extends BaseNode> list, int position) {
        BaseNode node = list.get(position);
        if (node instanceof SectionExpandData) {
            return ((SectionExpandData) node).getItemType();
        } else if (node instanceof ContentExpandData) {
            return ((ContentExpandData) node).getItemType();
        } else if (node instanceof FootExpandData) {
            return ((FootExpandData) node).getItemType();
        }
        return -1;
    }

    static class SectionExpandProvider extends BaseNodeProvider{
        public SectionExpandProvider() {
            addChildClickViewIds(R.id.tVheader);
        }
        @Override
        public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
            SectionExpandData entity = (SectionExpandData) baseNode;
            helper.setText(R.id.tVheader, entity.getTitle());
        }
        @Override
        public int getLayoutId() { return R.layout.header; }
        @Override
        public int getItemViewType() { return MultiExpandNode.HEADER; }
        @Override
        public void onChildClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode node, int position) {
            /**
             * // 自动展开\折叠某一位置的Node
             * getAdapter().expandOrCollapse(position);
             *
             * // 展开某一位置的Node，并且其子节点的也展开
             * getAdapter().expandAndChild(position);
             *
             * // 折叠某一位置的Node，并且其子节点的也折叠
             * getAdapter().collapseAndChild(position);
             */
            if (((MultiExpandNode) node).isExpanded()) {
                Toast.makeText(
                        context,
                        "Node Header: collapse" + position,
                        Toast.LENGTH_SHORT
                ).show();
                // 折叠某一个位置的Node
                Objects.requireNonNull(getAdapter()).collapse(position);
            } else {
                Toast.makeText(
                        context,
                        "Node Header: expand" + position,
                        Toast.LENGTH_SHORT
                ).show();
                // 展开某一位置的Node
                Objects.requireNonNull(getAdapter()).expand(position);
            }
        }
    }

    static class ContentExpandProvider extends BaseNodeProvider {
        public ContentExpandProvider() {
            addChildClickViewIds(R.id.tVitem);
        }
        @Override
        public int getItemViewType() {
            return MultiExpandNode.ITEM_TXT;
        }
        @Override
        public int getLayoutId() {
            return R.layout.item;
        }
        @Override
        public void convert(BaseViewHolder helper, BaseNode baseNode) {
            //數據要強轉
            ContentExpandData entity = (ContentExpandData) baseNode;
            helper.setText(R.id.tVitem, entity.getTitle());
        }
        @Override
        public void onChildClick(BaseViewHolder helper, View view, BaseNode node, int position) {
            if (view.getId() == R.id.tVitem && node instanceof MultiExpandNode) {
                if (((MultiExpandNode)node).isExpanded()) {
                    // 折叠某一个位置的Node
                    Objects.requireNonNull(getAdapter()).collapse(position);
                } else {
                    // 展开某一位置的Node
                    Objects.requireNonNull(getAdapter()).expand(position);
                }
            }

        }
    }

    static class FootExpandProvider extends BaseNodeProvider {
        public FootExpandProvider() {
            addChildClickViewIds(R.id.tVheader);
        }
        @Override
        public int getItemViewType() {
            return MultiExpandNode.FOOTER;
        }
        @Override
        public int getLayoutId() {
            return R.layout.header;
        }
        @Override
        public void convert(BaseViewHolder helper, BaseNode baseNode) {
            //數據要強轉
            FootExpandData entity = (FootExpandData) baseNode;
            helper.setText(R.id.tVheader, entity.getTitle());
        }
        @Override
        public void onChildClick(BaseViewHolder helper, View view, BaseNode node, int position) {
            if (view.getId() == R.id.tVheader){
                if (((MultiExpandNode)node).isExpanded()) {
                    // 折叠某一个位置的Node
                    Objects.requireNonNull(getAdapter()).collapse(position);
                } else {
                    // 展开某一位置的Node
                    Objects.requireNonNull(getAdapter()).expand(position);
                }
            }
        }
    }


}
