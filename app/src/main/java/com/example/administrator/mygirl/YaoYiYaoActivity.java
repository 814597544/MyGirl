package com.example.administrator.mygirl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.been.ShakeListenerUtils;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.controller.RequestType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.UMSsoHandler;
import com.umeng.socialize.controller.UMWXHandler;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.TencentWBSsoHandler;

import java.util.Random;

/**
 * Created by Administrator on 2015/3/9.
 */
public class YaoYiYaoActivity extends Activity{
    TextView title,title_right;
    LinearLayout title_return,finish;
    RelativeLayout yaolayout;
    private Vibrator vibrator;//震动
    /** 摇之前 遥之后 ,隐藏的 */
    private ImageView imView, imgbuttom;
    private TextView textgain;
    /**监听*/
    private ShakeListenerUtils shakeListener;
    private Button btnBack;

    private	int icon[] = { R.drawable.image_left, R.drawable.image_middle, R.drawable.image_right };

    private	int index = 0;

    private int randomC=0;

    private Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yaoyiyao_activity);
        init();
        shareData();

        title_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share",RequestType.SOCIAL);
                mController.openShare(YaoYiYaoActivity.this, false);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }
    public void init(){

        title= (TextView) findViewById(R.id.title);
        title.setText("摇一摇");
        title_return= (LinearLayout) findViewById(R.id.title_return);
        title_return.setVisibility(View.VISIBLE);
        title_right= (TextView) findViewById(R.id.title_right);
        title_right.setText("分享");
        finish= (LinearLayout) findViewById(R.id.finish);
        yaolayout= (RelativeLayout) findViewById(R.id.yaolayout);
        finish.setVisibility(View.VISIBLE);


        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        imView = (ImageView) findViewById(R.id.imgmiddle);
        imgbuttom= (ImageView) findViewById(R.id.imgbuttom);
        textgain = (TextView) findViewById(R.id.textgain);

        imView .getBackground().setAlpha(126);
        imgbuttom .getBackground().setAlpha(126);
        yaolayout .getBackground().setAlpha(126);

        random=new Random();
        shakeListener = new ShakeListenerUtils(this);
        shakeListener.setOnShake(onShake);
    }



    /** 重力感应 */
    private ShakeListenerUtils.OnShakeListener onShake = new ShakeListenerUtils.OnShakeListener() {

        @Override
        public void onShake() {
            textgain.setText("女神，惊喜马上就来！");
            startVibrator();
            shakeListener.stop();

            handler.sendEmptyMessageDelayed(1, 200);
            handler.sendEmptyMessageDelayed(2, 2000);

            randomC=random.nextInt(13);
            Log.e("--", "---"+randomC);

        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (index < icon.length - 1) {
                    index++;
                } else {
                    index = 0;
                }
                imView.setBackgroundResource(icon[index]);
                handler.sendEmptyMessageDelayed(1, 200);
            } else {
                imView.setBackgroundResource(R.drawable.lottery_result);
                handler.removeMessages(1);
                shakeListener.start();
                if(randomC==1){
                    textgain.setText(R.string.gain_1);
                    yaolayout.setBackgroundResource(R.drawable.ago3);
                }
               else if(randomC==2){
                    textgain.setText(R.string.gain_2);
                    yaolayout.setBackgroundResource(R.drawable.ago6);
                }
                else if(randomC==3){
                    textgain.setText(R.string.gain_3);
                    yaolayout.setBackgroundResource(R.drawable.login_bg);
                }
                else if(randomC==4){
                    textgain.setText(R.string.gain_4);
                    yaolayout.setBackgroundResource(R.drawable.ago4);
                }
                else if(randomC==5){
                    textgain.setText(R.string.gain_5);
                    yaolayout.setBackgroundResource(R.drawable.now5);
                }
                else if(randomC==6){
                    textgain.setText(R.string.gain_6);
                    yaolayout.setBackgroundResource(R.drawable.now13);
                }
                else if(randomC==7){
                    textgain.setText(R.string.gain_7);
                    yaolayout.setBackgroundResource(R.drawable.now15);
                }
                else if(randomC==8){
                    textgain.setText(R.string.gain_8);
                    yaolayout.setBackgroundResource(R.drawable.now17);
                }
                else if(randomC==9){
                    textgain.setText(R.string.gain_9);
                    yaolayout.setBackgroundResource(R.drawable.now11);
                }
                else if(randomC==10){
                    textgain.setText(R.string.gain_10);
                    yaolayout.setBackgroundResource(R.drawable.now16);
                }
                else if(randomC==11){
                    textgain.setText(R.string.gain_11);
                    yaolayout.setBackgroundResource(R.drawable.ago11);
                }
                else if(randomC==12){
                    textgain.setText(R.string.gain_12);
                    yaolayout.setBackgroundResource(R.drawable.now14);
                }else{
                    textgain.setText(R.string.gain_nothing);
                    yaolayout.setBackgroundResource(R.drawable.now12);

                }

            }
            imView .getBackground().setAlpha(126);
            imgbuttom .getBackground().setAlpha(126);
            yaolayout .getBackground().setAlpha(126);
        }

    };

    /**
     * 播放振动效果
     */
    public void startVibrator() {
        vibrator.vibrate(new long[] { 500, 300, 500, 300 }, -1);
    }



    /**
     * 一键分享
     */
    private void shareData() {
        //appkey在友盟上注册
        SocializeConstants.APPKEY = "554c11cd67e58e7366005da1";
        // 首先在您的Activity中添加如下成员变量
        final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share", RequestType.SOCIAL);

        // 设置分享内容
        mController.setShareContent("快和我一起来玩MyGirl吧，让你认识不一样的她！");


        // wx9c4bd02b3392e471是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appID = "wx";
        // 微信图文分享必须设置一个url
        String contentUrl = "http://user.qzone.qq.com/867011886?ptlang=2052";
        // 添加微信平台，参数1为当前Activity, 参数2为用户申请的AppID, 参数3为点击分享内容跳转到的目标url
        UMWXHandler wxHandler = mController.getConfig().supportWXPlatform(this,appID, contentUrl);

        //设置分享标题
        wxHandler.setWXTitle("MyGirl");
        // 支持微信朋友圈
        UMWXHandler circleHandler = mController.getConfig().supportWXCirclePlatform(this,appID, contentUrl) ;
        circleHandler.setCircleTitle("我在MyGirl摇一摇中获得收获：有志者自有千计万计，无志者只感千难万难！");
        circleHandler.setToCircle(true);

        //  参数1为当前Activity， 参数2为用户点击分享内容时跳转到的目标地址
        mController.getConfig().supportQQPlatform(this, "http://user.qzone.qq.com/867011886?ptlang=2052");

        mController.getConfig().setSsoHandler(new QZoneSsoHandler(this));

        //设置腾讯微博SSO handler
        mController.getConfig().setSsoHandler(new TencentWBSsoHandler());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share",RequestType.SOCIAL);

        /**使用SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode) ;
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }


}
