package com.example.administrator.mygirl;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mygirl.been.DatabaseHelper;

/**
 * Created by Administrator on 2015/5/5.
 */
public class AddNoteActivity extends Activity{
    TextView title,next;
    EditText add_table_name;
    LinearLayout title_return;
    String addTableName=null;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        findView();
    }

    private void findView() {

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        title= (TextView) findViewById(R.id.title);
        next= (TextView) findViewById(R.id.next);
        add_table_name= (EditText) findViewById(R.id.add_table_name);
        title_return= (LinearLayout) findViewById(R.id.title_return);

        title.setText("添  加");
        title_return.setVisibility(View.VISIBLE);

        title_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTableName=add_table_name.getText().toString().trim();//trim()可以去掉输入字符串的前后空格
                cursor = sqLiteDatabase.rawQuery("select * from notepad where name=?;", new String[]{addTableName});

                if (addTableName.equals("")){

                    Toast.makeText(getApplicationContext(),
                            "主题名称不能为空", Toast.LENGTH_SHORT).show();

                }else if (0!=cursor.getCount()){
                    Toast.makeText(getApplicationContext(),
                            "主题名称已存在！", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(AddNoteActivity.this,WriteNoteActivity.class);
                    intent.putExtra("titleName",addTableName);
                    startActivity(intent);
                }

            }
        });
    }
}
