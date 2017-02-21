package com.xiangsong.ticket.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.ProfileItem;

import java.util.List;

/**
 * Created by gala on 2016/7/3.
 */
public class CityNameAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> datas;
    private TextView textView;
    private Context context;

    public CityNameAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        this.context =context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
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
            convertView = mInflater.inflate(R.layout.city_name_item,null);
        }

        textView = (TextView) convertView.findViewById(R.id.city_name);

        textView.setText(datas.get(position));


        return convertView;
    }
}
