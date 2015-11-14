package com.example.telactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME="device_db";//数据库名
	private final static int DATABASE_VERSION = 1;
	public DBHelper(Context context)
	{
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void createDBtable(String sql){
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL(sql);
	}

	public Cursor select(String TABLE_NAME)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}
	public boolean select(String TABLE_NAME,String DEVICE_NAME,String value) {
	    boolean result = false ;
	    Cursor cursor = null ;
	    SQLiteDatabase db=this.getWritableDatabase();
	    try{
	        cursor = db.rawQuery( "select * from "+TABLE_NAME+" where "+DEVICE_NAME+" = ?"
	           , new String[]{ value} );
	        result = null != cursor && cursor.moveToFirst() ;
	    }catch (Exception e){
	        Log.e("db","checkColumnExists2..." + e.getMessage()) ;
	    }finally{
	        if(null != cursor && !cursor.isClosed()){
	            cursor.close() ;
	        }
	    }

	    return result ;
	}
	public long insert(String TABLE_NAME,ContentValues cv)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		long row=db.insert(TABLE_NAME, null, cv);
		return row;
	}
	
	public void delete(String TABLE_NAME,String MAJORKWY_NAME,String Value)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		String where=MAJORKWY_NAME+"=?";
		String[] whereValue={Value};
		db.delete(TABLE_NAME, where, whereValue);
	}
	public void deleteAll(String TABLE_NAME)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
	}
	//删除表
	public void deleteTable(String TABLE_NAME)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("drop table if exists " + TABLE_NAME);
	}
	//重命名表名
	public void renameTable(String TABLE_NAME,String NewTableName)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("alter table " + TABLE_NAME + " rename to " + NewTableName + ";");
	}
	
	public void update(String TABLE_NAME,String MAJORKWY_NAME,String Value,ContentValues cv)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		String where=MAJORKWY_NAME+"=?";
		String[] whereValue={Value};
		db.update(TABLE_NAME, cv, where, whereValue);
	}

}
