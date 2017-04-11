package com.jey_dev.lib.notice;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.jey_dev.lib.notice.activity.JeYNoticeActivity;
import com.jey_dev.lib.notice.service.JeYNoticeService;


/**
 * Created by jeyhoon on 16. 2. 26..
 */
public class JeYNotice {
    private Context ctx=null;
    private String appName="";
    public JeYNotice(Context ctx) {
        this.ctx = ctx;
    }

    public JeYNotice(Context ctx, String appName) {
        this.ctx = ctx;
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public JeYNotice setAppName(String appName) {
        this.appName = appName;
        return this;
    }
    public void startService(){
        if(ctx.checkCallingOrSelfPermission(Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED) {
            if (appName.equals("") || appName.length() <= 0) return;
            else
                ctx.startService(new Intent(ctx, JeYNoticeService.class).putExtra(JeYNoticeConst.Key.APP_NAME, appName));
        }
    }
    public void startActivity(){
        if(ctx.checkCallingOrSelfPermission(Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED) {
            if (appName.equals("") || appName.length() <= 0) return;
            else
                ctx.startActivity(new Intent(ctx, JeYNoticeActivity.class).putExtra(JeYNoticeConst.Key.APP_NAME, appName)
                        .putExtra(JeYNoticeConst.Key.IS_REALTIME,false).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
    public void startRealtimeActivity(){
        if(ctx.checkCallingOrSelfPermission(Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED) {
            if (appName.equals("") || appName.length() <= 0) return;
            else
                ctx.startActivity(new Intent(ctx, JeYNoticeActivity.class).putExtra(JeYNoticeConst.Key.APP_NAME, appName)
                        .putExtra(JeYNoticeConst.Key.IS_REALTIME,true).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
    public static void startService(Context ctx,String appName){
        if(ctx.checkCallingOrSelfPermission(Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED) {
            if (appName.equals("") || appName.length() <= 0) return;
            else
                ctx.startService(new Intent(ctx, JeYNoticeService.class).putExtra(JeYNoticeConst.Key.APP_NAME, appName));
        }
    }
    public static void startActivity(Context ctx,String appName){
        if(ctx.checkCallingOrSelfPermission(Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED) {
            if (appName.equals("") || appName.length() <= 0) return;
            else
                ctx.startActivity(new Intent(ctx, JeYNoticeActivity.class).putExtra(JeYNoticeConst.Key.APP_NAME, appName)
                        .putExtra(JeYNoticeConst.Key.IS_REALTIME,false).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
    public static void startRealtimeActivity(Context ctx,String appName){
        if(ctx.checkCallingOrSelfPermission(Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED) {
            if (appName.equals("") || appName.length() <= 0) return;
            else
                ctx.startActivity(new Intent(ctx, JeYNoticeActivity.class).putExtra(JeYNoticeConst.Key.APP_NAME, appName)
                        .putExtra(JeYNoticeConst.Key.IS_REALTIME,true).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
