/*
 * Copyright (c) 2015. WeNoun™. TANSAN, Since 2014.
 * Code By Jey.
 */

package com.jey_dev.lib.notice.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.jey_dev.lib.notice.data.Noti;
import com.jey_dev.lib.notice.data.NotiDataAdapter;

import java.util.ArrayList;
import java.util.Collections;



public class DbOpenHelper {

	private static final String DATABASE_NAME = "db_noti.db";
	private static final int DATABASE_VERSION = 1;
	public static SQLiteDatabase mDB;
	private DatabaseHelper mDBHelper;
	private Context mCtx;

	private class DatabaseHelper extends SQLiteOpenHelper {

		// 생성자
		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		// 최초 DB를 만들때 한번만 호출된다.
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DataBases.CreateNotiDB._CREATE);

		}

		// 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DataBases.CreateNotiDB._TABLENAME);
			onCreate(db);
		}
	}

	public DbOpenHelper(Context context) {
		this.mCtx = context;
	}

	public DbOpenHelper open() throws SQLException {
		mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null,
				DATABASE_VERSION);
		mDB = mDBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDB.close();
	}

	// Insert DB
	public long insertColumn(int no,String title, String msg, String date) {
		ContentValues values = new ContentValues();
		values.put(DataBases.CreateNotiDB.NO, no);
		values.put(DataBases.CreateNotiDB.TITLE, title);
		values.put(DataBases.CreateNotiDB.MSG, msg);
		values.put(DataBases.CreateNotiDB.DATE, date);
		return mDB.insert(DataBases.CreateNotiDB._TABLENAME, null, values);
	}
    public long insertColumn(Noti data){
        return insertColumn(data.getNo(),data.getTitle(),data.getContentUrl(),data.getDate());
    }
	// Update DB
	public boolean updateColumn(long id,int no,String title, String msg, String date) {
		ContentValues values = new ContentValues();
        values.put(DataBases.CreateNotiDB.NO, no);
        values.put(DataBases.CreateNotiDB.TITLE, title);
        values.put(DataBases.CreateNotiDB.MSG, msg);
        values.put(DataBases.CreateNotiDB.DATE, date);
		return mDB.update(DataBases.CreateNotiDB._TABLENAME, values, "_id=" + id,
				null) > 0;
	}
    public boolean updateColumn(long id,Noti data){
        return updateColumn(id,data.getNo(),data.getTitle(),data.getContentUrl(),data.getDate());
    }

	// Delete ID
	public boolean deleteColumn(long id) {
		return mDB.delete(DataBases.CreateNotiDB._TABLENAME, "_id=" + id, null) > 0;
	}
	// Delete All
		public boolean deleteAllColumn() {
			return mDB.delete(DataBases.CreateNotiDB._TABLENAME,null, null) > 0;
		}
	// Delete Contact
	public boolean deleteColumn(int no) {
		return mDB.delete(DataBases.CreateNotiDB._TABLENAME, DataBases.CreateNotiDB.NO+"=" + no,
				null) > 0;
	}

	// Select All
	public Cursor getAllColumns() {
		return mDB.query(DataBases.CreateNotiDB._TABLENAME, null, null, null, null,
				null, null);
	}

	// ID 컬럼 얻어 오기
	public Cursor getColumn(long id) {
		Cursor c = mDB.query(DataBases.CreateNotiDB._TABLENAME, null, "_id=" + id,
				null, null, null, null);
		if (c != null && c.getCount() != 0)
			c.moveToFirst();
		return c;
	}
    // ID 컬럼 얻어 오기
    public Cursor getColumn(int no) {
        Cursor c = mDB.query(DataBases.CreateNotiDB._TABLENAME, null, "no=" + no,
                null, null, null, null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }
    // 번호로 이름 검색 하기 (rawQuery)
    public Cursor getMatchNo(int no) {
        //String str = number.replace("-", "");
        String qry="select * from "+DataBases.CreateNotiDB._TABLENAME+" where "+DataBases.CreateNotiDB.NO + "="+"'"
                + no + "'";
        //Log.d("DbOpenHelper",qry);
        //Log.d("DbOpenHelper",_qry);
        Cursor c = mDB.rawQuery(qry, null);
        //Log.d("DbOpenHelper","count : "+c.getCount());
        return c;
    }
	public long getNoToId(int no) {
		long result = -1;
		Cursor c = getMatchNo(no);
		//c.
		if (c.getCount() > 0) {
			c.moveToNext();
			result = (long)c.getInt(c.getColumnIndex("_id"));
			//c.get
			c.close();
		}
		return result;
	}


	public boolean isNo(int no) {
		try{
		Cursor c = getMatchNo(no);
		if (c.getCount() > 0)
			return true;
		else
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	public String getMsgFromNo(int no) {
		Cursor c = getMatchNo(no);
		String result = "";
		c.moveToNext();
		try{
			result = c.getString(c.getColumnIndex(DataBases.CreateNotiDB.MSG));
		}catch(Exception e){
			result="";
		}
		c.close();
		return result;
	}
	public String getDateFromNo(int no) {
		Cursor c = getMatchNo(no);
		String result = "";
		c.moveToNext();
		try{
			result = c.getString(c.getColumnIndex(DataBases.CreateNotiDB.DATE));
		}catch(Exception e){
			result="";
		}
		c.close();
		return result;
	}

	public boolean delNo(int no) {
		boolean result = false;
		Cursor c = getMatchNo(no);
		while (c.moveToNext()) {
			long id = getNoToId(no);
			result = deleteColumn(id);
		}
		return result;
	}
	public ArrayList<Noti> getFullNotiListArray() {
		Cursor c = getAllColumns();
		ArrayList<Noti> arrlist = new ArrayList<Noti>();
		while (c.moveToNext()) {
            int no=0;
			String title="Title", msg="Msg", date="15.01.01";
			title = c.getString(c.getColumnIndex(DataBases.CreateNotiDB.TITLE));
			msg = c.getString(c.getColumnIndex(DataBases.CreateNotiDB.MSG));
			date = c.getString(c.getColumnIndex(DataBases.CreateNotiDB.DATE));
			no = c.getInt(c.getColumnIndex(DataBases.CreateNotiDB.NO));
			arrlist.add(new Noti(no, title, msg, date));
		}
		c.close();
		Collections.sort(arrlist, NotiDataAdapter.ALPHA_COMPARATOR);
		return arrlist;
	}
    public ArrayList<Noti> getTitleNotiListArray() {
        Cursor c = getAllColumns();
        ArrayList<Noti> arrlist = new ArrayList<Noti>();
        while (c.moveToNext()) {
            int no=0;
            String title="Title", msg="Msg", date="15.01.01";
            title = c.getString(c.getColumnIndex(DataBases.CreateNotiDB.TITLE));
            date = c.getString(c.getColumnIndex(DataBases.CreateNotiDB.DATE));
            no = c.getInt(c.getColumnIndex(DataBases.CreateNotiDB.NO));
            arrlist.add(new Noti(no, title, msg, date));
        }
        c.close();
		Collections.sort(arrlist, NotiDataAdapter.ALPHA_COMPARATOR);
        return arrlist;
    }

}
