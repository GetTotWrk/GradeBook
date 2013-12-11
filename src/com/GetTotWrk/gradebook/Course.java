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


//Course Object that keeps the database and has member functions to edit database



public class Course {
	private static final String TAG = "COURSES";
	private CourseOneData dbCourseOne;
	private SQLiteDatabase courseone;

	public CourseOneData dbhelper(){	//returnse database helper
		return dbCourseOne;
	}

	public SQLiteDatabase coursedb(){	//returns full datbase
		return courseone;
	}

	//Static fragment activity
	public final FragmentActivity fragact;
	
	//strings defining the databse name, columns and table name
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

	
	//SQL database main table creator string
	private final static String createDB = "create table if not exists " + TABLE_NAME + " ( "
			+ C_ID + " integer primary key autoincrement, "
			+ COURSE +" real, "
			+ NUMBER + " real, "
			+ ASSIGNMENT_TYPE +" text, "
			+ WEIGHT +" real, "
			+ RAW_SCORE +" real, "
			+ SCORE +" real); ";
	//SQL database main table2 creator string

	private final static String createDB2 = "create table if not exists " + TABLE_NAME2 + " ( "
			+ C_ID + " integer primary key autoincrement, "
			+ COURSE +" text); ";

	
	
	
	//Fetch all entries returns the entire database
	public Cursor fetchAllEntries() {
		Log.w(TAG, "FETCH ALL ENTRIES");

		String[] column = {C_ID, ASSIGNMENT_TYPE, COURSE,RAW_SCORE, SCORE, WEIGHT};	//LOMZ

		Cursor cursor = courseone.query(TABLE_NAME, column, null, null, null, null, null); 
		String testString = Integer.toString(cursor.getCount());

		//makes sure cursor is not null which would indicate an error with the database
		if (cursor != null) {
			cursor.moveToFirst();
			Log.w(TAG, testString);

		}
		return cursor;
	}
	
	//Constructor instantiates the object based on the activity the object is in
	public Course(FragmentActivity fragact) {
		this.fragact = fragact;
	}
	private static class CourseOneData extends SQLiteOpenHelper {

		CourseOneData(Context context){
			super(context, DATABASE_NAME, null, VERSION);
		}

			//creaes SQLite Database with 2 tables
		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.w(TAG, "CREATE TABLE");
			db.execSQL(createDB);
			db.execSQL(createDB2);


		}
		@Override
				//when DB is updated the table is expanded
		public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

			db.execSQL("drop table " + TABLE_NAME);
			db.execSQL("drop table " + TABLE_NAME2);
		}
	}

	
		//Another constructor that also creates a DB links with previous constructor
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
//returns entire DB
	}


// inputs function arguments into DB
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

//creates new course name in table2
	public long createCourse(String course) {
		Log.w(TAG, "STARTED INSERT VALUES");
		Log.w(TAG, course);


		ContentValues initialValues = new ContentValues();
		initialValues.put(COURSE, course);

		return courseone.insert(TABLE_NAME2, null, initialValues);

	}
	
	//for testing and user experience purposes puts in some sample data
	public void insertSomeCourses() {

		createCourse("Course 1");
		createCourse("Course 2");
		createCourse("Course 3");
		createCourse("Course 4");
		createCourse("Course 5");
		createCourse("Course 6");

	}
	
	//for testing purposes puts in sample data assignments
	public void insertSomeAssignments() {

		createAssignment("Test",1,.30,2);
		createAssignment("Homework",2,.2, 5);
		createAssignment("Lab",1,.25,5);
		createAssignment("Paper",2,0.25,2);
	}


