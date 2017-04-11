/*
 * Copyright (c) 2015. WeNoun™. TANSAN, Since 2014.
 * Code By Jey.
 */

package com.jey_dev.lib.notice.db;

import android.provider.BaseColumns;

public final class DataBases {
    
    public static final class CreateNotiDB implements BaseColumns{
    	public static final String NO = "no";
        public static final String TITLE = "title";
        public static final String MSG = "msg";
        public static final String DATE="date";
        public static final String _TABLENAME = "noti_list";
        public static final String _CREATE = 
            "create table "+_TABLENAME+"("
                    +_ID+" integer primary key autoincrement, "
                    +NO+" integer not null , "
                    +TITLE+" text not null , "
                    +MSG+" text not null , "
                    +DATE+" text not null );";
    }
}
