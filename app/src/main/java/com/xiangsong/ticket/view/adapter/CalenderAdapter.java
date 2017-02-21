package com.xiangsong.ticket.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.Order;

import java.util.List;

/**
 * Created by gala on 2016/7/3.
 */
public class CalenderAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Order> datas;
    private ImageView imageView;
    private TextView textView;
    private Context context;

    public CalenderAdapter(Context context, List<Order> datas) {
        mInflater = LayoutInflater.from(context);
        this.context =context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.calender_item,null);
        }



        return convertView;
    }
}
