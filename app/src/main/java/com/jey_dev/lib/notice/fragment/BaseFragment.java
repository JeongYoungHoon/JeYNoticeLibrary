package com.jey_dev.lib.notice.fragment;

//import android.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jey_dev.lib.notice.activity.JeYNoticeActivity;


/**
 * Created by jeyhoon on 16. 3. 3..
 */
public class BaseFragment extends Fragment {
    protected Context ctx=null;
    protected JeYNoticeActivity parent=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=getActivity().getBaseContext();
        parent=(JeYNoticeActivity)getActivity();
    }
}
