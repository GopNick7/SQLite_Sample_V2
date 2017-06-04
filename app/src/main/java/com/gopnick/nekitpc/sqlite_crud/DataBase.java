package com.gopnick.nekitpc.sqlite_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {

    private static final String DBNAME = "mydbq";
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "employees";
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ADDRESS = "address";
    public static final String SALARY = "salary";

    private SQLiteDatabase sqLiteDatabase;

    public DataBase(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryTable = "CREATE TABLE " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRST_NAME + " TEXT NOT NULL, " +
                LAST_NAME + " TEXT NOT NULL, " +
                ADDRESS + " TEXT NOT NULL, " +
                SALARY + " REAL NOT NULL " +
                ")";

        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Открывается база
    public void openDB() {
        sqLiteDatabase = getWritableDatabase();
    }

    // Закорывается база
    public void closeDB() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    // Добавляем данные в базу
    public long insert(String fName, String lName, String address, Double salary) {
        ContentValues cv = new ContentValues();

        cv.put(FIRST_NAME, fName);
        cv.put(LAST_NAME, lName);
        cv.put(ADDRESS, address);
        cv.put(SALARY, salary);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv);
    }

    // Обновляем данные в базе
    public long update(int id, String fName, String lName, String address, Double salary) {
        ContentValues cv = new ContentValues();

        cv.put(FIRST_NAME, fName);
        cv.put(LAST_NAME, lName);
        cv.put(ADDRESS, address);
        cv.put(SALARY, salary);

        String where = ID + " = " + id;

        return sqLiteDatabase.update(TABLE_NAME, cv, where, null);
    }

    // Удаляем данные из базы
    public long delete(int id) {
        String where = ID + " = " + id;

        return sqLiteDatabase.delete(TABLE_NAME, where, null);
    }

    // Показываем все данные в базе
    public Cursor getAllRecords() {

//        sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);

        String query = "SELECT * FROM " + TABLE_NAME;
        return sqLiteDatabase.rawQuery(query, null);
    }
}