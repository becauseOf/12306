package com.xiangsong.ticket.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.Order;
import com.xiangsong.ticket.view.activity.OrderDetailActivity;
import com.xiangsong.ticket.view.adapter.CalenderAdapter;
import com.xiangsong.ticket.view.adapter.OrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiangsong on 2016/9/11.
 */
public class CalenderFragment extends Fragment {

    @BindView(R.id.calender_lv)
    ListView calenderList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {

        ButterKnife.bind(this, getActivity());


        calenderList.setAdapter(new CalenderAdapter(getContext(), new ArrayList<Order>()));

    }
}
