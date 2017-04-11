package com.jey_dev.lib.notice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jey_dev.lib.notice.R;
import com.jey_dev.lib.notice.db.DB;


/**
 * Created by jeyhoon on 16. 3. 3..
 */
public class Detail extends BaseFragment {
    private static View root=null;
    private WebView webView=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try{
            root=inflater.inflate(R.layout.notice_fragment_detail,container,false);
        }catch(Exception e){}
        if(parent.getNotiNo()!=-1){
//            ((TextView)root.findViewById(R.id.detail_msg)).setText(DB.Notice.getMsgFromNo(ctx,parent.getNotiNo()));
//            ((TextView)root.findViewById(R.id.detail_date)).setText(DB.Notice.getDateFromNo(ctx,parent.getNotiNo()));
            webView=(WebView)root.findViewById(R.id.notice_webview);
            webView.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
            webView.loadUrl(DB.Notice.getMsgFromNo(ctx,parent.getNotiNo()));
        }
        return root;
    }
}
