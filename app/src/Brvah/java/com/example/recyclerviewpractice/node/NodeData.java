package com.example.recyclerviewpractice.node;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.entity.node.NodeFooterImp;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by luyiling on 2021/12/13
 * <p>
 * 1st node
 *  - 2nd node
 *    -3rd node
 *    -3rd node
 *    -3rd node
 *  - 2nd node
 *    -3rd node
 *    -3rd node
 *    -3rd node
 * 1st node
 *  - 2nd node
 *    -3rd node
 *    -3rd node
 *    -3rd node
 *  - 2nd node
 *    -3rd node
 *    -3rd node
 *    -3rd node
 *
 * <IMPORTANT>以樹狀結構</IMPORTANT>
 */
public class NodeData extends MultiBaseNode implements NodeFooterImp {
    private final String title;
    @Nullable
    private List<BaseNode> childNodes;
    private int itemType;
    private BaseNode foot;



    /**
     * Header, Content
     * @param title
     * @param data
     * @param type
     * @param footer
     * @param <E>
     */
    public <E extends BaseNode> NodeData(String title, @Nullable List<E> data, int type, BaseNode footer) {
        this.title = title;
        childNodes =  data == null ? null :(List<BaseNode>) data ;
        itemType = type;
        foot = footer;
    }

    /**
     * Header, Content, Footer
     * @param name
     * @param footer
     */
    public NodeData(String name, BaseNode footer, int type) {
        title = name;
        foot = footer;
        itemType = type;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    //PATCH 如果有child nodes 就須塡值，否則為null 和空值
    @Override
    public List<BaseNode> getChildNode() {
        return childNodes;
    }

    @Nullable
    @Override
    public BaseNode getFooterNode() {
        return foot;
    }
}
