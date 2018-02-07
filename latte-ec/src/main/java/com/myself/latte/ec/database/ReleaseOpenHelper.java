package com.myself.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Kamh on 2018/2/7.
 */

public class ReleaseOpenHelper extends DaoMaster.OpenHelper{
    //必须先写Entity（UserProfile），才能写这个
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
