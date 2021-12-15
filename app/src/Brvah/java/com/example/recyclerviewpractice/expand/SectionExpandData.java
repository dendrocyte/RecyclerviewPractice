package com.example.recyclerviewpractice.expand;

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
public class SectionExpandData extends MultiExpandNode {
    private final String title;
    private List<BaseNode> _2ndNodes;


    public String getTitle() {
        return title;
    }

    public <E extends BaseNode> SectionExpandData(String title, List<E> data) {
        this.title = title;
        _2ndNodes = (List<BaseNode>) data;
        // 也可以在生成 Node 后设置。
        // 注意：必须在设置给 Adapter 之前修改。数据设置给Adapter后，不应该再修改
        setExpanded(false);
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
