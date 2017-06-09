package com.example.atomsystems.mysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by atomsystems on 20/01/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "My Products";
    public static final String Table_Name = "Product_Table";
    public static final String Col_1 = "Id";
    public static final String Col_2 = "Name";
    public static final String Col_3 = "Quantity";
    public static final String Col_4 = "Price";


    public DataBaseHelper(Context context) {

        super(context,Database_Name,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" Create Table " + Table_Name + " (ID INTEGER  PRIMARY KEY AUTOINCREMENT,NAME TEXT,QUANTITY INTEGER ,PRICE INTEGER) " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE"+Table_Name);
        onCreate(db);
    }
    public boolean insertData(String name, String quantity, String price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, name);
        contentValues.put(Col_3,quantity);
        contentValues.put(Col_4, price);
        long result = db.insert(Table_Name, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }
    public boolean IsertDeleteSpace(String id,String name,String quantity,String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, id);
        contentValues.put(Col_2, name);
        contentValues.put(Col_3,quantity);
        contentValues.put(Col_4, price);
        long result = db.insert(Table_Name, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;


    }
    public boolean UpdateData(String name,String quantity,String price,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, id);
        contentValues.put(Col_2, name);
        contentValues.put(Col_3,quantity);
        contentValues.put(Col_4, price);
        db.update(Table_Name,contentValues,"ID = ?",new String[] {id});
        return true;
    }
    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorresult = db.rawQuery("select * from "+ Table_Name,null);
        return cursorresult;

    }
    public Integer DeleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name,"Id = ?",new String[] {id});
    }
    public void DeleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Table_Name);
        db.close();
    }



}
