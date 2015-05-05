package com.example.administrator.mygirl;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mygirl.been.DatabaseHelper;
import com.mygirl.been.LoadingDialog;


/**
 * Created by Administrator on 2015/5/5.
 */
public class MyNoteActivity extends Activity{
    LinearLayout finish,title_return;
    TextView title,noteName,datetime,body;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    String titleName,nowTime,Body;
    LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynote_activity);

        findView();
        dialog.show();
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

    private void loadData() {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from notepad where name=?" ,new String[]{titleName});

        while (cursor.moveToNext()) {
            titleName=cursor.getString(0);
            nowTime=cursor.getString(1);
            Body=cursor.getString(2);

        }

    }

    private void findView() {
        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Intent intent=getIntent();
        titleName=intent.getStringExtra("titleName");
        dialog = new LoadingDialog(this);

        title= (TextView) findViewById(R.id.title);
        title_return= (LinearLayout) findViewById(R.id.title_return);
        noteName= (TextView) findViewById(R.id.noteName);
        datetime= (TextView) findViewById(R.id.datetime);
        body= (TextView) findViewById(R.id.body);


        title.setText("我的记事本");
        title_return.setVisibility(View.VISIBLE);
        title_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what==1){
                noteName.setText(titleName);
                datetime.setText(nowTime);
                body.setText(Body);
            }else{
                Toast.makeText(getApplicationContext(),
                        "加载数据失败", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        }
    };
}
