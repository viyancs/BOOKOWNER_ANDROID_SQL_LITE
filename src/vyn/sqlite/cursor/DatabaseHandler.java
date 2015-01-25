package vyn.sqlite.cursor;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bookOwner";

    // Contacts table name
    private static final String TABLE_BOOKS = "bookowner";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME_BOOK = "name_buku";
    private static final String KEY_PENGARANG = "pengarang";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ABOUT = "about";
    private static final String KEY_YEAR = "year";
    private final ArrayList<Books> contact_list = new ArrayList<Books>();

    public DatabaseHandler(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
	String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
		+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME_BOOK + " TEXT,"
		+ KEY_PENGARANG + " TEXT," + KEY_EMAIL + " TEXT," + KEY_ABOUT + " TEXT," + KEY_YEAR + " TEXT" + ")";
	db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// Drop older table if existed
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);

	// Create tables again
	onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void Add_Contact(Books contact) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(KEY_NAME_BOOK, contact.getNameBook()); // Contact Name
	values.put(KEY_PENGARANG, contact.getPengarang()); // Contact Phone
	values.put(KEY_EMAIL, contact.getPenerbit()); // Contact Email
	values.put(KEY_ABOUT, contact.getAbout());
	values.put(KEY_YEAR, contact.getYear());
	// Inserting Row
	db.insert(TABLE_BOOKS, null, values);
	db.close(); // Closing database connection
    }

    // Getting single contact
    Books Get_Contact(int id) {
	SQLiteDatabase db = this.getReadableDatabase();

	Cursor cursor = db.query(TABLE_BOOKS, new String[] { KEY_ID,
		KEY_NAME_BOOK, KEY_PENGARANG, KEY_EMAIL, KEY_ABOUT, KEY_YEAR }, KEY_ID + "=?",
		new String[] { String.valueOf(id) }, null, null, null, null);
	if (cursor != null)
	    cursor.moveToFirst();

	Books contact = new Books(Integer.parseInt(cursor.getString(0)),
		cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
	// return contact
	cursor.close();
	db.close();

	return contact;
    }

    // Getting All Contacts
    public ArrayList<Books> Get_Contacts() {
	try {
	    contact_list.clear();

	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_BOOKS;

	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
		do {
		    Books contact = new Books();
		    contact.setID(Integer.parseInt(cursor.getString(0)));
		    contact.setNameBook(cursor.getString(1));
		    contact.setPengarang(cursor.getString(2));
		    contact.setPenerbit(cursor.getString(3));
		    contact.setAbout(cursor.getString(4));
		    contact.setYear(cursor.getString(5));
		    // Adding contact to list
		    contact_list.add(contact);
		} while (cursor.moveToNext());
	    }

	    // return contact list
	    cursor.close();
	    db.close();
	    return contact_list;
	} catch (Exception e) {
	    // TODO: handle exception
	    Log.e("all_contact", "" + e);
	}

	return contact_list;
    }

    // Updating single contact
    public int Update_Contact(Books contact) {
	SQLiteDatabase db = this.getWritableDatabase();

	ContentValues values = new ContentValues();
	values.put(KEY_NAME_BOOK, contact.getNameBook());
	values.put(KEY_PENGARANG, contact.getPengarang());
	values.put(KEY_EMAIL, contact.getPenerbit());
	values.put(KEY_ABOUT, contact.getAbout());
	values.put(KEY_YEAR, contact.getYear());

	// updating row
	return db.update(TABLE_BOOKS, values, KEY_ID + " = ?",
		new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void Delete_Contact(int id) {
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_BOOKS, KEY_ID + " = ?",
		new String[] { String.valueOf(id) });
	db.close();
    }

    // Getting contacts Count
    public int Get_Total_Contacts() {
	String countQuery = "SELECT  * FROM " + TABLE_BOOKS;
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.rawQuery(countQuery, null);
	cursor.close();

	// return count
	return cursor.getCount();
    }

}
