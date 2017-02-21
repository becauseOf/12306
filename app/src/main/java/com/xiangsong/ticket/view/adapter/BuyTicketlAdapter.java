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

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.Order;
import com.xiangsong.ticket.model.OrderBean;
import com.xiangsong.ticket.model.QuerySeat;
import com.xiangsong.ticket.model.QueryTicketItem;
import com.xiangsong.ticket.model.RemainTicket;
import com.xiangsong.ticket.model.Seat;
import com.xiangsong.ticket.model.Ticket;
import com.xiangsong.ticket.model.TicketBean;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.activity.LoginActivity;
import com.xiangsong.ticket.view.activity.MainActivity;
import com.xiangsong.ticket.view.activity.OrderDetailActivity;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gala on 2016/7/3.
 */
public class BuyTicketlAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<HashMap<String,Integer>> remainNums;
    private TextView textView;
    private Context context;
    private String[] names = {"硬座","硬卧","软卧"};
    private double[] prices;
    private int[] nums;
    private TicketBean ticketBean;
//    private View.OnClickListener onClickListener;

    public BuyTicketlAdapter(Context context, double[] prices,int[] nums,TicketBean ticketBean) {
        mInflater = LayoutInflater.from(context);
        this.context =context;
        this.prices = prices;
        this.nums = nums;
        this.ticketBean = ticketBean;
    }

    @Override
    public int getCount() {
        return names.length;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.buy_ticket_lv_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.seatType.setText(names[position]);
        viewHolder.ticketNum.setText(nums[position]+"张");
        viewHolder.price.setText("¥"+prices[position]);
        /*viewHolder.btnYuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        viewHolder.btnYuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //生成订单，然后才能跳转
                /**
                 * 开始进行生成订单的操作
                 */

                if (ValueUtil.User.email!=null&&ValueUtil.User.email.equals("")){
                    Toast.makeText(context, "请首先登陆",Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, LoginActivity.class));
                }else if (nums[position] != 0) {
                    final ProgressDialog dialog = new ProgressDialog(context);
                    dialog.setTitle("正在预定...");
                    dialog.show();
                    new AsyncTask<String,String,String>(){

                        /*
                         * 点击预定操作之后，从后台获取席位信息，然后跳转到订单详情页面
                         *
                         * */
                        @Override
                        protected String doInBackground(String... params) {
                            String datas = "";
                            try {
                                //断点的位置也是很重要的，应该设置在可能抛出异常的位置，尽量不要使用输出语句，否则可能程序会提前抛出异常，造成程序提前终止
                                HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/OrderDetail");

                                QuerySeat queryTicketItem = new QuerySeat(ticketBean.getStartStation(),ticketBean.getEndStation(),ticketBean.getTrainid(),names[position],ticketBean.getDate());
                                String parameter = "content="+ GsonUtil.toJson(queryTicketItem,QuerySeat.class);
                                HttpUtil.setBodyParameter(conn,parameter);
                                String content = HttpUtil.readInputStream(conn);


                                JSONObject j = new JSONObject(content);
                                datas = j.getString("content");

                                Seat seat = (Seat) GsonUtil.fromJson(datas,Seat.class);

                                //测试了一晚上的DAO，以为是DAO出问题，结果居然是URL链接写错了
                                HttpURLConnection conn1 =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/GenerateOrder");

                                Ticket ticket = new Ticket();
                                ticket.setTrainid(seat.getTrainid());
                                ticket.setDate(ticketBean.getDate());

                                ticket.setTrainBox(seat.getNum());
                                ticket.setSeatNum(seat.getSeatNubmer());
                                ticket.setStartStation(ticketBean.getStartStation());
                                ticket.setEndStation(ticketBean.getEndStation());
                                ticket.setPrice(ticketBean.getYingzuoPrice().doubleValue());
//                                ticket.setName("王林");
                                ticket.setName(ValueUtil.User.name);
                                ticket.setPasgtype("成人");
//                                ticket.setIdcard("450421199506084511");
                                ticket.setIdcard(ValueUtil.User.idcard);

                                OrderBean orderBean = new OrderBean();
                                orderBean.setOrderState("未支付");
                                //下单的时候userid错误
                                orderBean.setUserid(Integer.parseInt(ValueUtil.User.userid));
                                orderBean.setOrderTime(ticket.getDate());

                                String parameter1 = "ticket="+ GsonUtil.toJson(ticket,Ticket.class)
                                        +"&order="+GsonUtil.toJson(orderBean,OrderBean.class);
                                HttpUtil.setBodyParameter(conn1,parameter1);

//                            String content1 = HttpUtil.readInputStream(conn);
//                            这里又犯了低级错误，必须要调用readInputStream，URL链接才会真正地建立
//                            而且conn应该写成conn1
                                String content1 = HttpUtil.readInputStream(conn1);
                                System.out.print(content1);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return datas;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            dialog.dismiss();
                            Seat seat = (Seat) GsonUtil.fromJson(s,Seat.class);

//                        Intent intent = new Intent(context, OrderDetailActivity.class);
                            Intent intent = new Intent(context, MainActivity.class);
                            ValueUtil.mainViewPager = 1;

                            Toast.makeText(context, "预定成功",Toast.LENGTH_SHORT).show();
                            intent.putExtra("seat",seat);
                            intent.putExtra("seatType",names[position]);
                            intent.putExtra("ticketBean",ticketBean);
                            intent.putExtra("price",prices[position]);
                            context.startActivity(intent);
                        }
                    }.execute("");
                }else {
                    Toast.makeText(context, "该席位已经没有余票！",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return convertView;
    }

    static class ViewHolder{
        @BindView(R.id.buy_ticket_item_seat_type) TextView seatType;
        @BindView(R.id.buy_ticket_item_price) TextView price;
        @BindView(R.id.ticket_num) TextView ticketNum;
        @BindView(R.id.buy_ticket_item_btn_yuding) Button btnYuding;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }

    }
}
