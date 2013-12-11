package com.GetTotWrk.gradebook;

import java.util.Locale;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;

public class MainActivity extends FragmentActivity implements
ActionBar.TabListener {
	
	//Logs are used in order to debug, this way people who want to see how the app works
	//can access the logs to see how data is transferred and without ever changing the user interface
	
	

	//course1, course2, course3, course4, course5 are all identicial accept for hardcoded to refer to specvific course
	// Buttons: button1_1,button3_1,button2_1,button2_1,button2_2,button2_3 ,button3_1,button3_2,button3_2 are all identical
	// except for hard coded to refer to specific assignments
	// button1_1 in course1 commented for questions on how the buttons work see that instance
	
	
	//variables used in fragments to return user input to functions
	Button cust;
	Dialog custom;
	Dialog secondary;
	EditText Fname;
	EditText Lname;
	static String Course1;
	TextView txt;
	Button savebtn;
	Button canbtn;
	TextView frag;
	static int assnum;
	static Double weight;
	static Double weightdouble,totalscoredouble,rawscoredouble;
	static Double num;

	SectionsPagerAdapter mSectionsPagerAdapter;
	public static class TotalSectionFragment extends Fragment {

		private static final String TAG = "TOTALSECTIONFRAGMENT";

		public TotalSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.summary_frag, container, false);
			//load course object
			course = new Course(getActivity());
			Log.w(TAG, "COURSES ");
			
			//load all text box variables
			final TextView message1_1 = (TextView) rootView.findViewById(R.id.textview1_1);
			final TextView message1_2 = (TextView) rootView.findViewById(R.id.textview1_2);
			final TextView message1_3 = (TextView) rootView.findViewById(R.id.textview1_3);
			final TextView message1_4 = (TextView) rootView.findViewById(R.id.textview1_4);
			final TextView message1_5 = (TextView) rootView.findViewById(R.id.textview1_5);


			final TextView message2_1 = (TextView) rootView.findViewById(R.id.textview2_1);
			final TextView message2_2 = (TextView) rootView.findViewById(R.id.textview2_2);
			final TextView message2_3 = (TextView) rootView.findViewById(R.id.textview2_3);
			final TextView message2_4 = (TextView) rootView.findViewById(R.id.textview2_4);
			final TextView message2_5 = (TextView) rootView.findViewById(R.id.textview2_5);

			final TextView message3_1 = (TextView) rootView.findViewById(R.id.textview3_1);
			final TextView message3_2 = (TextView) rootView.findViewById(R.id.textview3_2);
			final TextView message3_3 = (TextView) rootView.findViewById(R.id.textview3_3);
			final TextView message3_4 = (TextView) rootView.findViewById(R.id.textview3_4);
			final TextView message3_5 = (TextView) rootView.findViewById(R.id.textview3_5);
//load update button 
			Button updategrade = (Button) rootView.findViewById(R.id.updategrade);
			
			updategrade.setOnClickListener(new OnClickListener(){
					//When update is pressed functions called to update grade
					// buttons are hard coded to correspond with certain fragments
				@Override
				public void onClick(View arg0) {
					message3_1.setText(String.format("%.2f", course.CourseGrade(1)));
					message2_1.setText(String.format("%.2f", course.CourseHighestGrade(1)));
					message3_2.setText(String.format("%.2f", course.CourseGrade(2)));
					message2_2.setText(String.format("%.2f", course.CourseHighestGrade(2)));
					message3_3.setText(String.format("%.2f", course.CourseGrade(3)));
					message2_3.setText(String.format("%.2f", course.CourseHighestGrade(3)));
					message3_4.setText(String.format("%.2f", course.CourseGrade(4)));
					message2_4.setText(String.format("%.2f", course.CourseHighestGrade(4)));
					message3_5.setText(String.format("%.2f", course.CourseGrade(5)));
					message2_5.setText(String.format("%.2f", course.CourseHighestGrade(5)));

					message1_1.setText(course.GetCourseName(1));
					message1_2.setText(course.GetCourseName(2));
					message1_3.setText(course.GetCourseName(3));
					message1_4.setText(course.GetCourseName(4));
					message1_5.setText(course.GetCourseName(5));
					Toast.makeText(getActivity(),"Grades of all courses updated",Toast.LENGTH_LONG).show();


				}

			});
			course.open();
			Log.w(TAG, "COURSES OPEN");
			message3_1.setText(String.format("%.2f", course.CourseGrade(1)));
			message2_1.setText(String.format("%.2f", course.CourseHighestGrade(1)));
			message3_2.setText(String.format("%.2f", course.CourseGrade(2)));
			message2_2.setText(String.format("%.2f", course.CourseHighestGrade(2)));
			message3_3.setText(String.format("%.2f", course.CourseGrade(3)));
			message2_3.setText(String.format("%.2f", course.CourseHighestGrade(3)));
			message3_4.setText(String.format("%.2f", course.CourseGrade(4)));
			message2_4.setText(String.format("%.2f", course.CourseHighestGrade(4)));
			message3_5.setText(String.format("%.2f", course.CourseGrade(5)));
			message2_5.setText(String.format("%.2f", course.CourseHighestGrade(5)));

			message1_1.setText(course.GetCourseName(1));
			message1_2.setText(course.GetCourseName(2));
			message1_3.setText(course.GetCourseName(3));
			message1_4.setText(course.GetCourseName(4));
			message1_5.setText(course.GetCourseName(5));
			return rootView;
		}
	}

	

	
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}


	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}


		@Override
		public Fragment getItem(int position) {
			//chooses the correct fragment
			//all fragments are identical expect for hard coding
			//of inputs into database querying functions 
			if(position == 0){
				Fragment fragment = new TotalSectionFragment();
				return fragment;
			}
			else if(position == 1){
				Fragment fragment = new Course1();
				return fragment;
			}
			else if(position == 2){
				Fragment fragment = new Course2();
				return fragment;
			}
			else if(position == 3){
				Fragment fragment = new Course3();
				return fragment;
			}else if(position == 4){
				Fragment fragment = new Course4();
				return fragment;
			}
			else{
				Fragment fragment = new Course5();
				return fragment;
			}
		}


		@Override
		public int getCount() {
			// Show 4 total pages.
			return 6;
		}

		
		//sets the title of the actionbar
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_total).toUpperCase(l);
			case 1:
				return getString(R.string.title_section1).toUpperCase(l);
			case 2:
				return getString(R.string.title_section2).toUpperCase(l);
			case 3:
				return getString(R.string.title_section3).toUpperCase(l);
			case 4:	
				return getString(R.string.title_section4).toUpperCase(l);
			case 5:	
				return getString(R.string.title_section5).toUpperCase(l);

			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	static Course course;

	
	
	//Fragment that display everything for Course1
	public static class Course1 extends Fragment {
		private static final String TAG = "MainActivity1";
		SimpleCursorAdapter adapter;
		Cursor cursor;
		ListView list;
		int intnum;
		public Course1() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {


			final View rootView = inflater.inflate(R.layout.databaselayout,container, false);
			course = new Course(getActivity());
			course.open();
			Log.w(TAG, "COURSES OPEN");

			list = (ListView) rootView.findViewById(R.id.listView1);	//LOMZ
			Log.w(TAG, "Make List");

			String[] from = {Course.ASSIGNMENT_TYPE, Course.RAW_SCORE,Course.SCORE, Course.WEIGHT};	//LOMZ
			Log.w(TAG, "Make from");

			//String[] column = {Course.C_ID, Course.ASSIGNMENT_TYPE, Course.COURSE,Course.SCORE, Course.WEIGHT};	//LOMZ
			int[] to = {R.id.asstype,
					R.id.RawScore,
					R.id.listtotalscore,
					R.id.listweight,};	//LOMZ
			cursor = course.fetchAssignmentsbyName("1"); //HardCoded
			Log.w(TAG, "FETCH ALL ENTRIES");

			adapter = new SimpleCursorAdapter(getActivity(),R.layout.list_layout, cursor, from, to);	//LOMZ 
			list.setAdapter(adapter);	//LOMZ
			Log.w(TAG, "Set new adapter");

			Button Update = (Button) rootView.findViewById(R.id.updateview); // update button updates the view of the app
			Update.setOnClickListener(new OnClickListener(){ 

				@Override
				public void onClick(View arg0) {
					cursor = course.fetchAssignmentsbyName("1"); //HardCoded to only show courses with identifier of "1"
					adapter.swapCursor(cursor); // changes adapter
					list.setAdapter(adapter); // displays new adapter
												// in order to show the updates list
					Toast.makeText(getActivity(),"Course List updated",Toast.LENGTH_LONG).show();

				}

			});
			Button editcourse = (Button) rootView.findViewById(R.id.editcourse);
			// shows dialog box with choices
			// 
			editcourse.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog_editcourse);
					custom.setTitle("Edit Course Assignments");
					Button button0_2 = (Button)custom.findViewById(R.id.button0_2);
					button0_2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							course.remove(1); //HardCoded
							Toast.makeText(getActivity(),"All course entries deleted",Toast.LENGTH_LONG).show();

						}

					});

					Button button0_1 = (Button)custom.findViewById(R.id.button0_1); 
					button0_1.setOnClickListener(new View.OnClickListener() { // on press dialog appears to allow user to
																			// change the course name on the title page
						@Override
						public void onClick(View view) {
							Log.w(TAG, "Edit Course Clicked");

							custom.hide();	// hides previous dialog box
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcoursename_dialog);
							secondary.setTitle("Edit Course Name");
							Log.w(TAG, "Dialog Changed");
							secondary.show(); // shows new dialog box with custom title

							Log.w(TAG, "Dialog Show");
							final EditText coursename = (EditText) secondary.findViewById(R.id.coursename);
							coursename.setText("Course 1");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn3);
							Button save = (Button) secondary.findViewById(R.id.savebtn3);
							cancel.setOnClickListener(new View.OnClickListener() { //when cancel button is pressed

								@Override
								public void onClick(View v) {
									secondary.dismiss();		// close current dialog box
									custom.show();				// return to previous dialog box

								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String coursnametext = coursename.getText().toString();
									course.courseNameUpdate(1,coursnametext);	// Call course member function
																				// changes all instances for that course
									secondary.dismiss();						//close current box
									Toast.makeText(getActivity(),"Assignments added",Toast.LENGTH_LONG).show();

									custom.show();								// open previous box
								}
							});

						}
					});
					Button button1_1 = (Button)custom.findViewById(R.id.button1_1);
					Log.w(TAG, "Button 1_1 aquired");

					button1_1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Homework");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();	// save num to string
									String assignmentweightstr = assignmentnum1.getText().toString(); // save weight to string

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) { //check to see if string is empty
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									//if string is not empty then save the values to the database
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString()); // database expects integer
										weight = Double.parseDouble(assignmentweight1.getText().toString()); // database expects double

										if (weight <= 0 || weight > 1 || assnum <= 0  ){ // make sure inputs are in the right range
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());
											Toast.makeText(getActivity(),"Error: assignments not added",Toast.LENGTH_LONG).show();
											//if not in the right rang then display a notification

										}
										else{
											Log.w(TAG, "Min Conditions met");	// display
											course.createcourseassignment("Homework", "1", weight,assnum); //HardCoded call course member function to create assignment
											Log.w(TAG, "Course Assignment Saved");
											Toast.makeText(getActivity(),"Assignments added",Toast.LENGTH_LONG).show(); // display confirmation of saving
											secondary.dismiss();		//close current window
											custom.show();}}			// open previous window
								}
							});
						}
					});
					Button button2_1 = (Button)custom.findViewById(R.id.button2_1);
					button2_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Essay"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());
											Toast.makeText(getActivity(),"Error: assignments not added",Toast.LENGTH_LONG).show();



										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Essay", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											Toast.makeText(getActivity(),"Assignments added",Toast.LENGTH_LONG).show();

											custom.show();}
									}
								}
							});
						}
					});
					Button button3_1 = (Button)custom.findViewById(R.id.button3_1);
					button3_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Exam"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Exam", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_2 = (Button)custom.findViewById(R.id.button1_2);
					button1_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Lab", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_2 = (Button)custom.findViewById(R.id.button2_2);
					button2_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Pre-lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Pre-lab", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_2 = (Button)custom.findViewById(R.id.button3_2);
					button3_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Project"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Project", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_3 = (Button)custom.findViewById(R.id.button1_3);
					button1_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Quiz"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Quiz", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();
										}
									}

								}
							});
						}
					});
					Button button2_3 = (Button)custom.findViewById(R.id.button2_3);
					button2_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Program"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Program", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});					
						}
					});
					Button button3_3 = (Button)custom.findViewById(R.id.button3_3);
					button3_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Other"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Other", "1", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});						

						}
					});

					custom.show();
				}

			});
			// list displays the database values ie the assignments/grades
			//when entry is clicked dialog box shows up
			// dialog box acts the same as the other buttons in terms of error checkign

			list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> list, View view, int position, long id) {
					// Get the cursor, positioned to the corresponding row in the result set
					Log.w(TAG, "Int position: " + Integer.toString(position));

					final Cursor cursor2 = (Cursor) list.getItemAtPosition(position);

					// TODO Auto-generated method stub
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog);
					custom.setTitle("Add Assignment Grade");
					Button savebtn = (Button)custom.findViewById(R.id.savebtn);
					Button canbtn = (Button)custom.findViewById(R.id.canbtn);
					final EditText totalscore = (EditText) custom.findViewById(R.id.totalscore);
					final EditText rawscore = (EditText) custom.findViewById(R.id.rawscore);


					savebtn.setOnClickListener(new View.OnClickListener() {


						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub

							String totalscorestr = totalscore.getText().toString();
							String rawscorestr = rawscore.getText().toString();

							if (totalscorestr.matches("") || rawscorestr.matches("")) {
								Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
							else{

							//spinner.setText(" ");
							String text2 = totalscore.getText().toString();
							String text3 = rawscore.getText().toString();

							try {
								rawscoredouble = Double.parseDouble(text3); // use excveption for handling parsing error.
							} catch (NumberFormatException e) {

							}
							try {
								totalscoredouble = Double.parseDouble(text2); // use exception for error handling parsing error
							} catch (NumberFormatException e) {

							}
							{
								//course.createAssignment(text, text2, text3);
								//Toast.makeText(getActivity(), "DATA SAVED" + text + text2 + text3, Toast.LENGTH_SHORT).show();
								Log.w(TAG, "Rawscore: " + text3);
								Log.w(TAG, "Total score: " + text2);

								course.tableupdate(cursor2, totalscoredouble,rawscoredouble);

								custom.dismiss();

							}
							}

						}

					});
					canbtn.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							custom.dismiss();

						}
					});
					custom.show();
				}


			});
			
			//Searching for different asssignment typs
			//
			EditText myFilter = (EditText) rootView.findViewById(R.id.myFilter);
			myFilter.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					adapter.getFilter().filter(s.toString());
				}
			});

			adapter.setFilterQueryProvider(new FilterQueryProvider() {
				public Cursor runQuery(CharSequence constraint) {
					return course.fetchAssignmentsbyAssignment(constraint.toString()); //calls member function to find 
				}													//all entries with a certain course name and assignment type
			});




			return rootView;


		}

	}
	
	//see comments on Course1 for details of operation
	public static class Course2 extends Fragment {
		private static final String TAG = "MainActivity1";
		SimpleCursorAdapter adapter;
		Cursor cursor;
		ListView list;
		int intnum;
		public Course2() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {


			final View rootView = inflater.inflate(R.layout.databaselayout,container, false);
			course = new Course(getActivity());
			course.open();
			Log.w(TAG, "COURSES OPEN");

			list = (ListView) rootView.findViewById(R.id.listView1);	//LOMZ
			Log.w(TAG, "Make List");

			String[] from = {Course.ASSIGNMENT_TYPE, Course.RAW_SCORE,Course.SCORE, Course.WEIGHT};	//LOMZ
			Log.w(TAG, "Make from");

			//String[] column = {Course.C_ID, Course.ASSIGNMENT_TYPE, Course.COURSE,Course.SCORE, Course.WEIGHT};	//LOMZ
			int[] to = {R.id.asstype,
					R.id.RawScore,
					R.id.listtotalscore,
					R.id.listweight,};	//LOMZ
			cursor = course.fetchAssignmentsbyName("2"); //HardCoded
			Log.w(TAG, "FETCH ALL ENTRIES");

			adapter = new SimpleCursorAdapter(getActivity(),R.layout.list_layout, cursor, from, to);	//LOMZ 
			list.setAdapter(adapter);	//LOMZ
			Log.w(TAG, "Set new adapter");

			Button Update = (Button) rootView.findViewById(R.id.updateview);
			Update.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					cursor = course.fetchAssignmentsbyName("2"); //HardCoded
					adapter.swapCursor(cursor);
					list.setAdapter(adapter);

				}

			});
			Button editcourse = (Button) rootView.findViewById(R.id.editcourse);
			editcourse.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog_editcourse);
					custom.setTitle("Edit Course Assignments");
					Button button0_2 = (Button)custom.findViewById(R.id.button0_2);
					button0_2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							course.remove(2); //HardCoded
							Toast.makeText(getActivity(),"All course entries deleted",Toast.LENGTH_LONG).show();


						}

					});

					Button button0_1 = (Button)custom.findViewById(R.id.button0_1);
					button0_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Log.w(TAG, "Edit Course Clicked");

							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcoursename_dialog);
							secondary.setTitle("Edit Course Name");
							Log.w(TAG, "Dialog Changed");
							secondary.show();

							Log.w(TAG, "Dialog Show");
							final EditText coursename = (EditText) secondary.findViewById(R.id.coursename);
							coursename.setText("Course 1");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn3);
							Button save = (Button) secondary.findViewById(R.id.savebtn3);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String coursnametext = coursename.getText().toString();
									course.courseNameUpdate(2,coursnametext); // hard coded
									secondary.dismiss();
									custom.show();
								}
							});

						}
					});
					Button button1_1 = (Button)custom.findViewById(R.id.button1_1);
					Log.w(TAG, "Button 1_1 aquired");

					button1_1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Homework");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Homework", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_1 = (Button)custom.findViewById(R.id.button2_1);
					button2_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Essay"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Essay", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_1 = (Button)custom.findViewById(R.id.button3_1);
					button3_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Exam"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Exam", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_2 = (Button)custom.findViewById(R.id.button1_2);
					button1_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Lab", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_2 = (Button)custom.findViewById(R.id.button2_2);
					button2_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Pre-lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Pre-lab", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_2 = (Button)custom.findViewById(R.id.button3_2);
					button3_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Project"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Project", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_3 = (Button)custom.findViewById(R.id.button1_3);
					button1_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Quiz"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Quiz", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_3 = (Button)custom.findViewById(R.id.button2_3);
					button2_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Program"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Program", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});						}
					});
					Button button3_3 = (Button)custom.findViewById(R.id.button3_3);
					button3_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Other"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Other", "2", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});						}
					});

					custom.show();
				}

			});


			list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> list, View view, int position, long id) {
					// Get the cursor, positioned to the corresponding row in the result set
					Log.w(TAG, "Int position: " + Integer.toString(position));

					final Cursor cursor2 = (Cursor) list.getItemAtPosition(position);

					// TODO Auto-generated method stub
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog);
					custom.setTitle("Add Assignment Grade");
					Button savebtn = (Button)custom.findViewById(R.id.savebtn);
					Button canbtn = (Button)custom.findViewById(R.id.canbtn);
					final EditText totalscore = (EditText) custom.findViewById(R.id.totalscore);
					final EditText rawscore = (EditText) custom.findViewById(R.id.rawscore);


					savebtn.setOnClickListener(new View.OnClickListener() {


						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub


							String totalscorestr = totalscore.getText().toString();
							String rawscorestr = rawscore.getText().toString();

							if (totalscorestr.matches("") || rawscorestr.matches("")) {
								Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
							else{
							
							String text2 = totalscore.getText().toString();
							String text3 = rawscore.getText().toString();

							try {
								rawscoredouble = Double.parseDouble(text3); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							try {
								totalscoredouble = Double.parseDouble(text2); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							{
								//course.createAssignment(text, text2, text3);
								//Toast.makeText(getActivity(), "DATA SAVED" + text + text2 + text3, Toast.LENGTH_SHORT).show();
								Log.w(TAG, "Rawscore: " + text3);
								Log.w(TAG, "Total score: " + text2);

								course.tableupdate(cursor2, totalscoredouble,rawscoredouble);

								custom.dismiss();}


							}

						}

					});
					canbtn.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							custom.dismiss();

						}
					});
					custom.show();
				}


			});
			EditText myFilter = (EditText) rootView.findViewById(R.id.myFilter);
			myFilter.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					adapter.getFilter().filter(s.toString());
				}
			});

			adapter.setFilterQueryProvider(new FilterQueryProvider() {
				public Cursor runQuery(CharSequence constraint) {
					return course.fetchAssignmentsbyAssignment(constraint.toString());
				}
			});




			return rootView;


		}

	}
	public static class Course3 extends Fragment {
		private static final String TAG = "MainActivity1";
		SimpleCursorAdapter adapter;
		Cursor cursor;
		ListView list;
		int intnum;
		public Course3() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {


			final View rootView = inflater.inflate(R.layout.databaselayout,container, false);
			course = new Course(getActivity());
			course.open();
			Log.w(TAG, "COURSES OPEN");

			list = (ListView) rootView.findViewById(R.id.listView1);	//LOMZ
			Log.w(TAG, "Make List");

			String[] from = {Course.ASSIGNMENT_TYPE, Course.RAW_SCORE,Course.SCORE, Course.WEIGHT};	//LOMZ
			Log.w(TAG, "Make from");

			//String[] column = {Course.C_ID, Course.ASSIGNMENT_TYPE, Course.COURSE,Course.SCORE, Course.WEIGHT};	//LOMZ
			int[] to = {R.id.asstype,
					R.id.RawScore,
					R.id.listtotalscore,
					R.id.listweight,};	//LOMZ
			cursor = course.fetchAssignmentsbyName("3"); //HardCoded
			Log.w(TAG, "FETCH ALL ENTRIES");

			adapter = new SimpleCursorAdapter(getActivity(),R.layout.list_layout, cursor, from, to);	//LOMZ 
			list.setAdapter(adapter);	//LOMZ
			Log.w(TAG, "Set new adapter");

			Button Update = (Button) rootView.findViewById(R.id.updateview);
			Update.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					cursor = course.fetchAssignmentsbyName("3"); //HardCoded
					adapter.swapCursor(cursor);
					list.setAdapter(adapter);

				}

			});
			Button editcourse = (Button) rootView.findViewById(R.id.editcourse);
			editcourse.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog_editcourse);
					custom.setTitle("Edit Course Assignments");
					Button button0_2 = (Button)custom.findViewById(R.id.button0_2);
					button0_2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							course.remove(3); //HardCoded
							Toast.makeText(getActivity(),"All course entries deleted",Toast.LENGTH_LONG).show();

						}

					});

					Button button0_1 = (Button)custom.findViewById(R.id.button0_1);
					button0_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Log.w(TAG, "Edit Course Clicked");

							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcoursename_dialog);
							secondary.setTitle("Edit Course Name");
							Log.w(TAG, "Dialog Changed");
							secondary.show();

							Log.w(TAG, "Dialog Show");
							final EditText coursename = (EditText) secondary.findViewById(R.id.coursename);
							coursename.setText("Course 1");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn3);
							Button save = (Button) secondary.findViewById(R.id.savebtn3);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String coursnametext = coursename.getText().toString();
									course.courseNameUpdate(3,coursnametext); //Hard coded
									secondary.dismiss();
									custom.show();
								}
							});

						}
					});
					Button button1_1 = (Button)custom.findViewById(R.id.button1_1);
					Log.w(TAG, "Button 1_1 aquired");

					button1_1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Homework");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Homework", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_1 = (Button)custom.findViewById(R.id.button2_1);
					button2_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Essay"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Essay", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_1 = (Button)custom.findViewById(R.id.button3_1);
					button3_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Exam"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();

									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){

											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Exam", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_2 = (Button)custom.findViewById(R.id.button1_2);
					button1_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Lab", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_2 = (Button)custom.findViewById(R.id.button2_2);
					button2_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Pre-lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Pre-lab", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_2 = (Button)custom.findViewById(R.id.button3_2);
					button3_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Project"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Project", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_3 = (Button)custom.findViewById(R.id.button1_3);
					button1_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Quiz"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Quiz", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_3 = (Button)custom.findViewById(R.id.button2_3);
					button2_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Program"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Program", "3", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});						}
					});
					Button button3_3 = (Button)custom.findViewById(R.id.button3_3);
					button3_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Other"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									assnum = Integer.parseInt(assignmentnum1.getText().toString());
									weight = Double.parseDouble(assignmentweight1.getText().toString());

									if (weight <= 0 || weight > 1 || assnum <= 0  ){
										Log.w(TAG, "Min Conditions not met");
										Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
										Log.w(TAG, "weight" + assignmentweight1.getText().toString());


									}
									else{
										Log.w(TAG, "Min Conditions met");
										course.createcourseassignment("Other", "3", weight,assnum); //HardCoded
										Log.w(TAG, "Course Assignment Saved");
										secondary.dismiss();
										custom.show();}
								}
							});						}
					});

					custom.show();
				}

			});


			list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> list, View view, int position, long id) {
					// Get the cursor, positioned to the corresponding row in the result set
					Log.w(TAG, "Int position: " + Integer.toString(position));

					final Cursor cursor2 = (Cursor) list.getItemAtPosition(position);

					// TODO Auto-generated method stub
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog);
					custom.setTitle("Add Assignment Grade");
					Button savebtn = (Button)custom.findViewById(R.id.savebtn);
					Button canbtn = (Button)custom.findViewById(R.id.canbtn);
					final EditText totalscore = (EditText) custom.findViewById(R.id.totalscore);
					final EditText rawscore = (EditText) custom.findViewById(R.id.rawscore);


					savebtn.setOnClickListener(new View.OnClickListener() {


						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub

							String totalscorestr = totalscore.getText().toString();
							String rawscorestr = rawscore.getText().toString();

							if (totalscorestr.matches("") || rawscorestr.matches("")) {
								Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
							else{

							//spinner.setText(" ");
							String text2 = totalscore.getText().toString();
							String text3 = rawscore.getText().toString();

							try {
								rawscoredouble = Double.parseDouble(text3); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							try {
								totalscoredouble = Double.parseDouble(text2); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							{
								//course.createAssignment(text, text2, text3);
								//Toast.makeText(getActivity(), "DATA SAVED" + text + text2 + text3, Toast.LENGTH_SHORT).show();
								Log.w(TAG, "Rawscore: " + text3);
								Log.w(TAG, "Total score: " + text2);

								course.tableupdate(cursor2, totalscoredouble,rawscoredouble);

								custom.dismiss();


							}
							}

						}

					});
					canbtn.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							custom.dismiss();

						}
					});
					custom.show();
				}


			});
			EditText myFilter = (EditText) rootView.findViewById(R.id.myFilter);
			myFilter.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					adapter.getFilter().filter(s.toString());
				}
			});

			adapter.setFilterQueryProvider(new FilterQueryProvider() {
				public Cursor runQuery(CharSequence constraint) {
					return course.fetchAssignmentsbyAssignment(constraint.toString());
				}
			});




			return rootView;


		}

	}
	public static class Course4 extends Fragment {
		private static final String TAG = "MainActivity1";
		SimpleCursorAdapter adapter;
		Cursor cursor;
		ListView list;
		int intnum;
		public Course4() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {


			final View rootView = inflater.inflate(R.layout.databaselayout,container, false);
			course = new Course(getActivity());
			course.open();
			Log.w(TAG, "COURSES OPEN");

			list = (ListView) rootView.findViewById(R.id.listView1);	//LOMZ
			Log.w(TAG, "Make List");

			String[] from = {Course.ASSIGNMENT_TYPE, Course.RAW_SCORE,Course.SCORE, Course.WEIGHT};	//LOMZ
			Log.w(TAG, "Make from");

			//String[] column = {Course.C_ID, Course.ASSIGNMENT_TYPE, Course.COURSE,Course.SCORE, Course.WEIGHT};	//LOMZ
			int[] to = {R.id.asstype,
					R.id.RawScore,
					R.id.listtotalscore,
					R.id.listweight,};	//LOMZ
			cursor = course.fetchAssignmentsbyName("4"); //HardCoded
			Log.w(TAG, "FETCH ALL ENTRIES");

			adapter = new SimpleCursorAdapter(getActivity(),R.layout.list_layout, cursor, from, to);	//LOMZ 
			list.setAdapter(adapter);	//LOMZ
			Log.w(TAG, "Set new adapter");

			Button Update = (Button) rootView.findViewById(R.id.updateview);
			Update.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					cursor = course.fetchAssignmentsbyName("4"); //HardCoded
					adapter.swapCursor(cursor);
					list.setAdapter(adapter);

				}

			});
			Button editcourse = (Button) rootView.findViewById(R.id.editcourse);
			editcourse.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog_editcourse);
					custom.setTitle("Edit Course Assignments");
					Button button0_2 = (Button)custom.findViewById(R.id.button0_2);
					button0_2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							course.remove(4); //HardCoded
							Toast.makeText(getActivity(),"All course entries deleted",Toast.LENGTH_LONG).show();

						}

					});

					Button button0_1 = (Button)custom.findViewById(R.id.button0_1);
					button0_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Log.w(TAG, "Edit Course Clicked");

							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcoursename_dialog);
							secondary.setTitle("Edit Course Name");
							Log.w(TAG, "Dialog Changed");
							secondary.show();

							Log.w(TAG, "Dialog Show");
							final EditText coursename = (EditText) secondary.findViewById(R.id.coursename);
							coursename.setText("Course 1");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn3);
							Button save = (Button) secondary.findViewById(R.id.savebtn3);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String coursnametext = coursename.getText().toString();
									course.courseNameUpdate(4,coursnametext); //Hard coded
									secondary.dismiss();
									custom.show();
								}
							});

						}
					});
					Button button1_1 = (Button)custom.findViewById(R.id.button1_1);
					Log.w(TAG, "Button 1_1 aquired");

					button1_1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Homework");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Homework", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_1 = (Button)custom.findViewById(R.id.button2_1);
					button2_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Essay"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Essay", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_1 = (Button)custom.findViewById(R.id.button3_1);
					button3_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Exam"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Exam", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_2 = (Button)custom.findViewById(R.id.button1_2);
					button1_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Lab", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_2 = (Button)custom.findViewById(R.id.button2_2);
					button2_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Pre-lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Pre-lab", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_2 = (Button)custom.findViewById(R.id.button3_2);
					button3_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Project"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Project", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_3 = (Button)custom.findViewById(R.id.button1_3);
					button1_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Quiz"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Quiz", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_3 = (Button)custom.findViewById(R.id.button2_3);
					button2_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Program"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Program", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});						}
					});
					Button button3_3 = (Button)custom.findViewById(R.id.button3_3);
					button3_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Other"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Other", "4", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});						}
					});
					custom.show();
				}

			});


			list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> list, View view, int position, long id) {
					// Get the cursor, positioned to the corresponding row in the result set
					Log.w(TAG, "Int position: " + Integer.toString(position));

					final Cursor cursor2 = (Cursor) list.getItemAtPosition(position);

					// TODO Auto-generated method stub
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog);
					custom.setTitle("Add Assignment Grade");
					Button savebtn = (Button)custom.findViewById(R.id.savebtn);
					Button canbtn = (Button)custom.findViewById(R.id.canbtn);
					final EditText totalscore = (EditText) custom.findViewById(R.id.totalscore);
					final EditText rawscore = (EditText) custom.findViewById(R.id.rawscore);


					savebtn.setOnClickListener(new View.OnClickListener() {


						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							String totalscorestr = totalscore.getText().toString();
							String rawscorestr = rawscore.getText().toString();

							if (totalscorestr.matches("") || rawscorestr.matches("")) {
								Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
							else{


						
							String text2 = totalscore.getText().toString();
							String text3 = rawscore.getText().toString();

							try {
								rawscoredouble = Double.parseDouble(text3); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							try {
								totalscoredouble = Double.parseDouble(text2); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							{
								//course.createAssignment(text, text2, text3);
								//Toast.makeText(getActivity(), "DATA SAVED" + text + text2 + text3, Toast.LENGTH_SHORT).show();
								Log.w(TAG, "Rawscore: " + text3);
								Log.w(TAG, "Total score: " + text2);

								course.tableupdate(cursor2, totalscoredouble,rawscoredouble);

								custom.dismiss();


							}
							}

						}

					});
					canbtn.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							custom.dismiss();

						}
					});
					custom.show();
				}


			});
			EditText myFilter = (EditText) rootView.findViewById(R.id.myFilter);
			myFilter.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					adapter.getFilter().filter(s.toString());
				}
			});

			adapter.setFilterQueryProvider(new FilterQueryProvider() {
				public Cursor runQuery(CharSequence constraint) {
					return course.fetchAssignmentsbyAssignment(constraint.toString());
				}
			});




			return rootView;


		}

	}
	public static class Course5 extends Fragment {
		private static final String TAG = "MainActivity1";
		SimpleCursorAdapter adapter;
		Cursor cursor;
		ListView list;
		int intnum;
		public Course5() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {


			final View rootView = inflater.inflate(R.layout.databaselayout,container, false);
			course = new Course(getActivity());
			course.open();
			Log.w(TAG, "COURSES OPEN");

			list = (ListView) rootView.findViewById(R.id.listView1);	//LOMZ
			Log.w(TAG, "Make List");

			String[] from = {Course.ASSIGNMENT_TYPE, Course.RAW_SCORE,Course.SCORE, Course.WEIGHT};	//LOMZ
			Log.w(TAG, "Make from");

			//String[] column = {Course.C_ID, Course.ASSIGNMENT_TYPE, Course.COURSE,Course.SCORE, Course.WEIGHT};	//LOMZ
			int[] to = {R.id.asstype,
					R.id.RawScore,
					R.id.listtotalscore,
					R.id.listweight,};	//LOMZ
			cursor = course.fetchAssignmentsbyName("5"); //HardCoded
			Log.w(TAG, "FETCH ALL ENTRIES");

			adapter = new SimpleCursorAdapter(getActivity(),R.layout.list_layout, cursor, from, to);	//LOMZ 
			list.setAdapter(adapter);	//LOMZ
			Log.w(TAG, "Set new adapter");

			Button Update = (Button) rootView.findViewById(R.id.updateview);
			Update.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					cursor = course.fetchAssignmentsbyName("5"); //HardCoded
					adapter.swapCursor(cursor);
					list.setAdapter(adapter);

				}

			});
			Button editcourse = (Button) rootView.findViewById(R.id.editcourse);
			editcourse.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog_editcourse);
					custom.setTitle("Edit Course Assignments");
					Button button0_2 = (Button)custom.findViewById(R.id.button0_2);
					button0_2.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							course.remove(5); //HardCoded
							Toast.makeText(getActivity(),"All course entries deleted",Toast.LENGTH_LONG).show();

						}

					});

					Button button0_1 = (Button)custom.findViewById(R.id.button0_1);
					button0_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Log.w(TAG, "Edit Course Clicked");

							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcoursename_dialog);
							secondary.setTitle("Edit Course Name");
							Log.w(TAG, "Dialog Changed");
							secondary.show();

							Log.w(TAG, "Dialog Show");
							final EditText coursename = (EditText) secondary.findViewById(R.id.coursename);
							coursename.setText("Course 1");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn3);
							Button save = (Button) secondary.findViewById(R.id.savebtn3);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String coursnametext = coursename.getText().toString();
									course.courseNameUpdate(5,coursnametext); //Hard coded
									secondary.dismiss();
									custom.show();
								}
							});

						}
					});
					Button button1_1 = (Button)custom.findViewById(R.id.button1_1);
					Log.w(TAG, "Button 1_1 aquired");

					button1_1.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Homework");
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Homework", "5", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_1 = (Button)custom.findViewById(R.id.button2_1);
					button2_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Essay"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Essay", "5", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_1 = (Button)custom.findViewById(R.id.button3_1);
					button3_1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Exam"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {

									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Exam", "5", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_2 = (Button)custom.findViewById(R.id.button1_2);
					button1_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Lab", "5", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_2 = (Button)custom.findViewById(R.id.button2_2);
					button2_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Pre-lab"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Pre-lab", "5", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button3_2 = (Button)custom.findViewById(R.id.button3_2);
					button3_2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Project"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Project", "5", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button1_3 = (Button)custom.findViewById(R.id.button1_3);
					button1_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Quiz"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
										assnum = Integer.parseInt(assignmentnum1.getText().toString());
										weight = Double.parseDouble(assignmentweight1.getText().toString());

										if (weight <= 0 || weight > 1 || assnum <= 0  ){
											Log.w(TAG, "Min Conditions not met");
											Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
											Log.w(TAG, "weight" + assignmentweight1.getText().toString());


										}
										else{
											Log.w(TAG, "Min Conditions met");
											course.createcourseassignment("Quiz", "5", weight,assnum); //HardCoded
											Log.w(TAG, "Course Assignment Saved");
											secondary.dismiss();
											custom.show();}
									}
								}
							});
						}
					});
					Button button2_3 = (Button)custom.findViewById(R.id.button2_3);
					button2_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Program"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);

							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
									assnum = Integer.parseInt(assignmentnum1.getText().toString());
									weight = Double.parseDouble(assignmentweight1.getText().toString());

									if (weight <= 0 || weight > 1 || assnum <= 0  ){
										Log.w(TAG, "Min Conditions not met");
										Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
										Log.w(TAG, "weight" + assignmentweight1.getText().toString());


									}
									else{
										Log.w(TAG, "Min Conditions met");
										course.createcourseassignment("Program", "5", weight,assnum); //HardCoded
										Log.w(TAG, "Course Assignment Saved");
										secondary.dismiss();
										custom.show();}
								}
								}
							});						}
					});
					Button button3_3 = (Button)custom.findViewById(R.id.button3_3);
					button3_3.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							custom.hide();
							final Dialog secondary = new Dialog(getActivity());
							secondary.setContentView(R.layout.editcourse_dialog_assignment);
							secondary.setTitle("Add Assignment Type");
							Log.w(TAG, "Dialog Changed");
							secondary.show();
							final EditText assignment = (EditText) secondary.findViewById(R.id.assignment);
							Log.w(TAG, "Assignment aquired");

							assignment.setText("Other"); //hard coded
							Button cancel = (Button) secondary.findViewById(R.id.canbtn2);
							Button save = (Button) secondary.findViewById(R.id.savebtn2);
							final EditText assignmentweight1 = (EditText) secondary.findViewById(R.id.assignmentweight);
							final EditText assignmentnum1 = (EditText) secondary.findViewById(R.id.assignmentnumber);
							cancel.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									secondary.dismiss();
									custom.show();
								}
							});
							save.setOnClickListener(new View.OnClickListener() {

								@Override
								public void onClick(View v) {
									String assignmentnumstr = assignmentnum1.getText().toString();
									String assignmentweightstr = assignmentnum1.getText().toString();

									if (assignmentnumstr.matches("") || assignmentweightstr.matches("")) {
										Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
									else{
									assnum = Integer.parseInt(assignmentnum1.getText().toString());
									weight = Double.parseDouble(assignmentweight1.getText().toString());

									if (weight <= 0 || weight > 1 || assnum <= 0  ){
										Log.w(TAG, "Min Conditions not met");
										Log.w(TAG, "Assnum" + assignmentnum1.getText().toString());
										Log.w(TAG, "weight" + assignmentweight1.getText().toString());


									}
									else{
										Log.w(TAG, "Min Conditions met");
										course.createcourseassignment("Other", "5", weight,assnum); //HardCoded
										Log.w(TAG, "Course Assignment Saved");
										secondary.dismiss();
										custom.show();}
								}
								}
							});						}
					});
					
					custom.show();
				}

			});


			list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> list, View view, int position, long id) {
					// Get the cursor, positioned to the corresponding row in the result set
					Log.w(TAG, "Int position: " + Integer.toString(position));

					final Cursor cursor2 = (Cursor) list.getItemAtPosition(position);

					// TODO Auto-generated method stub
					final Dialog custom = new Dialog(getActivity());
					custom.setContentView(R.layout.dialog);
					custom.setTitle("Add Assignment Grade");
					Button savebtn = (Button)custom.findViewById(R.id.savebtn);
					Button canbtn = (Button)custom.findViewById(R.id.canbtn);
					final EditText totalscore = (EditText) custom.findViewById(R.id.totalscore);
					final EditText rawscore = (EditText) custom.findViewById(R.id.rawscore);
					final Button erase = (Button) custom.findViewById(R.id.delete);

					erase.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							course.tabledelete(cursor2);
							custom.dismiss();


						}
					});
					savebtn.setOnClickListener(new View.OnClickListener() {


						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							String totalscorestr = totalscore.getText().toString();
							String rawscorestr = rawscore.getText().toString();

							if (totalscorestr.matches("") || rawscorestr.matches("")) {
								Toast.makeText(getActivity(), "You did not enter a value", Toast.LENGTH_SHORT).show();}
							else{


							//spinner.setText(" ");
							String text2 = totalscore.getText().toString();
							String text3 = rawscore.getText().toString();

							try {
								rawscoredouble = Double.parseDouble(text3); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							try {
								totalscoredouble = Double.parseDouble(text2); // Make use of autoboxing.  It's also easier to read.
							} catch (NumberFormatException e) {

							}
							{
								//course.createAssignment(text, text2, text3);
								//Toast.makeText(getActivity(), "DATA SAVED" + text + text2 + text3, Toast.LENGTH_SHORT).show();
								Log.w(TAG, "Rawscore: " + text3);
								Log.w(TAG, "Total score: " + text2);

								course.tableupdate(cursor2, totalscoredouble,rawscoredouble);

								custom.dismiss();
							}

							}

						}

					});
					canbtn.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View view) {
							// TODO Auto-generated method stub
							custom.dismiss();

						}
					});
					custom.show();
				}


			});
			EditText myFilter = (EditText) rootView.findViewById(R.id.myFilter);
			myFilter.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {

				}

				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					adapter.getFilter().filter(s.toString());
				}
			});

			adapter.setFilterQueryProvider(new FilterQueryProvider() {
				public Cursor runQuery(CharSequence constraint) {
					return course.fetchAssignmentsbyAssignment(constraint.toString());
				}
			});




			return rootView;


		}

	}
}

