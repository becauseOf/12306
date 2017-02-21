package com.xiangsong.ticket.view.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.adapter.CityNameAdapter;
import com.xiangsong.ticket.view.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityNameActivity extends AppCompatActivity {


    @BindView(R.id.city_name_listview)
    ListView cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_name);

        ButterKnife.bind(this);


        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.toolbar);

        myChildToolbar.setTitle("选择车站名");
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final List<String> datas = new ArrayList<>();
        datas.add("广州");
        datas.add("长沙");
        datas.add("武汉");
        datas.add("北京");

        cityName.setAdapter(new CityNameAdapter(this, datas));


        cityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ValueUtil.startStationStr = datas.get(position);
                Intent intent = new Intent();
                intent.putExtra("cityname",datas.get(position));


                setResult(1,intent);
                finish();
            }
        });
    }
}
