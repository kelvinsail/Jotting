package com.yifan.utils.base.widget;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yifan.utils.utils.Constant;

/**
 * BaseRecyclerHolder RecyclerView.ViewHolder封装
 *
 * Created by yifan on 2016/8/25.
 */
public abstract class BaseRecyclerHolder extends RecyclerView.ViewHolder {

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
    }

    /**
     * 设置序号等数据
     *
     * @param position
     */
    public void setPosition(int position) {
        Bundle data;
        if (null != itemView
                && null != itemView.getTag()
                && itemView.getTag() instanceof Bundle) {
            data = (Bundle) itemView.getTag();
        } else {
            data = new Bundle();
        }
        data.putInt(Constant.POSITION, position);
        itemView.setTag(data);
    }


    /**
     * 从View的tag中取出序号
     *
     * @param view
     * @return
     */
    public static int getPositionFroView(View view) {
        int posotion = -1;
        //取出序号
        if (null != view && null != view.getTag()
                && view.getTag() instanceof Bundle) {
            posotion = ((Bundle) view.getTag()).getInt(Constant.POSITION, -1);
        }
        return posotion;
    }
}