//returns all DB entries that match function arguments
	public Cursor fetchAssignmentsbyName(String inputText) throws SQLException {
		Log.w(TAG, inputText);
		int ID = Integer.parseInt(inputText);
		Cursor mCursor = null;
		String[] column = {C_ID, ASSIGNMENT_TYPE,COURSE,RAW_SCORE,SCORE, WEIGHT};	//LOMZ
//chekcs input args
		if (inputText == null  ||  inputText.length () == 0)  {
			mCursor = courseone.query(TABLE_NAME, column,null, null, null, null, null);

		}
		else {
			mCursor = courseone.query(true, TABLE_NAME, column, COURSE + " like '%" + ID + "%'", null,null, null, null, null);
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		//return cursor with all values
		return mCursor;

	}
	
	//same as previous function but checks for assignment name rather than course name
	public Cursor fetchAssignmentsbyAssignment(String inputText) throws SQLException {
		Log.w(TAG, inputText);
		Cursor mCursor = null;
		String[] column = {C_ID, ASSIGNMENT_TYPE,COURSE,RAW_SCORE,SCORE, WEIGHT};	//LOMZ

		if (inputText == null  ||  inputText.length () == 0)  {
			mCursor = courseone.query(TABLE_NAME, column,null, null, null, null, null);
		}
		else {
			mCursor = courseone.query(true, TABLE_NAME, column, ASSIGNMENT_TYPE + " like '%" + inputText + "%'", null,null, null, null, null);
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	
	//change name of certain course
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
//Update table based on user defined arguements, adds raw and recieved score
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

	
	//creates assignment row, defines type, course name, weight and number of assignm,ents
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
//calculates current course grade by grade = grade + (weight*recieved/total)
	//iterates over entire DB
	//only sums those entries that are associated with the user defined course
	public double CourseGrade(int id){
		Log.w(TAG, "Started Calculating Grades");

		double grade, rawscore,totalscore,weight;
		grade = 0;
		String[] column = {C_ID, ASSIGNMENT_TYPE, COURSE,RAW_SCORE, SCORE, WEIGHT};	//LOMZ
		Cursor cursor = courseone.query(TABLE_NAME, column, null, null, null, null, null); 
		cursor.moveToFirst();
		int i = 0;
		while (cursor.isAfterLast() == false) {
			if(id == cursor.getInt(cursor.getColumnIndex(COURSE))){
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
			}
			cursor.moveToNext();

		}
		String testString = Double.toString(grade);
		Log.w(TAG, "Grade: " + testString);


		return 100*grade;
	}
	
	//get course name
	//used in totalfragment
	
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
	//does same thing as previous argument but calculates the highest score you can get by figuring out how many points
	// you have already lost
	//Assumes grade is out of 100
	public double CourseHighestGrade(int id){
		Log.w(TAG, "Started Calculating Grades");

		double grade, rawscore,totalscore,weight,totalweight;
		totalweight = 0;
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
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
				}
				else if (cursor.isNull(cursor.getColumnIndex(SCORE)) &&!cursor.isNull(cursor.getColumnIndex(RAW_SCORE))){
					totalscore = cursor.getDouble(cursor.getColumnIndex(RAW_SCORE));
					rawscore = 1;
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
				}
				else if (!cursor.isNull(cursor.getColumnIndex(SCORE)) && cursor.isNull(cursor.getColumnIndex(RAW_SCORE))){
					totalscore = 0;
					rawscore = 1;
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
				}
				else{
					rawscore= cursor.getDouble(cursor.getColumnIndex(RAW_SCORE));
					totalscore = cursor.getDouble(cursor.getColumnIndex(SCORE));
					weight = cursor.getDouble(cursor.getColumnIndex(WEIGHT));
					totalweight = totalweight + weight;
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

		if (grade == 0.00 && totalweight>0){
			return 100;
		}
		else{
			return 100*grade;}
	}
	
	//Deletes all entries associated with a certain course
	public void remove(long id){
		String string =String.valueOf(id);
		dbCourseOne = new CourseOneData(fragact);
		courseone = dbCourseOne.getWritableDatabase();
		courseone.delete(TABLE_NAME, COURSE + "=?", new String[] { string });
	}
	
	//deletes all entries in a certain row b
	public void tabledelete(Cursor cursor) {
		// TODO Auto-generated method stub
		Log.w(TAG, "STARTED UPDATE VALUES");

		String where = "_id=?";
		int id = cursor.getColumnIndexOrThrow("_id");
		String testString = Double.toString(id);
		Log.w(TAG, "ID: " + cursor.getString(cursor.getColumnIndexOrThrow("_id")));

		String[] whereArgs = new String[] {cursor.getString(cursor.getColumnIndexOrThrow("_id"))};
		Log.w(TAG, cursor.getString(cursor.getColumnIndexOrThrow("_id")));
		courseone.delete(TABLE_NAME, where, whereArgs);
	}
}

