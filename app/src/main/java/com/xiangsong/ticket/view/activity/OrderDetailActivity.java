package com.xiangsong.ticket.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.Seat;
import com.xiangsong.ticket.model.TicketBean;
import com.xiangsong.ticket.presenter.util.ValueUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends AppCompatActivity {


    @BindView(R.id.start_date)
    TextView startDate;
    @BindView(R.id.start_station)
    TextView startStation;
    @BindView(R.id.end_station)
    TextView endStation;

    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;

    @BindView(R.id.train_id)
    TextView trainId;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.ticket_type)
    TextView ticketType;
    @BindView(R.id.seat_num)
    TextView seatNum;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.seat_type)
    TextView seatType;
    @BindView(R.id.order_state)
    TextView state;

    @BindView(R.id.order_detail_btn_continue_buy)
    Button btn1;

    @BindView(R.id.order_detail_btn_buy_convert)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ButterKnife.bind(this);

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.toolbar);

        myChildToolbar.setTitle("");
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        String seatTypeValue = getIntent().getStringExtra("seatType");
        Seat seat = (Seat) getIntent().getSerializableExtra("seat");
        TicketBean ticketBean = (TicketBean) getIntent().getSerializableExtra("ticketBean");
        double priceValue = getIntent().getDoubleExtra("price",0.0);



        if (seat.getState().equals("已支付")) {
            btn1.setText("继续购票");
            btn2.setText("预定返程");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else if (seat.getState().equals("已退票")) {
            btn1.setText("继续购票");
            btn2.setText("预定返程");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else{//未支付情况
            btn1.setText("取消订单");
            btn2.setText("立即支付");

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = getIntent().getStringExtra("ticketid");

                }
            });
        }


        name.setText(ValueUtil.User.name);
        ticketType.setText(ValueUtil.User.psgType);

        //进行设置值
        startDate.setText(seat.getTime());
        startStation.setText(ticketBean.getStartStation());
        endStation.setText(ticketBean.getEndStation());
        trainId.setText(ticketBean.getTrainid());

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");//小写的mm表示的是分钟
        String strStart="",strEnd="";
        String dstrStrat=ticketBean.getStartTime();
        String dstrEnd=ticketBean.getEndTime();
        java.util.Date date;
        if (dstrStrat!=""&&dstrStrat!=null) {
            try {
                date=sdf.parse(dstrStrat);
                strStart = sdf.format(date);
                date=sdf.parse(dstrEnd);
                strEnd = sdf.format(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        startTime.setText(strStart);
        endTime.setText(strEnd);



        seatNum.setText(seat.getNum()+"车厢"+seat.getSeatNubmer()+"号");
        price.setText("¥"+priceValue);
        seatType.setText(seatTypeValue);

        //这里要为订单的状态
        state.setText(seat.getState());
        /*if (getIntent().getStringExtra("ticketStaus") != null&&!getIntent().getStringExtra("ticketStaus").equals("")) {
            state.setText(getIntent().getStringExtra("ticketStaus"));
        }*/


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
