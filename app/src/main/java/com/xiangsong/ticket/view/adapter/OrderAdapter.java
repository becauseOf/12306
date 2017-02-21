package com.xiangsong.ticket.view.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.Order;
import com.xiangsong.ticket.model.OrderBean;
import com.xiangsong.ticket.model.ProfileItem;
import com.xiangsong.ticket.model.QuerySeat;
import com.xiangsong.ticket.model.Ticket;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.activity.MainActivity;
import com.xiangsong.ticket.view.activity.OrderDetailActivity;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gala on 2016/7/3.
 */
public class OrderAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List datas;
    private ImageView imageView;
    private TextView textView;
    private Context context;
    private Ticket ticket;
    private OrderBean orderBean;

    public OrderAdapter(Context context, List datas) {
        mInflater = LayoutInflater.from(context);
        this.context =context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return ((List) datas.get(0)).size();
//        return datas.size()/2;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.order_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ticket = (Ticket) ((List) datas.get(0)).get(position);
        orderBean = (OrderBean) ((List) datas.get(1)).get(position);

        viewHolder.trainId.setText(ticket.getTrainid());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        String strStart="";
        String dstrStrat=ticket.getDate();
        java.util.Date date;
        try {
            date=sdf.parse(dstrStrat);
            SimpleDateFormat sdf1=new SimpleDateFormat("MM-dd");
            strStart = sdf1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        viewHolder.date.setText(strStart);




        viewHolder.startStation.setText(ticket.getStartStation());
        viewHolder.endStation.setText(ticket.getEndStation());
        //目前没有获取票价这一接口
        viewHolder.price.setText("¥"+ticket.getPrice());
        viewHolder.userName.setText(ticket.getName());

        String state = orderBean.getOrderState();
        if (state.equals("已支付")) {
            viewHolder.cancelBtn.setVisibility(View.INVISIBLE);
            viewHolder.payBtn.setText("已支付");
            viewHolder.payBtn.setBackgroundResource(R.drawable.order_btn_cancel_bg);
            viewHolder.payBtn.setClickable(false);
        }else if (state.equals("已退票")) {
            viewHolder.cancelBtn.setVisibility(View.INVISIBLE);
            viewHolder.payBtn.setBackgroundResource(R.drawable.order_btn_cancel_bg);
            viewHolder.payBtn.setText("已退票");
            viewHolder.payBtn.setClickable(false);
        } else{
            viewHolder.cancelBtn.setVisibility(View.VISIBLE);
            viewHolder.cancelBtn.setBackgroundResource(R.drawable.order_btn_cancel_bg);
            viewHolder.payBtn.setText("支付");
            viewHolder.payBtn.setBackgroundResource(R.drawable.order_btn_pay_bg);
        }

        viewHolder.cancelBtn.setOnClickListener(new View.OnClickListener() {

            //进行取消订单的操作
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(context);
                dialog.setTitle("正在取消...");
                dialog.show();
                new AsyncTask<String,String,Void>(){

                    @Override
                    protected Void doInBackground(String... params) {
                        try {
                            HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/DeleteOrder");


                            //错误原因在于获取到的orderBean一直都是最后一个
                            orderBean = (OrderBean) ((List) datas.get(1)).get(position);
                            String parameter = "orderid="+orderBean.getOrderid();
                            HttpUtil.setBodyParameter(conn,parameter);
                            String content = HttpUtil.readInputStream(conn);


                            HttpURLConnection conn1 =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/CancelSeat");


                            String parameter1 = "from="+ticket.getStartStation()
                                    +"&to="+ticket.getEndStation()
                                    +"&trainid="+ticket.getTrainid()
                                    +"&num="+ticket.getTrainBox()
                                    +"&seatNumber="+ticket.getSeatNum()
                                    +"&day="+ticket.getDate();
//                            HttpUtil.setBodyParameter(conn,parameter1);
                            //操！！！！！！！！！！！！
                            HttpUtil.setBodyParameter(conn1,parameter1);
                            String content1 = HttpUtil.readInputStream(conn1);

                            System.out.println("gggg"+content1);


                        }catch (Exception e){
                            System.out.println("gggge"+e);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        dialog.dismiss();
                        Toast.makeText(context,"订单取消成功",Toast.LENGTH_SHORT).show();
                        ValueUtil.mainViewPager=1;
                        context.startActivity(new Intent(context, MainActivity.class));
                    }
                }.execute("");

            }
        });

        viewHolder.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(context);
                dialog.setTitle("正在支付...");
                dialog.show();
                new AsyncTask<String,String,Boolean>(){



                    @Override
                    protected Boolean doInBackground(String... params) {
                        boolean isSuccess =false;
                        try {
                            HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/OperateOrder");


                            //错误原因在于获取到的orderBean一直都是最后一个
                            orderBean = (OrderBean) ((List) datas.get(1)).get(position);
                            String parameter = "orderid="+orderBean.getOrderid()+"&operation=已支付";
                            HttpUtil.setBodyParameter(conn,parameter);
                            String content = HttpUtil.readInputStream(conn);


                            JSONObject j = new JSONObject(content);
                            isSuccess = j.getBoolean("content");
                        }catch (Exception e){

                        }
                        return isSuccess;
                    }
                    @Override
                    protected void onPostExecute(Boolean b) {
                        dialog.dismiss();
                        if (b) {
                            Toast.makeText(context, "支付成功",Toast.LENGTH_SHORT).show();
                            viewHolder.cancelBtn.setVisibility(View.INVISIBLE);
                            viewHolder.payBtn.setText("已支付");
                            viewHolder.payBtn.setClickable(false);
                            viewHolder.payBtn.setBackgroundResource(R.drawable.order_btn_cancel_bg);
                            ValueUtil.mainViewPager=1;
                            context.startActivity(new Intent(context, MainActivity.class));
                        }else{
                            Toast.makeText(context, "支付失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute("");
            }
        });



        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.order_item_tv_train_id) TextView trainId;
        @BindView(R.id.order_item_tv_start_date) TextView date;
        @BindView(R.id.order_item_tv_start_station) TextView startStation;
        @BindView(R.id.order_item_tv_end_station) TextView endStation;
        @BindView(R.id.order_item_tv_price) TextView price;
        @BindView(R.id.order_item_tv_name) TextView userName;
        @BindView(R.id.order_item_btn_cancel) Button cancelBtn;
        @BindView(R.id.order_item_btn_pay) Button payBtn;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }

    }
}
