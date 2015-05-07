package com.example.administrator.mygirl;


import android.graphics.drawable.Drawable;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mygirl.been.MyApplication;
import com.mygirl.been.SnowView;

import java.io.IOException;


/**
 * Created by Administrator on 2015/5/5.
 */
public class MyGirlActivity extends BaseActivity{


    private ImageView imageView_pic,start,stop,music_stop;
    private TextView textView_desc ;
    View mygirl_layout;

    /**
     * 切换的动画
     */
    private Animation mFadeIn;
    private Animation mFadeInScale;
    private Animation mFadeOut;
    private Animation rotate;

    /**
     * 图片
     */
    private Drawable first;
    private Drawable mPicture_1;
    private Drawable mPicture_2;
    private Drawable mPicture_3;
    private Drawable mPicture_4;
    private Drawable mPicture_5;
    private Drawable mPicture_6;
    private Drawable mPicture_7;
    private Drawable mPicture_8;
    private Drawable mPicture_9;
    private Drawable mPicture_10;
    private Drawable mPicture_11;
    private Drawable mPicture_12;
    private Drawable mPicture_13;
    private Drawable mPicture_14;
    private Drawable mPicture_15;
    private Drawable mPicture_16;
    private Drawable mPicture_17;
    private Drawable mPicture_18;
    private Drawable mPicture_19;
    private Drawable mPicture_20;
    private Drawable mPicture_21;
    private Drawable mPicture_22;
    private Drawable mPicture_23;
    private Drawable mPicture_24;
    private Drawable mPicture_25;
    private Drawable mPicture_26;
    private Drawable mPicture_27;
    private Drawable mPicture_28;

    MyApplication myApplication;
    RelativeLayout avg_layout;
    SnowView snow = null;
    ProgressBar music_run;
    private long exitTime = 0;
    private MediaPlayer playerbg,player;
    boolean musicbgRun=true;
    @Override
    public void setView() {
        setContentView(R.layout.mygirl_activity);
    }

    @Override
    public void initView() {
        myApplication= (MyApplication) getApplication();
        myApplication.setRun(false);
        start = (ImageView) findViewById(R.id.start);
        stop = (ImageView) findViewById(R.id.stop);
        music_stop= (ImageView) findViewById(R.id.music_stop);
        music_run= (ProgressBar) findViewById(R.id.music_run);
        imageView_pic = (ImageView) findViewById(R.id.imageView_pic);
        textView_desc = (TextView) findViewById(R.id.textView_desc);
        avg_layout= (RelativeLayout) findViewById(R.id.avg_layout);
        mygirl_layout=  findViewById(R.id.mygirl_layout);
        mygirl_layout .getBackground().setAlpha(166);

        //MediaPlayer的初始化
        playerbg = MediaPlayer.create(this, R.raw.youhebuke);
        player = MediaPlayer.create(this, R.raw.iloveyou);
        playerbg.start();//开始播放
        playerbg.setLooping(true);//设置循环播放

        // 获得雪花视图,并加载雪花图片到内存
        snow = (SnowView) findViewById(R.id.snow);
        snow.LoadSnowImage();

        // 获取当前屏幕的高和宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        snow.SetView(dm.heightPixels, dm.widthPixels);
        // 更新当前雪花
        update();

        init();
       clickLayout();

    }

    private void clickLayout() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
                imageView_pic.setVisibility(View.VISIBLE);
                textView_desc.setVisibility(View.VISIBLE);
                music_run.setVisibility(View.VISIBLE);
                music_stop.setVisibility(View.GONE);

                playerbg.pause();//背景音乐暂停
                player.start();

