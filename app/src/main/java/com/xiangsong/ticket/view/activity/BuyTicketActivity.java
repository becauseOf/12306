package com.xiangsong.ticket.view.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.RemainTicket;
import com.xiangsong.ticket.model.TicketBean;
import com.xiangsong.ticket.model.User;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.adapter.BuyTicketlAdapter;
import com.xiangsong.ticket.view.adapter.ProfileDetailAdapter;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyTicketActivity extends AppCompatActivity {


    @BindView(R.id.buy_tickte_lv)
    ListView lv;
    @BindView(R.id.toolbar_title)
    TextView title;
    @BindView(R.id.start_station)
    TextView startStaion;
    @BindView(R.id.end_station)
    TextView endStation;

    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;

    @BindView(R.id.date)
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);

        ButterKnife.bind(this);

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.toolbar);

        myChildToolbar.setTitle("");
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        date.setText(ValueUtil.totalDate);
        final TicketBean remainTicket = (TicketBean) getIntent().getSerializableExtra("remainTicketItem");

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");//小写的mm表示的是分钟
        String strStart="",strEnd="";
        String dstrStrat=remainTicket.getStartTime();
        String dstrEnd=remainTicket.getEndTime();
        java.util.Date date;
        try {
            date=sdf.parse(dstrStrat);
            strStart = sdf.format(date);
            date=sdf.parse(dstrEnd);
            strEnd = sdf.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        startTime.setText(strStart);
        endTime.setText(strEnd);
        /*final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在预定中...");
        dialog.show();*/

        new AsyncTask<String,String,String>(){



            //查询订单
            @Override
            protected String doInBackground(String... params) {
                String result="";


                /*try {
                    //断点的位置也是很重要的，应该设置在可能抛出异常的位置，尽量不要使用输出语句，否则可能程序会提前抛出异常，造成程序提前终止
                    HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/QueryUser");
                    String parameter = "userid="+ params[0];
                    HttpUtil.setBodyParameter(conn,parameter);
                    String content = HttpUtil.readInputStream(conn);

                    System.out.println("content:"+content);
                    JSONObject j = new JSONObject(content);
                    result = j.getString("content");

                } catch (Exception e) {
                    e.printStackTrace();
                }*/


                return result;
            }

            @Override
            protected void onPostExecute(String s) {
//                dialog.dismiss();

                //需要先设置一些默认值，比如性别为男等
                /*User user = (User) GsonUtil.fromJson(s,User.class);

                if (user != null) {
                    ValueUtil.User.email=user.getEmail();
                    ValueUtil.User.idcard=user.getIdcard();
                    ValueUtil.User.name=user.getName();
                    ValueUtil.User.sex=user.getSex();
                    ValueUtil.User.phone=user.getPhone();
                    ValueUtil.User.psgType="成人";
                }*/


                title.setText(remainTicket.getTrainid());

                startStaion.setText(remainTicket.getStartStation());
                endStation.setText(remainTicket.getEndStation());

                double[] prices = {remainTicket.getYingzuoPrice().doubleValue(),remainTicket.getYingwoPrice().doubleValue(),remainTicket.getRuanwoPrice().doubleValue()};
                int[] nums = {remainTicket.getYingzuo(),remainTicket.getYingwo(),remainTicket.getRuanwo()};

                lv.setAdapter(new BuyTicketlAdapter(BuyTicketActivity.this, prices,nums,remainTicket));


            }
        }.execute(ValueUtil.User.email);






    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
