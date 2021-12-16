package com.example.recyclerviewpractice.expand;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.node.BaseExpandNode;

/**
 * Created by luyiling on 2021/12/15
 * Modified by
 * <p>
 * TODO:
 * Description:
 *
 * @params
 * @params
 */
abstract class MultiExpandNode extends BaseExpandNode implements MultiItemEntity {
    public static final int HEADER = 0;
    public static final int ITEM_TXT = 1;
    public static final int FOOTER = 2;

}
