//package com.GetTotWrk.gradebook;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DBHelperCourseName extends SQLiteOpenHelper {
//
//	public static final String DATABASE_NAME = "course_database";
//	public static final String TABLE_NAME = "course_table";
//	public static final String COURSE = "course_name";
//	public static final String ID = "id";
//	public static final String ASSIGNMENT_TYPE = "assignment_type";
//	public static final String ASSIGNMENT_NUMBER = "assignment_number";
//	public static final String WEIGHT = "weight";
//	public static final String SCORE = "score";
//	public static final int VERSION = 1;
//
//	private final String createDB = "create table if not exists " + TABLE_NAME + " ( " 
//			+ COURSE +" text, "
//			+ ASSIGNMENT_TYPE +" text, "
//			+ ASSIGNMENT_NUMBER + "text, "
//			+ WEIGHT +" text, "
//			+ SCORE +" text, " 
//			+ ID +" text); ";
//	public DBHelperCourseName(Context context){
//		super(context, DATABASE_NAME, null, VERSION);
//	}
//
//	
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		db.execSQL(createDB);
//		
//		ContentValues cv = new ContentValues();
//		String Asssignment1 = "Homework";
//		String Asssignment2 = "Quiz";
//		String Asssignment3 = "Project";
//		String Asssignment4 = "Exam";
//		String Asssignment5 = "Lab";
//		String Asssignment6 = "Pre Lab";
//		String Asssignment7 = "Other";
//		
//		
//		String text1 = "COURSE 1";
//		cv.put(ASSIGNMENT_TYPE,Asssignment1);
//		cv.put(COURSE,text1);
//		db.insert(TABLE_NAME, null, cv);
//		
//		String text3 = "COURSE 2";
//		cv.put(COURSE,text3);
//		cv.put(ASSIGNMENT_TYPE,Asssignment1);
//		db.insert(TABLE_NAME, null, cv);
//
//		
//		String text5 = "COURSE 3";
//		String text6 = "3";
//		cv.put(COURSE,text5);
//		cv.put(ID, text6);
//		cv.put(ASSIGNMENT_TYPE,Asssignment1);
//
//		db.insert(TABLE_NAME, null, cv);
//	
//		String text7 = "COURSE 4";
//		cv.put(COURSE,text7);
//		cv.put(ASSIGNMENT_TYPE,Asssignment1);
//
//		db.insert(TABLE_NAME, null, cv);
//		
//		
//		String text9 = "COURSE 5";
//		cv.put(COURSE,text9);
//		db.insert(TABLE_NAME, null, cv);
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
//
//		db.execSQL("drop table " + TABLE_NAME);
//	}
//}
