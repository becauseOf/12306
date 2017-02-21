package com.xiangsong.ticket.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wingjay.blurimageviewlib.FastBlurUtil;
import com.xiangsong.ticket.R;
import com.xiangsong.ticket.model.User;
import com.xiangsong.ticket.model.UserLogin;
import com.xiangsong.ticket.presenter.util.GsonUtil;
import com.xiangsong.ticket.presenter.util.HttpUtil;
import com.xiangsong.ticket.presenter.util.ValueUtil;

import org.json.JSONObject;

import java.net.HttpURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_linear)
    LinearLayout registerLinear;
    @BindView(R.id.register_btn)
    Button registerBtn;

    @BindView(R.id.register_et_username)
    EditText userName;

    @BindView(R.id.register_et_pwd)
    EditText psd;

    @BindView(R.id.register_et_verify_pwd)
    EditText verify;

    @BindView(R.id.back)
    ImageView back;

    Bitmap originBitmap,scaledBitmap,blurBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int scaleRatio = 20;
        int blurRadius = 8;
        /*originBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.register_bg);
        scaledBitmap = Bitmap.createScaledBitmap(originBitmap,
                originBitmap.getWidth() / scaleRatio,
                originBitmap.getHeight() / scaleRatio,
                false);
        blurBitmap = FastBlurUtil.doBlur(scaledBitmap, blurRadius, true);
        registerLinear.setBackground(new BitmapDrawable(blurBitmap));*/

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!psd.getText().toString().equals(verify.getText().toString())) {
                    Toast.makeText(RegisterActivity.this,"两次密码输入不一致!",Toast.LENGTH_LONG).show();
                }else{
                    final ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
                    dialog.setTitle("正在注册...");
                    dialog.show();
                    new AsyncTask<String,Integer,Boolean>(){
                        String content;

                        @Override
                        protected Boolean doInBackground(String... params) {
                            Boolean isSuccess = false;
                            try {
                                //断点的位置也是很重要的，应该设置在可能抛出异常的位置，尽量不要使用输出语句，否则可能程序会提前抛出异常，造成程序提前终止
                                HttpURLConnection conn =  HttpUtil.getHttpUrlConnection(HttpUtil.baseUrl+"/Register");
                                User user = new User(1," "," "," "," "," "," "," ");
                                user.setEmail(params[0]);
                                user.setSex("男");
                                UserLogin userLogin = new UserLogin(1,params[0],params[1]);

                                String parameter = "user="+ GsonUtil.toJson(user,User.class)+"&login="+GsonUtil.toJson(userLogin,UserLogin.class);
                                HttpUtil.setBodyParameter(conn,parameter);
                                content = HttpUtil.readInputStream(conn);

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
                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                ValueUtil.User.email = userName.getText().toString();
                                startActivity(new Intent(RegisterActivity.this, ProfileDetailActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_LONG).show();
                            }
                        }
                    }.execute(userName.getText().toString(),psd.getText().toString());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(originBitmap != null && !originBitmap.isRecycled()){
            // 回收并且置为null
            originBitmap.recycle();
            originBitmap = null;
        }
        if(scaledBitmap != null && !scaledBitmap.isRecycled()){
            // 回收并且置为null
            scaledBitmap.recycle();
            scaledBitmap = null;
        }
        if(blurBitmap != null && !blurBitmap.isRecycled()){
            // 回收并且置为null
            blurBitmap.recycle();
            blurBitmap = null;
        }
        System.gc();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
