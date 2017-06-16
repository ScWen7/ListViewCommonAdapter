package com.xxh.listviewcommonadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 解晓辉  on 2016/11/26 15:24 *
 * QQ  ：811733738
 * 作用: 打造通用的Adapter
 */

public abstract class ListCommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    /**
     * 数据集合
     */
    private List<T> mDatas;

    private int mItemViewId;


    public ListCommonAdapter(Context context, List<T> datas, int itemViewId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mItemViewId = itemViewId;
    }


    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    public void addData(T data) {
        mDatas.add(data);
    }

    public void addData(int position, T data) {
        if (position < 0) {
            position = 0;
        } else if (position > mDatas.size()) {
            position = mDatas.size();
        }
        mDatas.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        if (index < 0 || index > mDatas.size() - 1) {
            return;
        }
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(mContext, convertView, parent, mItemViewId, position);
        convert(commonViewHolder, getItem(position), position);
        return commonViewHolder.getConvertView();
    }

    /**
     * 子类重写次方法进行数据的绑定
     *
     * @param commonViewHolder 获取到的ViewHolder
     * @param item             数据bean
     */
    protected abstract void convert(CommonViewHolder commonViewHolder, T item, int position);

}
