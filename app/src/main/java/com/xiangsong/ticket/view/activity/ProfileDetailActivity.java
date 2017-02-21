package com.xiangsong.ticket.view.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.User;
import com.xiangsong.ticket.model.UserLogin;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;
import com.xiangsong.ticket.view.adapter.OrderAdapter;
import com.xiangsong.ticket.view.adapter.ProfileDetailAdapter;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileDetailActivity extends AppCompatActivity {

    @BindView(R.id.profile_detail_lv)
    ListView listView;

    @BindView(R.id.profile_detail_ok_btn)
    Button okButton;

    private User user1 = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);


        ButterKnife.bind(this);
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.toolbar);

        myChildToolbar.setTitle("");
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //获取个人信息



        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在查询...");
        dialog.show();

        new AsyncTask<String,String,String>(){


            //查询订单
            @Override
            protected String doInBackground(String... params) {
                String result="";
                try {
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
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();

                //需要先设置一些默认值，比如性别为男等
                final User user = (User) GsonUtil.fromJson(s,User.class);

               /* HashMap<String,String> values = new HashMap<>();
                values.put("姓名:",user.getName());
                values.put("性别:",user.getSex());
                values.put("身份证号:",user.getIdcard());
                values.put("旅客类型:","");
                values.put("电话号码",user.getPhone());
                values.put("邮箱",user.getEmail());*/
                String[] datas = new String[0];
                if (user != null) {
                    datas = new String[]{user.getName(), user.getSex(), user.getIdcard(), "成人", user.getPhone(), user.getEmail()};
                    ValueUtil.User.email=user.getEmail();
                    ValueUtil.User.idcard=user.getIdcard();
                    ValueUtil.User.name=user.getName();
                    ValueUtil.User.sex=user.getSex();
                    ValueUtil.User.phone=user.getPhone();
                    ValueUtil.User.psgType="成人";
                    user1.setEmail(user.getEmail());
                    user1.setIdcard(user.getIdcard());
                    user1.setName(user.getName());
                    user1.setSex(user.getSex());
                    user1.setPhone(user.getPhone());

                }


                /*if (datas.length!=0) {

                }*/
                listView.setAdapter(new ProfileDetailAdapter(ProfileDetailActivity.this, datas));

                final LayoutInflater layoutInflater = LayoutInflater.from(ProfileDetailActivity.this);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        final TextView textView = (TextView) view.findViewById(R.id.profile_item_value);
                        switch (position){
                            case 1://性别
                                View sexview = layoutInflater.inflate(R.layout.sex,null);


                                Button cancel  = (Button) sexview.findViewById(R.id.cancel_sex);
                                RadioGroup radioGroup = (RadioGroup) sexview.findViewById(R.id.sexs);

                                RadioButton man = (RadioButton) sexview.findViewById(R.id.man);
                                RadioButton woman = (RadioButton) sexview.findViewById(R.id.women);
                                if (textView.getText().equals("男")) {
                                    man.setChecked(true);
                                    woman.setChecked(false);
                                }else{
                                    man.setChecked(false);
                                    woman.setChecked(true);
                                }

                                final Dialog dialog = new AlertDialog.Builder(ProfileDetailActivity.this).setView(sexview).create();
                                dialog.show();
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });

                                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        switch (checkedId){
                                            case R.id.man:
                                                dialog.dismiss();
                                                textView.setText("男");
                                                user1.setSex("男");
                                                break;
                                            case R.id.women:
                                                textView.setText("女");
                                                user1.setSex("女");
                                                dialog.dismiss();
                                                break;
                                        }
                                    }
                                });


                                break;
                            default://其余都一样

                                if (position !=5) {
                                    View birthdayview = layoutInflater.inflate(R.layout.activity_name,null);

                                    TextView cancel1 = (TextView) birthdayview.findViewById(R.id.cancel_txt);
                                    TextView ok = (TextView) birthdayview.findViewById(R.id.ok_txt);
                                    final EditText name = (EditText) birthdayview.findViewById(R.id.edit_name);

                                    final Dialog birthdaydialog = new AlertDialog.Builder(ProfileDetailActivity.this).setView(birthdayview).create();
                                    birthdaydialog.show();

                                    cancel1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            birthdaydialog.dismiss();
                                        }
                                    });

                                    ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            birthdaydialog.dismiss();
                                            String value= name.getText().toString();

                                            if (value != null&&!value.equals("")) {
                                                textView.setText(value);
                                                switch (position){
                                                    case 0:
                                                        user1.setName(value);
                                                        break;
                                                    case 2:
                                                        user1.setIdcard(value);
                                                        break;
                                                    case 4:
                                                        user1.setPhone(value);
                                                        break;
                                                    case 5:
                                                        user1.setEmail(value);
                                                        break;
                                                }
                                            }

                                        }
                                    });
                                }else{
                                    Toast.makeText(ProfileDetailActivity.this,"邮箱不可修改！",Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                    }
                });

            }
        }.execute(ValueUtil.User.email);


        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user1.getSex() != null&&!user1.getSex().equals("")) {
                    ValueUtil.User.sex=user1.getSex();
                }
                if (user1.getPhone() != null&&!user1.getPhone().equals("")) {
                    ValueUtil.User.phone=user1.getPhone();
                }
                if (user1.getIdcard() != null&&!user1.getIdcard().equals("")) {
                    ValueUtil.User.idcard=user1.getIdcard();
                }
                if (user1.getEmail() != null&&!user1.getEmail().equals("")) {
                    ValueUtil.User.email=user1.getEmail();
                }
                if (user1.getName() != null&&!user1.getName().equals("")) {
                    ValueUtil.User.name=user1.getName();
                }

                /*ValueUtil.User.sex=user1.getSex();
                ValueUtil.User.phone=user1.getPhone();
                ValueUtil.User.idcard=user1.getIdcard();
                ValueUtil.User.email=user1.getEmail();
                ValueUtil.User.name=user1.getName();*/

                final ProgressDialog dialog = new ProgressDialog(ProfileDetailActivity.this);
                dialog.setTitle("正在保存...");
                dialog.show();

                new AsyncTask<User,Integer,Boolean>(){

                    //错误原因都在于服务端测试时有userid，而客户端则没有，应该通过用户名来更新

                    @Override
                    protected Boolean doInBackground(User... params) {
                        Boolean isSuccess = false;
                        try {
                            //断点的位置也是很重要的，应该设置在可能抛出异常的位置，尽量不要使用输出语句，否则可能程序会提前抛出异常，造成程序提前终止
                            HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/EditUser");
                            String parameter = "user="+GsonUtil.toJson(user1,User.class);
                            HttpUtil.setBodyParameter(conn,parameter);
                            String content = HttpUtil.readInputStream(conn);

                            System.out.println("content:"+content);
                            JSONObject j = new JSONObject(content);
                            isSuccess = j.getBoolean("content");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return isSuccess;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        dialog.dismiss();
                        if (aBoolean) {
                            Toast.makeText(ProfileDetailActivity.this,"保存个人信息成功！",Toast.LENGTH_SHORT).show();
                            ValueUtil.mainViewPager=3;
                            startActivity(new Intent(ProfileDetailActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(ProfileDetailActivity.this,"保存个人信息失败！",Toast.LENGTH_LONG).show();
                        }
                    }
                }.execute(user1);


            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
