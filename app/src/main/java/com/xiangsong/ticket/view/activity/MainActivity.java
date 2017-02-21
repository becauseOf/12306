package com.xiangsong.ticket.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Property;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.User;
import com.xiangsong.ticket.presenter.ipresenter.IMainPresenter;
import com.xiangsong.ticket.presenter.prensenterimpl.MainPresenterImpl;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.adapter.BuyTicketlAdapter;
import com.xiangsong.ticket.view.adapter.MainViewPagerAdapter;
import com.xiangsong.ticket.view.fragment.CalenderFragment;
import com.xiangsong.ticket.view.fragment.OrderFragment;
import com.xiangsong.ticket.view.fragment.ProfileFragment;
import com.xiangsong.ticket.view.fragment.TicketFragment;
import com.xiangsong.ticket.view.iview.IMainView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements IMainView,View.OnClickListener{


    @BindViews({R.id.ticket,R.id.order,R.id.calender,R.id.profile})
    List<ImageView> imgs;
    @BindViews({R.id.tv_main_ticket,R.id.tv_main_order,R.id.tv_main_calender,R.id.tv_main_profile})
    List<TextView> txts;
    @BindViews({R.id.linear_ticket,R.id.linear_order,R.id.linear_calender,R.id.linear_profile})
    List<LinearLayout> linears;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.toolbar_title)
    TextView toolBarTile;

    List<Integer> imgsResourceGreen = new ArrayList<>();
    List<Integer> imgsResourceGray = new ArrayList<>();
    IMainPresenter mainPresenter;
    private String[] titles = {"车票预订","订单管理","行程管理","个人中心"};
    private int index;

    private List<Fragment> fragmentLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();


        if (ValueUtil.User.email!=null&&!ValueUtil.User.email.equals("")) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("正在查询...");
            dialog.show();
            new AsyncTask<String,String,String>(){

                //查询订单
                @Override
                protected String doInBackground(String... params) {
                    String result = "";


                    try {
                        //断点的位置也是很重要的，应该设置在可能抛出异常的位置，尽量不要使用输出语句，否则可能程序会提前抛出异常，造成程序提前终止
                        HttpURLConnection conn = HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl + "/QueryUser");
                        String parameter = "userid=" + params[0];
                        HttpUtil.setBodyParameter(conn, parameter);
                        String content = HttpUtil.readInputStream(conn);

                        System.out.println("content:" + content);
                        JSONObject j = new JSONObject(content);
                        result = j.getString("content");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    return result;
                }

                @Override
                protected void onPostExecute(String s) {
                    dialog.dismiss();

                    //需要先设置一些默认值，比如性别为男等
                    User user = (User) GsonUtil.fromJson(s, User.class);

                    if (user != null) {
                        ValueUtil.User.email = user.getEmail();
                        ValueUtil.User.idcard = user.getIdcard();
                        ValueUtil.User.name = user.getName();
                        ValueUtil.User.sex = user.getSex();
                        ValueUtil.User.phone = user.getPhone();
                        ValueUtil.User.psgType = "成人票";
                        ValueUtil.User.userid = String.valueOf(user.getUserid());
                    }


                }
            }.execute(ValueUtil.User.email);
        }
    }

    @Override
    public void onChangeColor(View v) {
        resetColor();
        index = linears.indexOf(v);
        txts.get(index).setTextColor(getResources().getColor(R.color.colorPrimary));
        imgs.get(index).setImageResource(imgsResourceGreen.get(index));

    }

    @Override
    public void onChangePage() {
        viewPager.setCurrentItem(index);
    }


    private void initData(){

        //初始数据或者监听器有一定先后顺序
        fragmentLists.add(new TicketFragment());
        fragmentLists.add(new OrderFragment());
        fragmentLists.add(new CalenderFragment());
        fragmentLists.add(new ProfileFragment());

//        rebuild一下就能找到ButterKnife
        ButterKnife.bind(this);

        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragmentLists));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetColor();
                txts.get(position).setTextColor(getResources().getColor(R.color.colorPrimary));
                imgs.get(position).setImageResource(imgsResourceGreen.get(position));
                toolBarTile.setText(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        for(LinearLayout linear: linears) {
            linear.setOnClickListener(this);
        }

        mainPresenter = new MainPresenterImpl(this);

        //这里其实可以用资源数组吧
        imgsResourceGreen.add(R.drawable.ticket_green);
        imgsResourceGreen.add(R.drawable.order_green);
        imgsResourceGreen.add(R.drawable.calender_green);
        imgsResourceGreen.add(R.drawable.profile_green);

        imgsResourceGray.add(R.drawable.ticket_gray);
        imgsResourceGray.add(R.drawable.order_gray);
        imgsResourceGray.add(R.drawable.calender_gray);
        imgsResourceGray.add(R.drawable.profile_gray);

        viewPager.setCurrentItem(ValueUtil.mainViewPager);
    }

    private void resetColor(){

        for (int i = 0; i < imgs.size(); i++) {
            imgs.get(i).setImageResource(imgsResourceGray.get(i));
            txts.get(i).setTextColor(getResources().getColor(R.color.colorMainTxt));
        }
    }

    @Override
    public void onClick(View v) {
        mainPresenter.changeColor(v);
        toolBarTile.setText(titles[index]);

        mainPresenter.changePage();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                TicketFragment.startStation.setText(ValueUtil.startStationStr);
                break;
            case 2:
                TicketFragment.endStaion.setText(ValueUtil.startStationStr);
                break;

        }

    }
}
