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
import com.xiangsong.ticket.model.RemainTicket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gala on 2016/7/3.
 */
public class RemainTickteAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<RemainTicket> datas;
    private Context context;
    private String startStation,endStaion;
    private RemainTicket remainTicket;
    private List<HashMap<String,Integer>> remainNums;
    private Iterator iterator;
    private Map.Entry<String,Integer> entry;

    public RemainTickteAdapter(Context context, List<RemainTicket> datas,String startStation,String endStaion) {
        mInflater = LayoutInflater.from(context);
        this.context =context;
        this.datas = datas;
        this.startStation = startStation;
        this.endStaion = endStaion;
    }

    @Override
    public int getCount() {
        return 5;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.remain_ticket_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        remainTicket = datas.get(position);
        remainNums = remainTicket.getRemainNums();
        iterator = remainNums.get(0).entrySet().iterator();
        entry = (Map.Entry) iterator.next();

        viewHolder.seatType1.setText(entry.getKey());
        viewHolder.seatType1Value.setText(entry.getValue()+"张");

        iterator = remainNums.get(1).entrySet().iterator();
        entry = (Map.Entry) iterator.next();

        viewHolder.seatType2.setText(entry.getKey());
        viewHolder.seatType2Value.setText(entry.getValue()+"张");

        iterator = remainNums.get(2).entrySet().iterator();
        entry = (Map.Entry) iterator.next();

        viewHolder.seatType3.setText(entry.getKey());
        viewHolder.seatType3Value.setText(entry.getValue()+"张");

        viewHolder.startStaion.setText(startStation);
        viewHolder.endStation.setText(endStaion);
        viewHolder.trainId.setText(remainTicket.getTrainId());
        viewHolder.price.setText(remainTicket.getPrice());
        viewHolder.startTime.setText(remainTicket.getStartTime());
        viewHolder.span.setText(remainTicket.getSpanTime());
        viewHolder.endTime.setText(remainTicket.getEndTime());




        return convertView;
    }

    static class ViewHolder{
        //id加后缀的作用在此时就体现出来了,便于区分
        @BindView(R.id.remain_ticket_item_start_station) TextView startStaion;
        @BindView(R.id.remain_ticket_item_trainid) TextView trainId;
        @BindView(R.id.remain_ticket_item_end_station) TextView endStation;
        @BindView(R.id.remain_ticket_item_price) TextView price;
        @BindView(R.id.remain_ticket_item_starttime) TextView startTime;
        @BindView(R.id.remain_ticket_item_span) TextView span;
        @BindView(R.id.remain_ticket_item_endtime) TextView endTime;
        @BindView(R.id.remain_ticket_item_seattype1) TextView seatType1;
        @BindView(R.id.remain_ticket_item_seattype1_value) TextView seatType1Value;
        @BindView(R.id.remain_ticket_item_seattype2) TextView seatType2;
        @BindView(R.id.remain_ticket_item_seattype2_value) TextView seatType2Value;
        @BindView(R.id.remain_ticket_item_seattype3) TextView seatType3;
        @BindView(R.id.remain_ticket_item_seattype3_value) TextView seatType3Value;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }

    }
}
