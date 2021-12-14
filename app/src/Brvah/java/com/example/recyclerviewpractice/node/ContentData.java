package com.example.recyclerviewpractice.node;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.entity.node.NodeFooterImp;

import java.util.List;

/**
 * Created by luyiling on 2021/12/13
 * Modified by
 * <p>
 * TODO:
 * Description: this is 2nd nodes
 *
 * @params NodeFooterImp 是讓要做footer 才實作
 * @params
 */
public class ContentData extends MultiBaseNode implements NodeFooterImp {

    private String title;
    private BaseNode foot;

    /**
     *
     * @param name
     * @param footer
     */
    public ContentData(String name, BaseNode footer) {
        title = name;
        foot = footer;
    }


    public String getTitle() { return title; }

    @Override
    public int getItemType() { return ITEM_TXT; }

    @Override
    public List<BaseNode> getChildNode() {
        return null; //PATCH 如果有3rd nodes 就須塡值，否則為null 和空值
    }

    /**
     * 每一个 node 都可以有自己的脚部
     * {@link NodeFooterImp}
     * （可选实现）
     * 重写此方法，获取脚部节点
     */
    @Nullable
    @Override
    public BaseNode getFooterNode() {
        return foot;
    }
}
