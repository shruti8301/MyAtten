package com.example.myatten;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        // TODO Auto-generated constructor stub
        super(context,  DATABASE_NAME, null, 1);
    }


    String password;
    String name;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Mydatabase.db";

    // Contacts table name
    private static final String TABLE_REGISTER= "register";
    //private static final String TABLE_DATE= "date";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_REG_NO="reg_no";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CONTACT = "contact";
    //public static final String KEY_DATE="date";
    //public static final String KEY_TIME="time";


    public static final String CREATE_TABLE="CREATE TABLE " + TABLE_REGISTER + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+KEY_REG_NO+ " TEXT,"
            + KEY_CONTACT + " TEXT," + KEY_PASSWORD + " TEXT " + ")";

    //public static final String CREATE_TABLE_DATE="CREATE TABLE " + TABLE_DATE + "("
          //  + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REG_NO+ " TEXT,"+KEY_DATE + " TEXT,"
        // + KEY_TIME + " TEXT, " + "FOREIGN KEY ("+KEY_REG_NO+") REFERENCES "+TABLE_REGISTER+"("+KEY_REG_NO+"));";


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_TABLE);
        //db.execSQL(CREATE_TABLE_DATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE);

        // Create tables again
        onCreate(db);
    }

    void addregister(RegisterData registerdata)
    // code to add the new register
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,registerdata.getName()); // register first Name
        values.put(KEY_REG_NO, registerdata.getReg_no());//register email id
        values.put(KEY_CONTACT, registerdata.getContact());//register mobile no
        values.put(KEY_PASSWORD, registerdata.getPassword());


        // Inserting Row

        db.insert(TABLE_REGISTER, null, values);
        db.close(); // Closing database connection

    }





    //code to get the register
    String getregister(String regno){
        SQLiteDatabase db = this.getReadableDatabase();
        //String selectquery="SELECT * FROM TABLE_REGISTER";
        Cursor cursor=db.query(TABLE_REGISTER,null,  "reg_no=?",new String[]{regno},null, null, null, null);

        if(cursor.getCount()<1){
            cursor.close();
            return "Not Exist";
        }
        else if(cursor.getCount()>=1 && cursor.moveToFirst()){

            password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            cursor.close();

        }
        return password;


    }
    //retrieving data
    Cursor alldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from TABLE_REGISTER",null);
        return cursor;
    }
    String getname(){
        SQLiteDatabase db = this.getReadableDatabase();
        //String selectquery="SELECT * FROM TABLE_REGISTER";
        Cursor cursor=db.query(TABLE_REGISTER,null,  "name=?",new String[]{name},null, null, null, null);

        if(cursor.getCount()<1){
            cursor.close();
            return "Not Exist";
        }
        else if(cursor.getCount()>=1 && cursor.moveToFirst()){

            name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            cursor.close();

        }
        return name;


    }

    public String getDatabaseName() {
        return DATABASE_NAME;
    }


    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getTableContacts() {
        return TABLE_REGISTER;

    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

}
