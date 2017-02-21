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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by gala on 2016/7/3.
 */
public class ProfileDetailAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private TextView keyTextView;
    private TextView valueTextView;
    private Context context;
    private String[] keys= {"姓名:","性别:","身份证号:","旅客类型:","电话号码:","邮箱:"};
    private String[] datas;
    /*private Map.Entry<String,String> entry;
    private Iterator<Map.Entry<String,String>> iterator;*/

    public ProfileDetailAdapter(Context context, String[] datas) {
        mInflater = LayoutInflater.from(context);
        this.context =context;
        this.datas = datas;
//        iterator = datas.entrySet().iterator();
    }

    @Override
    public int getCount() {
        return 6;
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
            convertView = mInflater.inflate(R.layout.profile_detail_lv_item,null);
        }
        /*for (int i = 0; i <=position; i++) {
            entry = iterator.next();
        }*/
        keyTextView = (TextView) convertView.findViewById(R.id.profile_item_key);
        valueTextView = (TextView) convertView.findViewById(R.id.profile_item_value);

        keyTextView.setText(keys[position]);
        if (datas.length!=0) {
            valueTextView.setText(datas[position]);
        }


        return convertView;
    }
}
