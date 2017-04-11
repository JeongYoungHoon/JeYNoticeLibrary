/*
 * Copyright (c) 2015. WeNoun™. TANSAN, Since 2014.
 * Code By Jey.
 */

package com.jey_dev.lib.notice.data;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jey_dev.lib.notice.R;

import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;



public class NotiDataAdapter extends ArrayAdapter<Noti> {
    // 레이아웃 XML을 읽어들이기 위한 객체
    private LayoutInflater mInflater;
    LinearLayout layout;
    private SparseArray<WeakReference<View>> viewArray;
    Context ctx;
    String bId = "";
    Button btn;
    String is, main, sub, id;
    int pos;
    View view = null;
    View[] vv;
    final ArrayList<View> vvv = new ArrayList<View>();

    public NotiDataAdapter(Context context, ArrayList<Noti> object) {

        // 상위 클래스의 초기화 과정
        // context, 0, 자료구조
        super(context, 0, object);
        this.viewArray = new SparseArray<WeakReference<View>>(object.size());
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ctx = context;
    }

    // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
    CustomViewHolder holder;

    // View view = null;
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        final Noti data = this.getItem(position);
        View view = null;
        if (v == null) {
            view = mInflater.inflate(R.layout.notice_item_notice, null);
            holder = new CustomViewHolder();
            // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
            holder.titleTv = (TextView) view.findViewById(R.id.noti_title_title_tv);
            holder.dateTv = (TextView) view.findViewById(R.id.noti_title_date_tv);
//            holder.msgTv = (TextView) view.findViewById(R.id.noti_msg_msg_tv);
//            holder.titleView = view.findViewById(R.id.noti_title_layout);
//            holder.msgView = view.findViewById(R.id.noti_msg_layout);
//            holder.btn = (ImageView) view.findViewById(R.id.noti_title_down_btn);
            view.setTag(holder);
        } else {
            view = v;
            holder = (CustomViewHolder) view.getTag();
        }
        try {
            holder.titleTv.setText(data.getTitle());
            holder.dateTv.setText(data.getDate());
            //holder.msgTv.setText(data.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    }

    public class CustomViewHolder {
        public TextView titleTv;
        public TextView dateTv;
//        public TextView msgTv;
//        public ImageView btn;
//        public View msgView;
//        public View titleView;
    }

    public static final Comparator<Noti> ALPHA_COMPARATOR = new Comparator<Noti>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(Noti object1, Noti object2) {
            return sCollator.compare(object2.getDate(), object1.getDate());
        }
    };

}
