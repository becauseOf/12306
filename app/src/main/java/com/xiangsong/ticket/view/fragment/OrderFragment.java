package com.xiangsong.ticket.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.Order;
import com.xiangsong.ticket.model.OrderBean;
import com.xiangsong.ticket.model.RemainTicket;
import com.xiangsong.ticket.model.Seat;
import com.xiangsong.ticket.model.Ticket;
import com.xiangsong.ticket.model.TicketBean;
import com.xiangsong.ticket.model.UserLogin;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.activity.OrderDetailActivity;
import com.xiangsong.ticket.view.activity.RemainTicketActivity;
import com.xiangsong.ticket.view.adapter.OrderAdapter;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiangsong on 2016/9/11.
 */
public class OrderFragment extends Fragment {

    @BindView(R.id.order_lv)
    ListView orderList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {

        ButterKnife.bind(this, getActivity());
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("正在查询...");
        dialog.show();

        new AsyncTask<String,String,String>(){

            //查询订单
            @Override
            protected String doInBackground(String... params) {
                String result="";
                try {
                    //断点的位置也是很重要的，应该设置在可能抛出异常的位置，尽量不要使用输出语句，否则可能程序会提前抛出异常，造成程序提前终止
                    HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/QueryOrder");
                    String parameter = "userid="+ params[0]+"&idcard="+params[1];
                    HttpUtil.setBodyParameter(conn,parameter);
                    String content = HttpUtil.readInputStream(conn);


                    System.out.println(parameter+"order查询");

                    /*System.out.println("content:"+content);
                    JSONObject j = new JSONObject(content);
                    result = j.getString("content");*/

                    result = content;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();
                try {
                    JSONObject j = new JSONObject(s);
                    String ticket  = j.getString("ticket");
                    String order  = j.getString("order");
                    List<Ticket> tickets = (List) GsonUtil.fromJson(ticket,new TypeToken<List<Ticket>>(){}.getType());
                    List<OrderBean> orders = (List) GsonUtil.fromJson(order,new TypeToken<List<OrderBean>>(){}.getType());

                    final List list = new ArrayList();
                    list.add(tickets);
                    list.add(orders);

                    System.out.println(tickets.size()+"tickets.size()");
                    System.out.println(orders.size()+"orders.size()");

                    if (tickets.size()!=0&&orders.size()!=0) {
                        orderList.setAdapter(new OrderAdapter(getContext(), list));
                    }

                    orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        Ticket ticket;
                        Seat seat = new Seat();
                        TicketBean ticketBean = new TicketBean();
//                        Order order;
                        OrderBean orderBean;
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(getContext(), OrderDetailActivity.class);

                            ticket = (Ticket) ((List) list.get(0)).get(position);
                            orderBean = (OrderBean) ((List) list.get(1)).get(position);
                            seat.setNum(ticket.getTrainBox());
                            seat.setSeatNubmer(ticket.getSeatNum());
                            seat.setState(orderBean.getOrderState());
                            seat.setStationStart(ticket.getStartStation());
                            seat.setStationEnd(ticket.getEndStation());
                            seat.setTrainid(ticket.getTrainid());
                            seat.setTime(ticket.getDate());

                            ticketBean.setTrainid(ticket.getTrainid());
                            ticketBean.setStartStation(ticket.getStartStation());
                            ticketBean.setEndStation(ticket.getEndStation());


                            intent.putExtra("seat",seat);

                            //这里需要稍微做修改
                            intent.putExtra("seatType","硬座");
                            intent.putExtra("ticketBean",ticketBean);
                            intent.putExtra("price",ticket.getPrice());
                            intent.putExtra("ticketid",ticket.getTicketid());
                            intent.putExtra("ticketStaus",ticket.getTicketState());
                            startActivity(intent);
                        }
                    });
                }catch (Exception e){

                }


            }
        }.execute(ValueUtil.User.userid,ValueUtil.User.idcard);


    }
}
