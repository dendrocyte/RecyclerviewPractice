package com.example.recyclerviewpractice.node;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.node.BaseNode;

/**
 * Created by luyiling on 2021/12/13
 * Modified by
 * <p>
 * TODO:
 * Description:
 *
 * @params
 * @params
 */
abstract class MultiBaseNode extends BaseNode implements MultiItemEntity {
    public static final int HEADER = 0;
    public static final int ITEM_TXT = 1;
    public static final int FOOTER = 2;
}
