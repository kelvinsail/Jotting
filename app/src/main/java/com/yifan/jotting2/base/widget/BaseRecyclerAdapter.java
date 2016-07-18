package com.yifan.jotting2.base.widget;

import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * RecyclerAdapter基类
 * <p/>
 * Created by yifan on 2016/7/18.
 */
public abstract class BaseRecyclerAdapter<VH extends BaseRecyclerAdapter.BaseViewHolder>
        extends RecyclerView.Adapter<VH> implements View.OnClickListener, View.OnLongClickListener {

    /**
     * 列表项点击事件
     */
    OnItemClickListener mOnItemClickListener;
    /**
     * 列表项点击事件
     */
    OnItemLongClickListener mOnItemLongClickListener;

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (null != holder) {
            holder.setPosition(position);
        }
    }

    @Override
    public abstract int getItemCount();

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * RecyclerView.ViewHolder基类
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            if (null != itemView) {
                itemView.setOnClickListener(BaseRecyclerAdapter.this);
                itemView.setOnLongClickListener(BaseRecyclerAdapter.this);
            }
        }

        public void setPosition(int position) {
            if (null != itemView) {
                itemView.setId(position);
            }
        }

        public void setData(Object data) {
            if (null != itemView) {
                itemView.setTag(data);
            }
        }

    }

    @Override
    public boolean onLongClick(View view) {
        if (null != mOnItemLongClickListener) {
            return mOnItemLongClickListener.onLongClick(view, view.getId(), view.getTag());
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (null != mOnItemClickListener) {
            mOnItemClickListener.onClick(view, view.getId(), view.getTag());
        }
    }

    /**
     * 列表点击事件监听接口
     */
    public interface OnItemClickListener {
        void onClick(View v, int position, Object data);
    }

    /**
     * 列表点击事件监听接口
     */
    public interface OnItemLongClickListener {
        boolean onLongClick(View v, int position, Object data);
    }
}
