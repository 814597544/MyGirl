package com.example.administrator.mygirl;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import lt.lemonlabs.android.expandablebuttonmenu.ExpandableButtonMenu;
import lt.lemonlabs.android.expandablebuttonmenu.ExpandableMenuOverlay;

/**
 * Created by Administrator on 2015/5/4.
 */
public class HomeActivity extends TabActivity {

    private ExpandableMenuOverlay menuOverlay;

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        menuOverlay = (ExpandableMenuOverlay) findViewById(R.id.button_menu);

        tabHost = getTabHost();
        setTabs();

        /*menu中的点击事件*/
        menuOverlay.setOnMenuButtonClickListener(new ExpandableButtonMenu.OnMenuButtonClick() {
            @Override
            public void onClick(ExpandableButtonMenu.MenuButton action) {
                switch (action) {
                    case MID:
                        startActivity2();
                        break;
                    case LEFT:
                        startActivity1();
                        break;
                    case RIGHT:
                        startActivity3();
                        break;
                }
            }
        });
    }

    public void startActivity1(){

    }
    public void startActivity2(){

    }
    public void startActivity3(){

    }

    private void setTabs()
    {
        addTab("MyGirl", R.drawable.zkl_search, MyGirlActivity.class);
        addTab("记事本", R.drawable.schooltimetable_search, NotepadActivity.class);
    }
    private void addTab(String labelId, int drawableId, Class<?> c)
    {
        Intent intent = new Intent(this, c);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
        TextView title = (TextView) tabIndicator.findViewById(R.id.title);
        title.setText(labelId);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }


}
