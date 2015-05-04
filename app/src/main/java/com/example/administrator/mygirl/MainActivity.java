package com.example.administrator.mygirl;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mygirl.been.LoadingDialog;
import com.mygirl.been.SharePrefrerncesUtil;


public class MainActivity extends Activity {

    View logain_view;
    LinearLayout rpsd;
    ImageView rpsd_false,rpsd_true;
    boolean isRemeber=false;
    EditText userName,passWord;
    Button bt_login;
    String uname,upass;
    private LoadingDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        login();
        rPassword();
    }

    private void findView() {
        dialog1 = new LoadingDialog(this);

        logain_view=(View)findViewById(R.id.logain_view);
        logain_view.getBackground().setAlpha(126);

        rpsd= (LinearLayout) findViewById(R.id.rpsd);
        rpsd_false= (ImageView) findViewById(R.id.rpsd_false);
        rpsd_true= (ImageView) findViewById(R.id.rpsd_true);
        userName= (EditText) findViewById(R.id.edit_login_name);
        passWord= (EditText) findViewById(R.id.edit_login_pw);
        userName.setText(SharePrefrerncesUtil.getRpsd(MainActivity.this).get(0).toString());
        passWord.setText(SharePrefrerncesUtil.getRpsd(MainActivity.this).get(1).toString());
        bt_login= (Button) findViewById(R.id.bt_login);

        if (!SharePrefrerncesUtil.getRpsd(MainActivity.this).get(0).toString().equals("")){
            rpsd_true.setVisibility(View.VISIBLE);
            rpsd_false.setVisibility(View.GONE);
            isRemeber=true;

        }

    }

    private void rPassword() {
        rpsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRemeber){
                    rpsd_true.setVisibility(View.GONE);
                    rpsd_false.setVisibility(View.VISIBLE);
                    SharePrefrerncesUtil.remove(MainActivity.this,"Uname");
                    SharePrefrerncesUtil.remove(MainActivity.this, "Upsd");
                    isRemeber=false;
                }else{
                    rpsd_true.setVisibility(View.VISIBLE);
                    rpsd_false.setVisibility(View.GONE);
                    isRemeber=true;
                }
            }
        });
    }


    private void login() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 验证用户
                uname = userName.getText().toString().trim();
                upass = passWord.getText().toString().trim();

                if ("".equals(uname)) {
                    Toast.makeText(MainActivity.this, "请填写MyGirl名字！", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                } else if ("".equals(upass)) {
                    Toast.makeText(MainActivity.this, "请填写MyGirl生日！",Toast.LENGTH_SHORT).show();
                    passWord.requestFocus();
                } else if (!"金莹".equals(uname)) {
                    Toast.makeText(MainActivity.this, "MyGirl名字不正确！", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                }else if (!"0318".equals(upass)) {
                    Toast.makeText(MainActivity.this, "MyGirl生日不正确！", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                }else{
                    dialog1.show();
                    new Thread() {
                        public void run() {

                            Message msg = new Message();

                                msg.what=1;

                            handler.sendMessageDelayed(msg, 2000);
                        }
                    }.start();
                }
            }
        });
    }

  Handler handler=new Handler(){
      public void handleMessage(Message msg) {
          dialog1.dismiss();

          if (msg.what == 1) {
              Toast.makeText(MainActivity.this, "登录成功，欢迎来到MyGirl...", Toast.LENGTH_SHORT).show();
              //记住密码
              if (isRemeber){
                  SharePrefrerncesUtil.remPsd(MainActivity.this,uname,upass);
              }
             // finish();
                   /* startActivity(new Intent(LoginActivity.this,UserInfoActivity.class));*/

          }else{
              Toast.makeText(MainActivity.this, "系统故障，登录失败", Toast.LENGTH_SHORT).show();
          }

      }
  };

}
