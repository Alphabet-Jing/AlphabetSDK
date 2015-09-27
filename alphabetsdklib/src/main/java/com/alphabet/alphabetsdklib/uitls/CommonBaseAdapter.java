package com.alphabet.alphabetsdklib.uitls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by alphabet on 15/9/26.
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    protected int mLayoutId;
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mLayoutInflater;

    public CommonBaseAdapter(Context context, List<T> datas ,int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
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
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = ViewHolder.get(mContext,convertView,parent,mLayoutId,position);
        convert(viewHolder,getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder viewHolder,T t);
}
