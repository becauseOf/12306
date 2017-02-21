package com.xiangsong.ticket.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.Order;
import com.xiangsong.ticket.model.QueryTicketItem;
import com.xiangsong.ticket.model.RemainTicket;
import com.xiangsong.ticket.model.TicketBean;
import com.xiangsong.ticket.model.UserLogin;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.adapter.RemainTickteAdapter;
import com.xiangsong.ticket.view.adapter.RemainTickteNewAdapter;
import com.xiangsong.ticket.view.widget.CustomProgress;

import org.json.JSONObject;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RemainTicketActivity extends AppCompatActivity {

    @BindView(R.id.remain_ticket_lv)
    ListView remainTicketLists;
    @BindView(R.id.toolbar_title)
    TextView startStation;
    @BindView(R.id.toolbar_title1)
    TextView endStation;

    @BindView(R.id.date)
    TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remain_ticket);

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

        //获取查询数据，进行查询票的余额
        final QueryTicketItem queryTicketItem = (QueryTicketItem) getIntent().getSerializableExtra("queryDatas");
        startStation.setText(queryTicketItem.getFromCity());
        endStation.setText(queryTicketItem.getToCity());


        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在查询...");
        dialog.show();
        //完成登录的操作
        new AsyncTask<Void,Integer,String>(){
            String content;

            @Override
            protected String doInBackground(Void... params) {
                String datas = "";
                try {
                    //断点的位置也是很重要的，应该设置在可能抛出异常的位置，尽量不要使用输出语句，否则可能程序会提前抛出异常，造成程序提前终止
                    HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/QueryTicket");
                    String parameter = "content="+ GsonUtil.toJson(queryTicketItem,QueryTicketItem.class);
                    HttpUtil.setBodyParameter(conn,parameter);
                    content = HttpUtil.readInputStream(conn);


                    JSONObject j = new JSONObject(content);
                    datas = j.getString("content");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return datas;
            }


            @Override
            protected void onPostExecute(String string) {
                dialog.dismiss();
                final List<TicketBean> list = (List<TicketBean>) GsonUtil.fromJson(string,new TypeToken<List<TicketBean>>(){}.getType());
                if (list != null) {
                    remainTicketLists.setAdapter(new RemainTickteNewAdapter(RemainTicketActivity.this,list));

                    remainTicketLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(RemainTicketActivity.this, BuyTicketActivity.class);
                            intent.putExtra("remainTicketItem",list.get(position));

                            startActivity(intent);
                        }
                    });
                }

            }
        }.execute();








//        setListViewHeight(remainTicketLists);
    }

    public void setListViewHeight(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
