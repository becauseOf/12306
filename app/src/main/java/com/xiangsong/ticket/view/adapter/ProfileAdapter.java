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
public class ProfileAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ProfileItem> datas;
    private ImageView imageView;
    private TextView textView;
    private Context context;

    public ProfileAdapter(Context context, List<ProfileItem> datas) {
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
            convertView = mInflater.inflate(R.layout.profile_lv_item,null);
        }

        imageView = (ImageView) convertView.findViewById(R.id.profile_item_iv);
        textView = (TextView) convertView.findViewById(R.id.profile_item_tv);

        Picasso.with(context).load(datas.get(position).getImgId()).into(imageView);
        textView.setText(datas.get(position).getText());


        return convertView;
    }
}
