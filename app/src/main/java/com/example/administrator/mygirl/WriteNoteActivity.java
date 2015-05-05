package com.example.administrator.mygirl;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mygirl.been.DatabaseHelper;
import com.mygirl.been.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/5.
 */
public class WriteNoteActivity extends Activity{
    LinearLayout finish,title_return;
    TextView title;
    EditText writebody;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    LoadingDialog dialog;
    String titleName,nowTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writenote_activity);

        findView();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                getNowTime();
                new Thread(){
                    @Override
                    public void run() {
                        Message msg=new Message();
                        loadData();
                        msg.what=1;
                        handler.sendMessageDelayed(msg, 2000);
                    }
                }.start();


            }

        });
    }
    private void loadData(){
        sqLiteDatabase.execSQL("insert into notepad(name,time,body) values(?,?,?);",
                new Object[]{titleName,nowTime,writebody.getText().toString().trim()});
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what==1){
                Toast.makeText(getApplicationContext(),
                        "保存成功", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

                finish();
            }else{
                Toast.makeText(getApplicationContext(),
                        "保存失败", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void findView() {
        Intent intent=getIntent();
        titleName=intent.getStringExtra("titleName");

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        dialog = new LoadingDialog(this);

        title= (TextView) findViewById(R.id.title);
        title_return= (LinearLayout) findViewById(R.id.title_return);
        finish= (LinearLayout) findViewById(R.id.finish);
        finish.setVisibility(View.VISIBLE);

        writebody= (EditText) findViewById(R.id.writebody);
        title.setText("添加-"+titleName);
        title_return.setVisibility(View.VISIBLE);
        title_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getNowTime() {
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");
        Date curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
        String nowtime   =   formatter.format(curDate);
        nowTime=nowtime;
    }
}
