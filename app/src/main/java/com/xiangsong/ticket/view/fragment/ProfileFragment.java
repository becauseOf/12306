package com.xiangsong.ticket.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wingjay.blurimageviewlib.FastBlurUtil;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.ProfileItem;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.activity.LoginActivity;
import com.xiangsong.ticket.view.activity.MainActivity;
import com.xiangsong.ticket.view.activity.ProfileDetailActivity;
import com.xiangsong.ticket.view.activity.RegisterActivity;
import com.xiangsong.ticket.view.adapter.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiangsong on 2016/9/11.
 */
public class ProfileFragment extends Fragment{
    @BindView(R.id.profile_bg)
    LinearLayout profileBg;
    @BindView(R.id.profile_lv1)
    ListView listview1;
    @BindView(R.id.profile_lv2)
    ListView listview2;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_iv)
    ImageView loginIv;

    @BindView(R.id.profile_tv_username)
    TextView userName;

    List<ProfileItem> datas1;
    List<ProfileItem> datas2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        ButterKnife.bind(this, getActivity());


        int scaleRatio = 5;
        int blurRadius = 8;
        Bitmap originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slider4);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originBitmap,
                originBitmap.getWidth() / scaleRatio,
                originBitmap.getHeight() / scaleRatio,
                false);
        Bitmap blurBitmap = FastBlurUtil.doBlur(scaledBitmap, blurRadius, true);
        profileBg.setBackground(new BitmapDrawable(blurBitmap));

        datas1 = new ArrayList<>();
        datas2 = new ArrayList<>();

        datas1.add(new ProfileItem(R.drawable.profile_detail,"我的资料"));
        datas1.add(new ProfileItem(R.drawable.profile_sys_mes,"系统消息"));

        datas2.add(new ProfileItem(R.drawable.profile_feedback,"产品意见"));
        datas2.add(new ProfileItem(R.drawable.profile_version,"版本检测"));
        datas2.add(new ProfileItem(R.drawable.profile_about,"关于"));


        listview1.setAdapter(new ProfileAdapter(getContext(), datas1));

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(getContext(), ProfileDetailActivity.class));
                }
            }
        });
        listview2.setAdapter(new ProfileAdapter(getContext(), datas2));

        setListViewHeight(listview1);
        setListViewHeight(listview2);

        if (ValueUtil.User.email!=null&&!ValueUtil.User.email.equals("")) {
            userName.setText(ValueUtil.User.email);
        }


        if (ValueUtil.User.email!=null&&ValueUtil.User.email.equals("")) {
            loginBtn.setText("登录/注册");

        }else{
            loginBtn.setText("登出");
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValueUtil.User.email!=null&&ValueUtil.User.email.equals("")) {
                    loginBtn.setText("登录/注册");
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }else {
                    loginBtn.setText("登出");

                    ValueUtil.User.idcard="";
                    ValueUtil.User.sex="";
                    ValueUtil.User.name="";
                    ValueUtil.User.psgType="";
                    ValueUtil.User.phone="";
                    ValueUtil.User.email="";

                    userName.setText("我的邮箱");
                    loginBtn.setText("登录/注册");
                    Toast.makeText(getContext(), "登出成功！",Toast.LENGTH_LONG).show();
                }

            }
        });



        loginIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileDetailActivity.class));
            }
        });



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
}
