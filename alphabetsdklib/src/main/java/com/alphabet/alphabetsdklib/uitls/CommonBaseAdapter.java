package com.alphabet.alphabetsdklib.uitls;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by alphabet on 15/9/26.
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mLayoutInflater;
    protected SparseArray<Integer> mLayoutIdSparseArray;

    /**
     *
     * @param context
     * @param datas
     * @param layoutIds
     */
    public CommonBaseAdapter(Context context, List<T> datas ,int[] layoutIds) {
        mContext = context;
        mDatas = datas;
        mLayoutIdSparseArray = new SparseArray<Integer>();
        for (int i = 0; i < layoutIds.length; i++) {
            mLayoutIdSparseArray.put(i,layoutIds[0]);
        }
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
        ViewHolder viewHolder = ViewHolder.get(mContext,
                convertView,
                parent,
                mLayoutIdSparseArray.get(getItemViewType(position)),
                position);
        convert(viewHolder,getItemViewType(position),getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder viewHolder,int type,T t);
}
