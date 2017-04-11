package com.jey_dev.lib.notice.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jey_dev.lib.notice.R;
import com.jey_dev.lib.notice.data.Noti;
import com.jey_dev.lib.notice.data.NotiDataAdapter;
import com.jey_dev.lib.notice.db.DB;
import com.jey_dev.lib.notice.utils.GetJSONThread;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by jeyhoon on 16. 3. 3..
 */
public class RealtimeList extends BaseFragment {

    private static View root;
    private ListView listView;
//    protected ArrayList<Noti> arrlist = new ArrayList<Noti>();
    private NotiDataAdapter Adapter=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try{
            root=inflater.inflate(R.layout.notice_fragment_list,container,false);
        }catch(Exception e){

        }
        listView=(ListView)root.findViewById(R.id.list_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> p, View view, int position, long id) {
                if(Adapter.getCount()>0){
                    parent.showDetail(Adapter.getItem(position).getTitle(),Adapter.getItem(position).getNo());
                }
            }
        });
        parent.showDialog();
        getNotice();
//        Handler h = new Handler();
//        h.postDelayed(new versioncheck_hendler(), 0);
        return root;
    }

    public void getNotice() {
        String tmp = parent.getAppName();
        String url = "http://jey-dev.com/notice/php/get_notice.php?target_app=" + tmp+"&target_os=1";
        // Toast.makeText(SplashActivity.this, "ss", 0).show();
        // 스레드 객체를 생성해서 작업을 시킨다.
        GetJSONThread thread = new GetJSONThread(new Handler() {
            public void handleMessage(android.os.Message msg) {
                // 전송결과를 메시지로 받는다
                switch (msg.what) {
                    case 0: // 성공
                        String result1 = (String) msg.obj;
                        parent.dismissDialog();
                        break;
                    case 1: // 실패
                        String result2 = (String) msg.obj;
                        parent.dismissDialog();
                        break;
                    case 2: // getJSON성공

                        String jsonStr1 = (String) msg.obj;
//                        jsonStr1 = jsonStr1.replace("]", "");
//                        jsonStr1 = jsonStr1.replace("[", "");

                        // Toast.makeText(SplashActivity.this, jsonStr1, 0).show();
                        try {

                            int result;
                            JSONObject jsonObj = new JSONObject(jsonStr1);
                            // JSONArray 객체 얻어오기
                            result = jsonObj.getInt("result");
                            Log.d("NotiAct", jsonObj.toString() + "");
                            ArrayList<Noti> arrlist = new ArrayList<Noti>();
                            JSONArray dataArray=jsonObj.getJSONArray("data");
                            //JSONArray jsonArray=new JSONArray(jsonStr1);
                            if (result==1) {
                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject obj = dataArray.getJSONObject(i);
                                    int no;
                                    String title, contentUrl, date;
                                    no = obj.getInt("idx");
                                    title = obj.getString("title");
                                    contentUrl = obj.getString("contentUrl");
                                    date = obj.getString("date");
                                    arrlist.add(new Noti(no, title, contentUrl, date));
                                }
                                Collections.sort(arrlist, NotiDataAdapter.ALPHA_COMPARATOR);
                                DB.Notice.insertSyncNotiArray(ctx, arrlist);
                                if(null!=Adapter) Adapter.clear();
                                Adapter=new NotiDataAdapter(ctx,arrlist);
                                listView.setAdapter(Adapter);
//                                scrollView.addView(DB.Notice.getView(ctx));
                                //Adapter=new NotiDataAdapter(ctx,arrlist);
                                //listView.setAdapter(Adapter);
                            } else {


                            }

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        parent.dismissDialog();
                        break;
                    case 3: // getJSON실패
                        String jsonStr2 = (String) msg.obj;
                        parent.dismissDialog();
                        break;
                }
            }
        }, null, url);
        thread.start();
    }

    class versioncheck_hendler implements Runnable {
        public void run() {
            /*dialog = new ProgressDialog(SplashActivity.this);
            dialog.setTitle("버전체크중...");
			dialog.setMessage("잠시만 기다려 주세요...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);*/

            //splashIv.startAnimation(AnimationUtils.loadAnimation(ctx,R.anim.rear_alpha));
            //splashIv.setImageResource(R.drawable.splash);
            /*logoIv.setVisibility(View.GONE);
            Animation frontAni=AnimationUtils.loadAnimation(ctx,R.anim.front_alpha);
            splashIv.startAnimation(frontAni);*/
//            dialog = getProgressDialog();
//            dialog.show();
            new Thread(new Runnable() {
                public void run() {
                    // TODO Auto-generated method stub

//
//                    try {
//                        Thread.sleep(00);
//                    } catch (Throwable ex) {
//                        ex.printStackTrace();
//                    }
                    getNotice();
                    //CheckVersion();


                }
            }).start();
            // Toast.makeText(SplashActivity.this, InLogin[0]+"::"+InLogin[1],
            // 0).show();

        }
    }

}
