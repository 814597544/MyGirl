package com.mygirl.been;

import android.app.Application;

/**
 * Created by Administrator on 2015/5/6.
 */
public class MyApplication extends Application{

    int pictureNum;
    boolean isRun;

    @Override
    public void onCreate() {
        super.onCreate();
        setPictureNum(1);
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

    public int getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(int pictureNum) {
        this.pictureNum = pictureNum;
    }
}
