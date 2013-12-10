//package com.GetTotWrk.gradebook;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//	public static final String DATABASE_NAME = "database";
//	public static final String TABLE_NAME = "data";
//	public static final String C_ID = "_id";
//	public static final String COURSE = "course_name";
//	public static final String ASSIGNMENT_TYPE = "assignment_type";
//	public static final String WEIGHT = "weight";
//	public static final String SCORE = "score";
//	public static final String QUANTITY = "quantitiy";
//	public static final int VERSION = 1;
//
//	private final String createDB = "create table if not exists " + TABLE_NAME + " ( "
//			+ C_ID + " integer primary key autoincrement, "
//			+ COURSE +" text, "
//			+ ASSIGNMENT_TYPE +" text, "
//			+ WEIGHT +" text, "
//			+ SCORE +" text, " 
//			+ QUANTITY +" text); ";
//	public DBHelper(Context context){
//		super(context, DATABASE_NAME, null, VERSION);
//	}
//
//	
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		db.execSQL(createDB);
//
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
//
//		db.execSQL("drop table " + TABLE_NAME);
//	}
//
//}
