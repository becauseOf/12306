package com.xiangsong.ticket.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.QueryTicketItem;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.activity.BuyTicketActivity;
import com.xiangsong.ticket.view.activity.CityNameActivity;
import com.xiangsong.ticket.view.activity.ProfileDetailActivity;
import com.xiangsong.ticket.view.activity.RemainTicketActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiangsong on 2016/9/11.
 */
public class TicketFragment extends Fragment{

    private SliderLayout mSlider;
    @BindView(R.id.btn_query_remain_ticket)
    Button query;
//    @BindView(R.id.ticket_tv_start_station)
    public static TextView startStation;
//    @BindView(R.id.ticket_tv_end_station)
    public static TextView endStaion;
//    @BindView(R.id.ticket_tv_start_date)
    public static TextView startDate;
    public static TextView startWeek;
    @BindView(R.id.ticket_sw_query_g)
    Switch queryG;
    @BindView(R.id.ticket_sw_query_z)
    Switch queryZ;

    public static String startStationStr="";
    public static String endStationStr="";


    int y=2016;
    int m=9;
    int d=33;

    String myWeek = "三";


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("zhiwei:onActivityCreated"+ValueUtil.startStationStr);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("zhiwei:onDetach"+ValueUtil.startStationStr);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        System.out.println("zhiwei:onAttach"+ValueUtil.startStationStr);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("zhiwei:onAttach"+ValueUtil.startStationStr);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket,container,false);
        TextView startStation = (TextView) view.findViewById(R.id.ticket_tv_start_station);
        /*Bundle bundle = getArguments();
        if (bundle != null) {
            startStation.setText(bundle.getString("start"));
        }*/

        System.out.println("zhiwei:"+ValueUtil.startStationStr);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {

        mSlider = (SliderLayout) view.findViewById(R.id.slider);

        startStation = (TextView) view.findViewById(R.id.ticket_tv_start_station);
        endStaion = (TextView) view.findViewById(R.id.ticket_tv_end_station);
        startDate = (TextView) view.findViewById(R.id.ticket_tv_start_date);
        startWeek = (TextView) view.findViewById(R.id.ticket_tv_start_week);

        DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());
        defaultSliderView.image(R.drawable.slider3)
                .setScaleType(BaseSliderView.ScaleType.Fit);

        mSlider.addSlider(defaultSliderView);

        ButterKnife.bind(this,getActivity());

        startStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(getContext(), CityNameActivity.class),1);
            }
        });

        endStaion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivityForResult(new Intent(getContext(), CityNameActivity.class),2);
            }
        });

        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View birthdayview = layoutInflater.inflate(R.layout.date,null);

                TextView cancel = (TextView) birthdayview.findViewById(R.id.cancel_txt);
                TextView ok = (TextView) birthdayview.findViewById(R.id.ok_txt);
                final DatePicker datePicker = (DatePicker) birthdayview.findViewById(R.id.datepicker);

                final Dialog birthdaydialog = new AlertDialog.Builder(getContext()).setView(birthdayview).create();
                birthdaydialog.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        birthdaydialog.dismiss();
                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        /*
* 这里通过蔡勒公式算出某一天是星期几
*/
                         y=datePicker.getYear();
                         m=datePicker.getMonth()+1;
                        int c=20;
                         d=datePicker.getDayOfMonth()+12;
                        int w=(y+(y/4)+(c/4)-2*c+(26*(m+1)/10)+d-1)%7+1;

                        switch(w)
                        {
                            case 0:
                                myWeek="日";
                                break;
                            case 1:
                                myWeek="一";
                                break;
                            case 2:
                                myWeek="二";
                                break;
                            case 3:
                                myWeek="三";
                                break;
                            case 4:
                                myWeek="四";
                                break;
                            case 5:
                                myWeek="五";
                                break;
                            case 6:
                                myWeek="六";
                                break;
                            default:
                                myWeek="日";
                                break;
                        }
                        birthdaydialog.dismiss();
                        startDate.setText((datePicker.getMonth()+1)+"月"+datePicker.getDayOfMonth()+"日");
                        startWeek.setText("周"+myWeek);
                    }
                });

            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RemainTicketActivity.class);
                //长沙到武汉
                String mStr=String.valueOf(m);
                if (mStr.length()==1) {
                    mStr = "0"+mStr;
                }
                QueryTicketItem queryTicketItem = new QueryTicketItem(startStation.getText().toString(),endStaion.getText().toString(),y+"-"+mStr+"-"+(d-12),queryG.isChecked(),queryZ.isChecked());
                ValueUtil.totalDate = mStr+"月"+(d-12)+"日周"+myWeek;
//                QueryTicketItem queryTicketItem = new QueryTicketItem(startStation.getText().toString(),endStaion.getText().toString(),startDate.getText().toString(),queryG.isChecked(),queryZ.isChecked());

                intent.putExtra("queryDatas",queryTicketItem);

                startActivity(intent);
            }
        });

        /*startStation.setText(ValueUtil.startStationStr);
        if (!ValueUtil.startStationStr.equals("")) {
            startStation.setText(ValueUtil.startStationStr);
        }
        if (!endStationStr.equals("")) {
            endStaion.setText(endStationStr);
        }*/

        System.out.println("zhiwei:"+ValueUtil.startStationStr);


        /*DefaultSliderView defaultSliderView1 = new DefaultSliderView(getContext());
        defaultSliderView.image(R.drawable.slider4)
                .setScaleType(BaseSliderView.ScaleType.Fit);

        mSlider.addSlider(defaultSliderView1);*/

    }

}
