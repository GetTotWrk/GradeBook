package com.GetTotWrk.gradebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
public class Course {
	private static final String TAG = "COURSES";
	private CourseOneData dbCourseOne;
	private SQLiteDatabase courseone;





	public CourseOneData dbhelper(){
		return dbCourseOne;
	}

	public SQLiteDatabase coursedb(){
		return courseone;
	}


	public final FragmentActivity fragact;
	public static final String DATABASE_NAME = "course_database2";
	public static final String TABLE_NAME = "course_table";
	public static final String TABLE_NAME2 = "course_table2";
	public static final String COURSE = "course_name";
	public static final String C_ID = "_id";
	public static final String ASSIGNMENT_TYPE = "assignment_type";
	public static final String WEIGHT = "weight";
	public static final String RAW_SCORE = "raw_score";
	public static final String SCORE = "score";
	public static final String NUMBER = "number";
	public static final int VERSION = 1;

	private final static String createDB = "create table if not exists " + TABLE_NAME + " ( "
			+ C_ID + " integer primary key autoincrement, "
			+ COURSE +" real, "
			+ NUMBER + " real, "
			+ ASSIGNMENT_TYPE +" text, "
			+ WEIGHT +" real, "
			+ RAW_SCORE +" real, "
			+ SCORE +" real); ";
	private final static String createDB2 = "create table if not exists " + TABLE_NAME2 + " ( "
			+ C_ID + " integer primary key autoincrement, "
			+ COURSE +" text); ";

	public Cursor fetchAllEntries() {
		Log.w(TAG, "FETCH ALL ENTRIES");

		String[] column = {C_ID, ASSIGNMENT_TYPE, COURSE,RAW_SCORE, SCORE, WEIGHT};	//LOMZ

		Cursor cursor = courseone.query(TABLE_NAME, column, null, null, null, null, null); 
		String testString = Integer.toString(cursor.getCount());

		if (cursor != null) {
			cursor.moveToFirst();
			Log.w(TAG, testString);

		}
		return cursor;
	}
	public Course(FragmentActivity fragact) {
		this.fragact = fragact;
	}
	private static class CourseOneData extends SQLiteOpenHelper {



		CourseOneData(Context context){
			super(context, DATABASE_NAME, null, VERSION);
		}


		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.w(TAG, "CREATE TABLE");
			db.execSQL(createDB);
			db.execSQL(createDB2);


		}
		@Override

		public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

