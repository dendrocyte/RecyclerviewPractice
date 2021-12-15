package com.example.recyclerviewpractice.expand;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.entity.node.NodeFooterImp;

import java.util.List;

/**
 * Created by luyiling on 2021/12/13
 * Modified by
 * <p>
 * TODO:
 * Description: this is Footer nodes
 *
 * @params NodeFooterImp 是讓要做footer 才實作
 * @params
 */
public class FootExpandData extends MultiExpandNode implements NodeFooterImp {

    private String title;

    public FootExpandData(String name) {
        title = name;
    }


    public String getTitle() {
        return title;
    }

    @Override
    public int getItemType() {
        return FOOTER;
    }

    @Override
    public List<BaseNode> getChildNode() { return null; }

    /**
     * 每一个 node 都可以有自己的脚部
     * {@link NodeFooterImp}
     * （可选实现）
     * 重写此方法，获取脚部节点
     */
    @Nullable
    @Override
    public BaseNode getFooterNode() { return null; }
}