                player.setLooping(true);//设置循环播放
                new Thread(){
                    @Override
                    public void run() {
                        Message msg=new Message();
                        msg.what=1;
                        handler.sendMessageDelayed(msg, 3000);
                    }
                }.start();
                /**
                 * 界面刚开始显示的内容
                 */
                imageView_pic.setImageDrawable(first);
                textView_desc.setText("准备好了吗？"+"\n "+"    带你一起认识我心中"+"\n "+"             不一样的她...");
                imageView_pic.startAnimation(mFadeIn);
                myApplication.setRun(true);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.GONE);
                imageView_pic.setVisibility(View.GONE);
                textView_desc.setVisibility(View.GONE);
                if (musicbgRun){
                    music_run.setVisibility(View.VISIBLE);
                    music_stop.setVisibility(View.GONE);
                    playerbg.start();//背景音乐继续播放
                }else {
                    music_run.setVisibility(View.GONE);
                    music_stop.setVisibility(View.VISIBLE);
                }
                player.pause();
                player.seekTo(0);

                myApplication.setRun(false);
            }
        });
        avg_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myApplication.isRun()==true){
                    stop.setVisibility(View.VISIBLE);
                    new Thread(){
                        @Override
                        public void run() {
                            Message msg=new Message();
                            msg.what=1;
                            handler.sendMessageDelayed(msg, 3000);
                        }
                    }.start();
                }
            }
        });
        music_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_run.setVisibility(View.VISIBLE);
                music_stop.setVisibility(View.GONE);

                    playerbg.start();

                musicbgRun=true;
            }
        });
        music_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_run.setVisibility(View.GONE);
                music_stop.setVisibility(View.VISIBLE);

                playerbg.pause();
                playerbg.seekTo(0);
                if (myApplication.isRun()){
                    player.pause();
                    player.seekTo(0);
                }
                musicbgRun=false;
            }
        });
    }

    private void init() {
        initAnim();
        initPicture();

    }



    /**
     * 初始化动画
     */
    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setDuration(1000);
        mFadeInScale = AnimationUtils.loadAnimation(this,R.anim.welcome_fade_in_scale);
        mFadeInScale.setDuration(6000);
        mFadeOut = AnimationUtils.loadAnimation(this,R.anim.welcome_fade_out);
        mFadeOut.setDuration(1000);
      /*  rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        rotate.setDuration(1000);*/
    }

    /**
     * 初始化图片
     */

    private void initPicture() {
        first = getResources().getDrawable(R.drawable.liveshow_bg);
        mPicture_1 = getResources().getDrawable(R.drawable.ago1);
        mPicture_2 = getResources().getDrawable(R.drawable.ago2);
        mPicture_3 = getResources().getDrawable(R.drawable.ago3);
        mPicture_4 = getResources().getDrawable(R.drawable.ago4);
        mPicture_5 = getResources().getDrawable(R.drawable.login_bg);
        mPicture_6 = getResources().getDrawable(R.drawable.ago6);
        mPicture_7= getResources().getDrawable(R.drawable.ago7);
        mPicture_8 = getResources().getDrawable(R.drawable.now1);
        mPicture_9 = getResources().getDrawable(R.drawable.now2);
        mPicture_10 = getResources().getDrawable(R.drawable.now3);
        mPicture_11= getResources().getDrawable(R.drawable.now4);
        mPicture_12= getResources().getDrawable(R.drawable.now5);
        mPicture_13= getResources().getDrawable(R.drawable.now6);
        mPicture_14= getResources().getDrawable(R.drawable.now7);
        mPicture_15= getResources().getDrawable(R.drawable.now8);
        mPicture_16= getResources().getDrawable(R.drawable.now9);
        mPicture_17 = getResources().getDrawable(R.drawable.now10);
        mPicture_18= getResources().getDrawable(R.drawable.now11);
        mPicture_19 = getResources().getDrawable(R.drawable.now12);
        mPicture_20= getResources().getDrawable(R.drawable.now13);
        mPicture_21= getResources().getDrawable(R.drawable.now14);
        mPicture_22= getResources().getDrawable(R.drawable.now15);
        mPicture_23= getResources().getDrawable(R.drawable.now16);
        mPicture_24= getResources().getDrawable(R.drawable.now17);
        mPicture_25= getResources().getDrawable(R.drawable.ago8);
        mPicture_26= getResources().getDrawable(R.drawable.ago9);
        mPicture_27= getResources().getDrawable(R.drawable.ago10);
        mPicture_28= getResources().getDrawable(R.drawable.ago11);

    }


    /**
     * 监听事件
     */
    public void setListener() {
        /**
         * 动画切换原理:开始时是用第一个渐现动画,当第一个动画结束时开始第二个放大动画,当第二个动画结束时调用第三个渐隐动画,
         * 第三个动画结束时修改显示的内容并且重新调用第一个动画,从而达到循环效果
         */
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                imageView_pic.startAnimation(mFadeInScale);
            }
        });
        mFadeInScale.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                imageView_pic.startAnimation(mFadeOut);
            }
        });
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                /**
                 * 这里其实还可以采用更多的方式来判断当前显示的是第几个,从而修改数据,
                 *
                 */
            if (myApplication.isRun()){
                if (myApplication.getPictureNum()==1) {
                    myApplication.setPictureNum(2);
                    textView_desc.setText("那时的她庄重...");
                    imageView_pic.setImageDrawable(mPicture_1);
                } else if (myApplication.getPictureNum()==2) {
                    myApplication.setPictureNum(3);
                    textView_desc.setText("可爱...");
                    imageView_pic.setImageDrawable(mPicture_3);
                } else if (myApplication.getPictureNum()==3) {
                    myApplication.setPictureNum(4);
                    textView_desc.setText("单纯...");
                    imageView_pic.setImageDrawable(mPicture_2);
                }
                else if (myApplication.getPictureNum()==4) {
                    myApplication.setPictureNum(5);
                    textView_desc.setText("美丽...");
                    imageView_pic.setImageDrawable(mPicture_5);
                } else if (myApplication.getPictureNum()==5) {
                    myApplication.setPictureNum(6);
                    textView_desc.setText("她会时不时卖萌...");
                    imageView_pic.setImageDrawable(mPicture_6);
                } else if (myApplication.getPictureNum()==6) {
                    myApplication.setPictureNum(7);
                    textView_desc.setText("时不时淘气...");
                    imageView_pic.setImageDrawable(mPicture_28);
                }else if (myApplication.getPictureNum()==7) {
                    myApplication.setPictureNum(8);
                    textView_desc.setText("她也曾拥有梦想...");
                    imageView_pic.setImageDrawable(mPicture_26);
                }else if (myApplication.getPictureNum()==8) {
                    myApplication.setPictureNum(9);
                    textView_desc.setText("去蓝天翱翔...");
                    imageView_pic.setImageDrawable(mPicture_7);
                }else if (myApplication.getPictureNum()==9) {
                    myApplication.setPictureNum(10);
                    textView_desc.setText("去山水之间游荡...");
                    imageView_pic.setImageDrawable(mPicture_25);
                }else if (myApplication.getPictureNum()==10) {
                    myApplication.setPictureNum(11);
                    textView_desc.setText("成为万人瞩目的女神...");
                    imageView_pic.setImageDrawable(mPicture_4);
                }else if (myApplication.getPictureNum()==11) {
                    myApplication.setPictureNum(1);
                    textView_desc.setText("在朦胧之间"+"/n"+"   时间却已悄悄的流逝...");
                    imageView_pic.setImageDrawable(mPicture_27);
                }
                imageView_pic.startAnimation(mFadeIn);
            }

            }
        });

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what==1){
              stop.setVisibility(View.GONE);
            }else{
                Toast.makeText(getApplicationContext(),
                        "系统故障", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /*
	 * 负责做界面更新工作 ，实现下雪
	 */
    private RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            //snow.addRandomSnow();
            snow.invalidate();
            sleep(100);
        }
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    /**
     * Handles the basic update loop, checking to see if we are in the running
     * state, determining if a move should be made, updating the snake's
     * location.
     */
    public void update() {
        snow.addRandomSnow();
        mRedrawHandler.sleep(600);
    }


    /*--------按两次返回键退出程序--------*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
        {

            if((System.currentTimeMillis()-exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
