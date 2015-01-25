package vyn.sqlite.cursor;

public class Books {

	// private variables
	public int _id;
	public String _nama_buku;
	public String _pengarang;
	public String _penerbit;
	public String _about;
	public String _year;

	public Books() {
	}

	// constructor
	public Books(int id, String name_book, String _pengarang, String _penerbit, String _about, String _year) {
		this._id = id;
		this._nama_buku = name_book;
		this._pengarang = _pengarang;
		this._penerbit = _penerbit;
		this._about = _about;
		this._year = _year;
	}

	// constructor
	public Books(String name_book, String _pengarang, String _penerbit, String _about , String _year) {
		this._nama_buku = name_book;
		this._pengarang = _pengarang;
		this._penerbit = _penerbit;
		this._about = _about;
		this._year = _year;
	}

	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int id) {
		this._id = id;
	}

	// getting name
	public String getNameBook() {
		return this._nama_buku;
	}

	// setting name
	public void setNameBook(String name) {
		this._nama_buku = name;
	}

	public String getPengarang() {
		return _pengarang;
	}

	public void setPengarang(String _pengarang) {
		this._pengarang = _pengarang;
	}

	// getting email
	public String getPenerbit() {
		return this._penerbit;
	}

	// setting email
	public void setPenerbit(String email) {
		this._penerbit = email;
	}

	public String getAbout() {
		return _about;
	}

	public void setAbout(String _about) {
		this._about = _about;
	}

	public String getYear() {
		return _year;
	}

	public void setYear(String _year) {
		this._year = _year;
	}
	
	

}