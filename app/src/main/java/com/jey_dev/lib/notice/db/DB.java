/*
 * Copyright (c) 2015. WeNoun™. TANSAN, Since 2014.
 * Code By Jey.
 */

package com.jey_dev.lib.notice.db;

import android.content.Context;
import android.util.Log;

import com.jey_dev.lib.notice.data.Noti;
import com.jey_dev.lib.notice.data.NotiDataAdapter;

import java.util.ArrayList;


public class DB {

    public DB() {
        // TODO Auto-generated constructor stub
    }

    public static class Notice {
        public static long insertNoti(Context ctx, Noti data) {
            long result = -1;
            int no = data.getNo();
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            boolean isNo = mDbOpenHelper.isNo(no);
            if (isNo) {
                Log.d("DB", "isUserId True");
                long id = mDbOpenHelper.getNoToId(no);
                mDbOpenHelper.updateColumn(id, data);
                result = id;
            } else {
                Log.d("DB", "isUserId False");
                result = mDbOpenHelper.insertColumn(data);
            }
            mDbOpenHelper.close();
            return result;
        }

        public static boolean insertSyncNotiArray(Context ctx,
                                               ArrayList<Noti> datalist) {
            boolean isNew=false;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            for (int i = 0; i < datalist.size(); i++) {
                int no = datalist.get(i).getNo();
                boolean isNo = mDbOpenHelper.isNo(no);
                if (isNo) {
                    Log.d("DB", "isUserId True - " + datalist.get(i).getNo());
                    long id = mDbOpenHelper.getNoToId(no);
                    mDbOpenHelper.updateColumn(id, datalist.get(i));
                } else {
                    Log.d("DB", "isUserId False");
                    mDbOpenHelper.insertColumn(datalist.get(i));
                    isNew=true;
                }
            }
            mDbOpenHelper.close();
            return isNew;
        }

        public static void insertNotiArray(Context ctx,
                                           ArrayList<Noti> datalist) {
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            for (int i = 0; i < datalist.size(); i++) {
                int no = datalist.get(i).getNo();
                boolean isNo = mDbOpenHelper.isNo(no);
                if (isNo) {
                } else {
                    Log.d("DB", "isUserId False");
                    mDbOpenHelper.insertColumn(datalist.get(i));
                }
            }
            mDbOpenHelper.close();
        }

        public static ArrayList<Noti> getFullNotiListArray(Context ctx) {
            ArrayList<Noti> strlist;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            strlist = mDbOpenHelper.getFullNotiListArray();
            mDbOpenHelper.close();
            return strlist;
        }

        public static ArrayList<Noti> getTitleNotiListArray(Context ctx) {
            ArrayList<Noti> strlist;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            strlist = mDbOpenHelper.getTitleNotiListArray();
            mDbOpenHelper.close();
            return strlist;
        }

        public static String getMsgFromNo(Context ctx, int no) {
            String data = null;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            data = mDbOpenHelper.getMsgFromNo(no);
            mDbOpenHelper.close();
            return data;
        }
        public static String getDateFromNo(Context ctx, int no) {
            String data = null;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            data = mDbOpenHelper.getDateFromNo(no);
            mDbOpenHelper.close();
            return data;
        }

        public static boolean isNo(Context ctx, int no) {
            boolean result = false;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            result = mDbOpenHelper.isNo(no);
            mDbOpenHelper.close();
            return result;
        }

        public static long getNoToId(Context ctx, int no) {
            long result = 0;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            result = mDbOpenHelper.getNoToId(no);
            mDbOpenHelper.close();
            return result;
        }

        public static boolean delListNo(Context ctx, int no) {
            boolean result = false;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            long id = mDbOpenHelper.getNoToId(no);
            result = mDbOpenHelper.deleteColumn(id);
            mDbOpenHelper.close();
            return result;
        }

        public static boolean delList(Context ctx, long id) {
            boolean result = false;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            result = mDbOpenHelper.deleteColumn(id);
            mDbOpenHelper.close();
            return result;
        }

        public static boolean delAll(Context ctx) {
            boolean result = false;
            DbOpenHelper mDbOpenHelper;
            mDbOpenHelper = new DbOpenHelper(ctx);
            mDbOpenHelper.open();
            result = mDbOpenHelper.deleteAllColumn();
            mDbOpenHelper.close();
            return result;
        }

//        private static View getChildView(Context ctx, Noti data) {
//            final View view;
//            LayoutInflater mInflater;
//            mInflater = (LayoutInflater) ctx
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = mInflater.inflate(R.layout.item_notice, null);
//            ((TextView) view.findViewById(R.id.noti_title_title_tv)).setText(data.getTitle());
//            ((TextView) view.findViewById(R.id.noti_title_date_tv)).setText(data.getDate());
////            ((TextView) view.findViewById(R.id.noti_msg_msg_tv)).setText(data.getMsg());
//            (view.findViewById(R.id.noti_title_layout)).setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    View msgView = view.findViewById(R.id.noti_msg_layout);
//                    ImageView btn = (ImageView) view.findViewById(R.id.noti_title_down_btn);
//                    if (msgView.getVisibility() == View.VISIBLE) {
////                        msgView.setVisibility(View.GONE);
////                        btn.setImageResource(R.drawable.btn_down);
//                    } else {
////                        msgView.setVisibility(View.VISIBLE);
////                        btn.setImageResource(R.drawable.btn_up);
//                    }
//                }
//            });
//            return view;
//        }

        public static NotiDataAdapter getFullView(Context ctx) {
            ArrayList<Noti> arrlist=getFullNotiListArray(ctx);
            return new NotiDataAdapter(ctx,arrlist);
        }
        public static NotiDataAdapter getTitleView(Context ctx) {
            ArrayList<Noti> arrlist=getTitleNotiListArray(ctx);
            return new NotiDataAdapter(ctx,arrlist);
        }
//        public static View getView(Context ctx) {
//            LinearLayout root = new LinearLayout(ctx);
//            root.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//            root.setOrientation(LinearLayout.VERTICAL);
//            ArrayList<Noti> arrlist=getFullNotiListArray(ctx);
//            for(int i=arrlist.size()-1; i>=0;i--){
//                root.addView(getChildView(ctx,arrlist.get(i)));
//            }
//
//            return root;
//        }
//        public static View getView(Context ctx, ArrayList<Noti> arrlist) {
//            LinearLayout root = new LinearLayout(ctx);
//            root.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//            root.setOrientation(LinearLayout.VERTICAL);
//            //ArrayList<Noti> arrlist=getFullNotiListArray(ctx);
//            for(int i=arrlist.size()-1; i>=0;i--){
//                root.addView(getChildView(ctx,arrlist.get(i)));
//            }
//
//            return root;
//        }
    }

}
