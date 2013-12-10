//			Button send = (Button) rootView.findViewById(R.id.sendbutton);          // objects that
			//
			//			send.setOnClickListener(new OnClickListener() { // Creating a click
			//
			//				public void onClick(View arg0) { // What we want it to do on a click
			//					EditText messageinput = (EditText) rootView.findViewById(R.id.inputtext);
			//					TextView message = (TextView) rootView.findViewById(R.id.textview);
			//					String text = messageinput.getText().toString();
			//					message.setText(text);
			//					Log.d(Tag,"Button Pressed");
			//					Toast.makeText(getActivity(), "Button Pressed", Toast.LENGTH_SHORT).show();
			//
			//					String[] columns = {DBHelper.ASSIGNMENT_TYPE};
			//					Log.d(Tag,"String Array created");                	
			//					Toast.makeText(getActivity(), "String Array created", Toast.LENGTH_SHORT).show();
			//
			//
			//					Cursor cursor = dbcourseone.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null); 
			//					Log.d(Tag,"Cursor Created");
			//					cursor.moveToFirst();
			//					while(cursor.moveToNext()){
			//						String assignment = cursor.getString(cursor.getColumnIndex(DBHelper.ASSIGNMENT_TYPE));
			//						Toast.makeText(getActivity(), assignment, Toast.LENGTH_SHORT).show();
			//					}
			//					cursor.close();
			//
			//				}		
			//
			//			});
			//			Button send2 = (Button) rootView.findViewById(R.id.Button01);          // objects that
			//
			//			send2.setOnClickListener(new OnClickListener() { // Creating a click
			//
			//				public void onClick(View arg0) { // What we want it to do on a click
			//					EditText messageinput = (EditText) rootView.findViewById(R.id.EditText01);
			//					TextView message = (TextView) rootView.findViewById(R.id.textview2);
			//					String text = messageinput.getText().toString();
			//					message.setText(text);
			//
			//
			//
			//
			//				}
			//			});


//			
			//			Button button1 = (Button) rootView.findViewById(R.id.button1_2);
			//
			//			button1.setOnClickListener(new OnClickListener() { // Creating a click
			//							
			//				//Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner2_2);
			//				public void onClick(View arg0) { // What we want it to do on a click
			//				
			//					//EditText messageinput = (EditText) rootView.findViewById(R.id.edittext4_2);
			//					EditText messageinput2 = (EditText) rootView.findViewById(R.id.edittext4_2);
			//
			//					//String text = spinner.getSelectedItem().toString();
			//					//String text2 = messageinput.getText().toString();
			//					//String text3 = messageinput2.getText().toString();
			//					//course.createAssignment(text, text2, text3);
			//					//Toast.makeText(getActivity(), "DATA SAVED" + text + text2 + text3, Toast.LENGTH_SHORT).show();
			//
			//					// Dynamically Create button
			//					//LinearLayout mainLayout = (LinearLayout)rootView.findViewById(R.id.linlayout3);
			//
			//					//Button addButton =new Button(getActivity());
			//					//addButton.setText("add");
			//
			//					//mainLayout.addView(addButton);
			//
			//				}		
			//
			//			});
			//			Button button2 = (Button) rootView.findViewById(R.id.button1_3);
			//			button2.setOnClickListener(new OnClickListener() {
			//
			//				@Override
			//				public void onClick(View arg0) {
			//					// TODO Auto-generated method stub
			//					final Dialog custom = new Dialog(getActivity());
			//					custom.setContentView(R.layout.dialog);
			//					custom.setTitle("Add Assignment Grade");
			//					Button savebtn = (Button)custom.findViewById(R.id.savebtn);
			//					Button canbtn = (Button)custom.findViewById(R.id.canbtn);
			//					savebtn.setOnClickListener(new View.OnClickListener() {
			//
			//						@Override
			//						public void onClick(View view) {
			//							// TODO Auto-generated method stub
			//
			//
			//							Spinner spinner = (Spinner) custom.findViewById(R.id.spinner);
			//							String text = spinner.getSelectedItem().toString();
			//							EditText totalscore = (EditText) custom.findViewById(R.id.totalscore);
			//							EditText rawscore = (EditText) custom.findViewById(R.id.rawscore);
			//							String text2 = totalscore.getText().toString();
			//							String text3 = rawscore.getText().toString();
			//							if(text == "Assignment Types"){
			//								Toast.makeText(getActivity(), " No entry found", Toast.LENGTH_SHORT).show();
			//
			//							}
			//							else{
			//								//course.createAssignment(text, text2, text3);
			//								Toast.makeText(getActivity(), "DATA SAVED" + text + text2 + text3, Toast.LENGTH_SHORT).show();
			//								custom.dismiss();
			//								list = (ListView) rootView.findViewById(R.id.listView1);	//LOMZ
			//
			//								String[] from = {Course.ASSIGNMENT_TYPE, Course.COURSE,Course.SCORE, Course.WEIGHT};	//LOMZ
			//								String[] column = {Course.C_ID, Course.ASSIGNMENT_TYPE, Course.COURSE,Course.SCORE, Course.WEIGHT};	//LOMZ
			//								int[] to = {R.id.code,
			//									    R.id.name,
			//									    R.id.continent,
			//									    R.id.region,};	//LOMZ
			//								cursor = course.fetchAllEntries();
			//								SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(getActivity(),R.layout.list_layout, cursor, from, to);	//LOMZ 
			//								list.setAdapter(adapter2);
			//							}
			//						}
			//
			//					});
			//					canbtn.setOnClickListener(new View.OnClickListener() {
			//
			//						@Override
			//						public void onClick(View view) {
			//							// TODO Auto-generated method stub
			//							custom.dismiss();
			//
			//						}
			//					});
			//					custom.show();
			//			}
			//		});




			//			TextView message1 = (TextView) rootView.findViewById(R.id.textview1_1);
			//			TextView message2 = (TextView) rootView.findViewById(R.id.textview1_2);
			//			TextView message3 = (TextView) rootView.findViewById(R.id.textview1_3);
			//
			//
			//			TextView button1 = (TextView) rootView.findViewById(R.id.textview2_1);
			//			TextView button2 = (TextView) rootView.findViewById(R.id.textview2_2);
			//			TextView button3 = (TextView) rootView.findViewById(R.id.textview2_3);
			//
			//			String[] columns = {DBHelperCourseName.COURSE};
			//			String text;
			//			Cursor cursor = dbcourse.query(dbhelpercoursename.TABLE_NAME, columns, null, null, null, null, null); 
			//			cursor.moveToFirst();
			//			text = cursor.getString(cursor.getColumnIndex(dbhelpercoursename.COURSE));
			//			message1.setText(text);
			//			button1.setText(text);
			//
			//
			//
			//			cursor.moveToNext();
			//			text = cursor.getString(cursor.getColumnIndex(dbhelpercoursename.COURSE));
			//			message2.setText(text);
			//			button2.setText(text);
			//
			//			cursor.moveToNext();
			//
			//			String text2 = cursor.getString(cursor.getColumnIndex(dbhelpercoursename.COURSE));
			//			message3.setText(text2);
			//			button3.setText(text);