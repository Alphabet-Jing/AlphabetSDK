package com.alphabet.alphabetsdklib.uitls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alphabet on 15/9/26.
 */
public class ViewHolder {

    private SparseArray<View> mViewSparseArray;
    private View mConvertView;
    private int mPosition;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mPosition = position;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mViewSparseArray = new SparseArray<>();

        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mPosition = position;
            return viewHolder;
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViewSparseArray.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId, CharSequence text) {
        TextView textView = (TextView) mViewSparseArray.get(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setText(int viewId, int resId) {
        TextView textView = (TextView) mViewSparseArray.get(viewId);
        textView.setText(resId);
        return this;
    }

    public ViewHolder setViewVisibility(int viewId, int visibility) {
        if (mViewSparseArray.get(viewId).getVisibility() == visibility) {
            mViewSparseArray.get(viewId).setVisibility(visibility);
        }
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView imageView = (ImageView) mViewSparseArray.get(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = (ImageView) mViewSparseArray.get(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView imageView = (ImageView) mViewSparseArray.get(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }
}