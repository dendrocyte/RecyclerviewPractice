package com.example.recyclerviewpractice.node;

import com.chad.library.adapter.base.entity.node.BaseNode;

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
public class SectionData extends MultiBaseNode {
    private final String title;
    private List<BaseNode> _2ndNodes;


    public String getTitle() {
        return title;
    }

    public <E extends BaseNode> SectionData(String title, List<E> data) {
        this.title = title;
        _2ndNodes = (List<BaseNode>) data;
    }

    @Override
    public int getItemType() {
        return HEADER;
    }

    @Override
    public List<BaseNode> getChildNode() {
        return _2ndNodes;
    }
}
