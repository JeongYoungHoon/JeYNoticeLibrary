/*
 * Copyright (c) 2015. WeNoun™. TANSAN, Since 2014.
 * Code By Jey.
 */

package com.jey_dev.lib.notice.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.jey_dev.lib.notice.JeYNoticeConst;
import com.jey_dev.lib.notice.R;
import com.jey_dev.lib.notice.fragment.Detail;
import com.jey_dev.lib.notice.fragment.List;
import com.jey_dev.lib.notice.fragment.RealtimeList;


/**
 * Created by SnakeJey on 2015-01-27.
 */
public class JeYNoticeActivity extends FragmentActivity {
    private Context ctx=null;
//    private ScrollView scrollView=null;
    private String appName="";
    private static int notiNo=-1;
    private boolean isRealtime=false;
    private FragmentManager fragmentManager=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=this;
        if(getIntent().hasExtra(JeYNoticeConst.Key.APP_NAME)){
            appName=getIntent().getExtras().getString(JeYNoticeConst.Key.APP_NAME);
        }else{
            finish();
        }
        if(getIntent().hasExtra(JeYNoticeConst.Key.IS_REALTIME)){
            isRealtime=getIntent().getExtras().getBoolean(JeYNoticeConst.Key.IS_REALTIME);
        }
        setContentView(R.layout.notice_layout_notice);
        fragmentManager = getSupportFragmentManager();
        if(isRealtime)
            setFragment(new RealtimeList(),getString(R.string.notice));
        else
            setFragment(new List(),getString(R.string.notice));
//        scrollView=(ScrollView)findViewById(R.id.notice_sv);
//        showDialog();


    }
    public String getAppName(){
        return this.appName;
    }
    public int getNotiNo(){
        return this.notiNo;
    }
    public void setFragment(Fragment fragment){
        setFragment(fragment,"");
    }
    public void setFragment(Fragment fragment,String title){
        setTitle(title);
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.notice_fragment, fragment);
        fragmentTransaction.commit();
    }
    public void addFragment(Fragment fragment, String title){
        setTitle(title);
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.notice_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void addFragment(Fragment fragment){
        addFragment(fragment,"");
    }
    public void showDetail(String title,int idx){
        notiNo=idx;
        addFragment(new Detail(),title);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void goMain(View v){
        onBackPressed();
    }

    public void setTitle(String title){
        ((TextView)findViewById(R.id.notice_title)).setText(title);
    }
    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount()>0) {
            fragmentManager.popBackStack();
            try {
                setTitle(getString(R.string.notice));
                notiNo=-1;
            }catch(Exception e){}
        }else
            super.onBackPressed();
    }
    private Dialog dialog=null;
    public Dialog getProgressDialog() {
        Dialog dialog = new Dialog(this, R.style.notice_dialog);
        dialog.setContentView(R.layout.notice_dialog_progress);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
    public void dismissDialog(){
        if(null!=dialog)
            dialog.dismiss();
    }
    public void showDialog(){
        dialog=getProgressDialog();
        dialog.show();
    }

}