			db.execSQL("drop table " + TABLE_NAME);
			db.execSQL("drop table " + TABLE_NAME2);
		}
	}

	public Course open() throws SQLException {
		try{
			dbCourseOne = new CourseOneData(fragact);
			courseone = dbCourseOne.getWritableDatabase();

			String[] column = {C_ID, ASSIGNMENT_TYPE, COURSE,RAW_SCORE,SCORE, WEIGHT};	//LOMZ

			Cursor cursor = courseone.query(TABLE_NAME, column, null, null, null, null, null); 

			if(cursor!=null && cursor.getCount()==0){

				//createcourseassignment("Course 1","Test",.30,2);
				//createcourseassignment("Course 1","Homework",.2, 5);
				//	createcourseassignment("Course 1","Lab", .25,5);
				//createcourseassignment("Course 1","Paper",0.25,2);
			}
			String[] column2  = {C_ID,COURSE};	//LOMZ
			Log.w(TAG, "Finished with table 1");
			Cursor cursor2 = courseone.query(TABLE_NAME2, column2, null, null, null, null, null); 
			Log.w(TAG, "Found table 2");

			if(cursor2!=null && cursor2.getCount()==0){
				Log.w(TAG, "Insterting Values into table2 ");

				insertSomeCourses();
			}

		}
		catch(Exception ex){
			Log.w(TAG, "exception caught");

		}
		return this;

	}



	public long createAssignment(String assignmentType,int course, Double weight, int assnum) {
		Log.w(TAG, "STARTED INSERT VALUES");
		Log.w(TAG, course + " " +assignmentType + " " + weight);


		ContentValues initialValues = new ContentValues();
		initialValues.put(COURSE, course);
		initialValues.put(ASSIGNMENT_TYPE, assignmentType);
		initialValues.put(WEIGHT, weight);
		initialValues.put(NUMBER, assnum);

		return courseone.insert(TABLE_NAME, null, initialValues);

	}


	public long createCourse(String course) {
		Log.w(TAG, "STARTED INSERT VALUES");
		Log.w(TAG, course);


		ContentValues initialValues = new ContentValues();
		initialValues.put(COURSE, course);

		return courseone.insert(TABLE_NAME2, null, initialValues);

	}
	public void insertSomeCourses() {

		createCourse("Course 1");
		createCourse("Course 2");
		createCourse("Course 3");
		createCourse("Course 4");
	}
	public void insertSomeAssignments() {

		createAssignment("Test",1,.30,2);
		createAssignment("Homework",2,.2, 5);
		createAssignment("Lab",1,.25,5);
		createAssignment("Paper",2,0.25,2);
	}



	public Cursor fetchAssignmentsbyName(String inputText) throws SQLException {
		Log.w(TAG, inputText);
		int ID = Integer.parseInt(inputText);
		Cursor mCursor = null;
		String[] column = {C_ID, ASSIGNMENT_TYPE,COURSE,RAW_SCORE,SCORE, WEIGHT};	//LOMZ

		if (inputText == null  ||  inputText.length () == 0)  {
			mCursor = courseone.query(TABLE_NAME, column,null, null, null, null, null);

		}
		else {
			mCursor = courseone.query(true, TABLE_NAME, column, COURSE + " like '%" + ID + "%'", null,null, null, null, null);
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	public void courseNameUpdate(int num, String course) {
		// TODO Auto-generated method stub
		Log.w(TAG, "START UPDATE CourseName");

		ContentValues initialValues = new ContentValues();
		initialValues.put(COURSE, course);
        String string =String.valueOf(num);

		dbCourseOne = new CourseOneData(fragact);
		courseone = dbCourseOne.getWritableDatabase();
 
    
		

		courseone.update(TABLE_NAME2, initialValues, C_ID + "=?", new String[] { string });
	}

	public void tableupdate(Cursor cursor,Double rawscore, Double totalscore) {
		// TODO Auto-generated method stub
		Log.w(TAG, "STARTED UPDATE VALUES");

		ContentValues initialValues = new ContentValues();
		initialValues.put(SCORE, totalscore);
		initialValues.put(RAW_SCORE, rawscore);
		String where = "_id=?";
		int id = cursor.getColumnIndexOrThrow("_id");
		String testString = Double.toString(id);
		Log.w(TAG, "ID: " + cursor.getString(cursor.getColumnIndexOrThrow("_id")));

		String[] whereArgs = new String[] {cursor.getString(cursor.getColumnIndexOrThrow("_id"))};
		Log.w(TAG, cursor.getString(cursor.getColumnIndexOrThrow("_id")));
		courseone.update(TABLE_NAME, initialValues, where, whereArgs);
	}

	public Course createcourseassignment(String assignmentype, String Course, Double weight, int assnum){
		dbCourseOne = new CourseOneData(fragact);
		courseone = dbCourseOne.getWritableDatabase();
		int course2 = Integer.parseInt(Course);
		Log.w(TAG, "Start Inserting data");

		String[] column = {C_ID, ASSIGNMENT_TYPE, COURSE,RAW_SCORE, SCORE, WEIGHT};

		Cursor cursor = courseone.query(TABLE_NAME, column, null, null, null, null, null); 

		Log.w(TAG, "Start Inserting data");

		Double indweight = weight/assnum;
		for (int i = 1; i<=assnum; i++){
			createAssignment(assignmentype,course2,indweight, i);
			Log.w(TAG, "data inserted" + i);

		}
		return this;

	}

	public double CourseGrade(int id){
		Log.w(TAG, "Started Calculating Grades");

		double grade, rawscore,totalscore,weight;
		grade = 0;
		String[] column = {C_ID, ASSIGNMENT_TYPE, COURSE,RAW_SCORE, SCORE, WEIGHT};	//LOMZ
		Cursor cursor = courseone.query(TABLE_NAME, column, null, null, null, null, null); 
		cursor.moveToFirst();
		int i = 0;
		while (cursor.isAfterLast() == false) {
			i++;
			String test = Integer.toString(i);

			Log.w(TAG, test);

			if (cursor.isNull(cursor.getColumnIndex(RAW_SCORE)) && cursor.isNull(cursor.getColumnIndex(SCORE))){
				rawscore =1;
				totalscore = 0;
				weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
			}
			else if (cursor.isNull(cursor.getColumnIndex(SCORE)) &&!cursor.isNull(cursor.getColumnIndex(RAW_SCORE))){
				totalscore = cursor.getDouble(cursor.getColumnIndex(RAW_SCORE));
				rawscore = 1;
				weight = 0;
				weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
			}
			else if (!cursor.isNull(cursor.getColumnIndex(SCORE)) && cursor.isNull(cursor.getColumnIndex(RAW_SCORE))){
				totalscore = 0;
				rawscore = 1;
				weight = 0;
				weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
			}
			else{
				rawscore= cursor.getDouble(cursor.getColumnIndex(RAW_SCORE));
				totalscore = cursor.getDouble(cursor.getColumnIndex(SCORE));
				weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
			}
			grade = grade + (totalscore*weight/rawscore);
			cursor.moveToNext();
		}
		String testString = Double.toString(grade);
		Log.w(TAG, "Grade: " + testString);


		return 100*grade;
	}
	public String GetCourseName(int id){
		String coursename = "course";

		String[] column = {C_ID, COURSE};	//LOMZ

		Cursor mCursor = courseone.query(TABLE_NAME2, column,null, null, null, null, null);
		mCursor.moveToFirst();
		while (mCursor.isAfterLast() == false) {
			if(id == mCursor.getInt(mCursor.getColumnIndex(C_ID))){
				coursename = mCursor.getString(mCursor.getColumnIndex(COURSE));
				break;
			}
			mCursor.moveToNext();

		}
		return coursename;
	}

	public double CourseHighestGrade(int id){
		Log.w(TAG, "Started Calculating Grades");

		double grade, rawscore,totalscore,weight;
		grade = 1;
		String[] column = {C_ID, ASSIGNMENT_TYPE, COURSE,RAW_SCORE, SCORE, WEIGHT};	//LOMZ
		Cursor cursor = courseone.query(TABLE_NAME, column, null, null, null, null, null); 
		cursor.moveToFirst();
		int i = 0;
		while (cursor.isAfterLast() == false) {
			i++;
			if(id == cursor.getInt(cursor.getColumnIndex(COURSE))){
				String test = Integer.toString(i);

				Log.w(TAG, test);

				if (cursor.isNull(cursor.getColumnIndex(RAW_SCORE)) && cursor.isNull(cursor.getColumnIndex(SCORE))){
					rawscore =1;
					totalscore = 0;
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
				}
				else if (cursor.isNull(cursor.getColumnIndex(SCORE)) &&!cursor.isNull(cursor.getColumnIndex(RAW_SCORE))){
					totalscore = cursor.getDouble(cursor.getColumnIndex(RAW_SCORE));
					rawscore = 1;
					weight = 0;
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
				}
				else if (!cursor.isNull(cursor.getColumnIndex(SCORE)) && cursor.isNull(cursor.getColumnIndex(RAW_SCORE))){
					totalscore = 0;
					rawscore = 1;
					weight = 0;
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
				}
				else{
					rawscore= cursor.getDouble(cursor.getColumnIndex(RAW_SCORE));
					totalscore = cursor.getDouble(cursor.getColumnIndex(SCORE));
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
					grade = grade- (weight -(totalscore*weight/rawscore));
					String testString = Double.toString(grade);

					Log.w(TAG, "Grade: " + testString);
					Log.w(TAG, "rawscore: " + Double.toString(rawscore));
					Log.w(TAG, "totalscore: " + Double.toString(totalscore));
					Log.w(TAG, "weight: " + Double.toString(weight));

				}
			}
			cursor.moveToNext();


		}
		String testString = Double.toString(grade);
		Log.w(TAG, "Grade: " + testString);


		return 100*grade;
	}
    public void remove(long id){
        String string =String.valueOf(id);
    	dbCourseOne = new CourseOneData(fragact);
		courseone = dbCourseOne.getWritableDatabase();
        courseone.delete(TABLE_NAME, COURSE + "=?", new String[] { string });
    }
}
