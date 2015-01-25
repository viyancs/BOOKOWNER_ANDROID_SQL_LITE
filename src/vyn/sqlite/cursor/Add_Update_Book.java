package vyn.sqlite.cursor;

import vyn.sqlite.cursor.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Add_Update_Book extends Activity {
	EditText add_name_book, add_pengarang, add_penerbit, add_about, add_year;
	Button add_save_btn, add_view_all, update_btn, update_view_all;
	LinearLayout add_view, update_view;
	String valid_pengarang = null, valid_penerbit = null, valid_name_book = null,
			Toast_msg = null, valid_user_id = "", valid_about = null,
			valid_year = null;
	int USER_ID;
	DatabaseHandler dbHandler = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_update_screen);

		// set screen
		Set_Add_Update_Screen();

		// set visibility of view as per calling activity
		String called_from = getIntent().getStringExtra("called");

		if (called_from.equalsIgnoreCase("add")) {
			add_view.setVisibility(View.VISIBLE);
			update_view.setVisibility(View.GONE);
		} else {

			update_view.setVisibility(View.VISIBLE);
			add_view.setVisibility(View.GONE);
			USER_ID = Integer.parseInt(getIntent().getStringExtra("USER_ID"));

			Books c = dbHandler.Get_Contact(USER_ID);

			add_name_book.setText(c.getNameBook());
			add_pengarang.setText(c.getPengarang());
			add_penerbit.setText(c.getPenerbit());
			add_about.setText(c.getAbout());
			add_year.setText(c.getYear());
			// dbHandler.close();
		}
		add_pengarang.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				// min lenth 10 and max lenth 12 (2 extra for - as per phone
				// matcher format)
				Is_Valid_Pengarang(3, 25, add_pengarang);
			}
		});

		
		add_year.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Is_Valid_Number_Year(4, 4, add_year);
				
			}
		});
		
		add_penerbit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Is_Valid_Penerbit(2, 45, add_penerbit);
				
			}
		});

		add_name_book.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Is_Valid_Book_Name(2, 45, add_name_book);
			}
		});

		add_save_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// check the value state is null or not
				//Show_Toast(valid_name_book);
				//Show_Toast(valid_pengarang);
				if (valid_name_book != null && valid_pengarang != null && valid_penerbit != null && valid_year.length() != 0) {

					dbHandler.Add_Contact(new Books(valid_name_book,
							valid_pengarang, valid_penerbit, add_about.getText().toString(),
							valid_year));
					Toast_msg = "Data inserted successfully";
					Show_Toast(Toast_msg);
					Reset_Text();

				}
				else {
					Show_Toast("Please Fill in Book Form");
				}

			}
		});

		update_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				valid_name_book = add_name_book.getText().toString();
				valid_pengarang = add_pengarang.getText().toString();
				valid_penerbit = add_penerbit.getText().toString();
				valid_about = add_about.getText().toString();
				valid_year = add_year.getText().toString();

				// check the value state is null or not
				if (valid_name_book != null && valid_pengarang != null && valid_penerbit != null && valid_year.length() != 0) {

					dbHandler.Update_Contact(new Books(USER_ID, valid_name_book,
							valid_pengarang, valid_penerbit, valid_about,
							valid_year));
					dbHandler.close();
					Toast_msg = "Data Update successfully";
					Show_Toast(Toast_msg);
					Reset_Text();
				} else {
					Toast_msg = "Sorry Some Fields are missing.\nPlease Fill up all.";
					Show_Toast(Toast_msg);
				}

			}
		});
		update_view_all.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent view_user = new Intent(Add_Update_Book.this,
						Main_Screen.class);
				view_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(view_user);
				finish();
			}
		});

		add_view_all.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent view_user = new Intent(Add_Update_Book.this,
						Main_Screen.class);
				view_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(view_user);
				finish();
			}
		});

	}

	public void Set_Add_Update_Screen() {

		add_name_book = (EditText) findViewById(R.id.add_name_book);
		add_pengarang = (EditText) findViewById(R.id.add_pengarang);
		add_penerbit = (EditText) findViewById(R.id.add_penerbit);
		add_about = (EditText) findViewById(R.id.add_about);
		add_year = (EditText) findViewById(R.id.add_year);

		add_save_btn = (Button) findViewById(R.id.add_save_btn);
		update_btn = (Button) findViewById(R.id.update_btn);
		add_view_all = (Button) findViewById(R.id.add_view_all);
		update_view_all = (Button) findViewById(R.id.update_view_all);

		add_view = (LinearLayout) findViewById(R.id.add_view);
		update_view = (LinearLayout) findViewById(R.id.update_view);

		add_view.setVisibility(View.GONE);
		update_view.setVisibility(View.GONE);

	}

	public void Is_Valid_Pengarang(int MinLen, int MaxLen,EditText edt) throws NumberFormatException {
		if (edt.getText().toString().length() < MinLen) {
			edt.setError("Minimum length " + MinLen);
			valid_pengarang = null;

		} else if (edt.getText().toString().length() > MaxLen) {
			edt.setError("Maximum length " + MaxLen);
			valid_pengarang = null;

		} else {
			valid_pengarang = edt.getText().toString();

		}

	} // END OF Edittext validation
	
	public void Is_Valid_Penerbit(int MinLen, int MaxLen,EditText edt) throws NumberFormatException {
		if (edt.getText().toString().length() < MinLen) {
			edt.setError("Minimum length " + MinLen);
			valid_penerbit = null;

		} else if (edt.getText().toString().length() > MaxLen) {
			edt.setError("Maximum length " + MaxLen);
			valid_penerbit = null;

		} else {
			valid_penerbit = edt.getText().toString();

		}

	} // END OF Edittext validation
	
	public void Is_Valid_Number_Year(int MinLen, int MaxLen,EditText edt) {
		if (edt.getText().toString() == null) {
			edt.setError("Invalid Year");
			valid_year = null;
		} else if (edt.getText().toString().length() <= 0) {
			edt.setError("Number Only");
			valid_year = null;
		} else if (edt.getText().toString().length() < MinLen) {
			edt.setError("Minimum length " + MinLen);
			valid_year = null;
		} else if (edt.getText().toString().length() < MaxLen) {
			edt.setError("Maximum length " + MaxLen);
			valid_year = null;
		} else {
			valid_year = edt.getText().toString();
		}
	}

	public void Is_Valid_Book_Name(int MinLen, int MaxLen,EditText edt) throws NumberFormatException {
		if (edt.getText().toString().length() < MinLen) {
			edt.setError("Minimum length " + MinLen);
			valid_name_book = null;

		} else if (edt.getText().toString().length() > MaxLen) {
			edt.setError("Maximum length " + MaxLen);
			valid_name_book = null;

		} else {
			valid_name_book = edt.getText().toString();
		}

	}

	public void Show_Toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}

	public void Reset_Text() {

		add_name_book.getText().clear();
		add_pengarang.getText().clear();
		add_penerbit.getText().clear();
		add_about.getText().clear();
		add_year.getText().clear();
	}

}
