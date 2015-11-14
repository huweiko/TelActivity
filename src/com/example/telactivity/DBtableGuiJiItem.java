package com.example.telactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBtableGuiJiItem extends DBHelper {

	public final static String DEVICE_TIME="device_mTime";//Ê±¼ä
	public final static String DEVICE_VALUE="device_mValue";//¼×È©Å¨¶È
	
	public DBtableGuiJiItem(Context context)
	{
		super(context);
	}
	public void createDBtable(String TABLE_NAME){
		SQLiteDatabase db=this.getWritableDatabase(); 
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='"+TABLE_NAME+"';";
		Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToNext()){
            int count = cursor.getInt(0);
            if(count>0){

            }else{
            	String sql0="Create table "+TABLE_NAME+"("+
        				DEVICE_TIME+" TEXT,"+
        				DEVICE_VALUE+" TEXT)";
        		super.createDBtable(sql0);
            }
         }

	}

	public Cursor select(String TABLE_NAME)
	{
		return super.select(TABLE_NAME);
	}
	
	public long insert(String TABLE_NAME,DeviceGuiJi mDeviceGuiJi)
	{
		ContentValues cv=new ContentValues(); 
		cv.put(DEVICE_TIME, mDeviceGuiJi.getmTime());
		cv.put(DEVICE_VALUE, mDeviceGuiJi.getmValue());
		
		return super.insert(TABLE_NAME, cv);
	}
	
	public void delete(String TABLE_NAME,String name)
	{
		super.delete(TABLE_NAME, DEVICE_TIME, name);
	}
	
	public void update(String TABLE_NAME,DeviceGuiJi mDeviceGuiJi,String name)
	{
		ContentValues cv=new ContentValues(); 
		cv.put(DEVICE_TIME, mDeviceGuiJi.getmTime());
		cv.put(DEVICE_VALUE, mDeviceGuiJi.getmValue());
		super.update(TABLE_NAME, DEVICE_TIME, name, cv);
	}
}
