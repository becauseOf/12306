package com.xiangsong.ticket.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.RemainTicket;
import com.xiangsong.ticket.model.TicketBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gala on 2016/7/3.
 */
public class RemainTickteNewAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<TicketBean> datas;
    private Context context;
    private TicketBean ticketBean;

    public RemainTickteNewAdapter(Context context, List<TicketBean> datas) {
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.remain_ticket_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        ticketBean = datas.get(position);
        viewHolder.seatType1.setText("硬座");
        viewHolder.seatType1Value.setText(ticketBean.getYingzuo()+"张");

        viewHolder.seatType2.setText("硬卧");
        viewHolder.seatType2Value.setText(ticketBean.getYingwo()+"张");


        viewHolder.seatType3.setText("软卧");
        viewHolder.seatType3Value.setText(ticketBean.getRuanwo()+"张");

        viewHolder.startStaion.setText(ticketBean.getStartStation());
        viewHolder.endStation.setText(ticketBean.getEndStation());
        viewHolder.trainId.setText(ticketBean.getTrainid());
        viewHolder.price.setText("¥"+ticketBean.getYingzuoPrice()+"起");//"+ticketBean.getYingzuoPrice()+"

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");//小写的mm表示的是分钟
        String strStart="",strEnd="",strSpan="";
        String dstrStrat=ticketBean.getStartTime();
        String dstrEnd=ticketBean.getEndTime();
        String dstrSpan=ticketBean.getTime();
        java.util.Date date;
        try {
            date=sdf.parse(dstrStrat);
            strStart = sdf.format(date);
            date=sdf.parse(dstrEnd);
            strEnd = sdf.format(date);

            sdf=new SimpleDateFormat("HH:mm");
            date=sdf.parse(dstrSpan);
            strSpan = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewHolder.startTime.setText(strStart);
        viewHolder.span.setText("历时"+strSpan+"分");
        viewHolder.endTime.setText(strEnd);




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
