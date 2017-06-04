package com.gopnick.nekitpc.sqlite_crud;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyLog";
    private EditText edtId, edtFirstName, edtLastName, edtAddress, edtSalary;
    private Button btnInsert, btnUpdate, btnDelete, btnLoadAll;
    private TextView txtData;

    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(MainActivity.this);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                long resultInsert =
                        dataBase.insert(getValue(edtFirstName),
                                getValue(edtLastName),
                                getValue(edtAddress),
                                Double.valueOf(getValue(edtSalary)));
                if (resultInsert == -1) {
                    Log.d(TAG, "Some error");
                } else {
                    Log.d(TAG, "Data inserted successfully, ID = " + resultInsert);
                }
                break;

            case R.id.btnUpdate:
                long resultUpdate = dataBase.update(
                        Integer.parseInt(getValue(edtId)),
                        getValue(edtFirstName),
                        getValue(edtLastName),
                        getValue(edtAddress),
                        Double.valueOf(getValue(edtSalary)));
                if (resultUpdate == 0) {
                    Log.d(TAG, "Some error");
                } else if (resultUpdate == 1) {
                    Log.d(TAG, "Data updated successfully, ID = " + resultUpdate);
                } else {
                    Log.d(TAG, "Some error, multiply records updated");
                }
                break;

            case R.id.btnDelete:
                long resultDelete = dataBase.delete(Integer.parseInt(getValue(edtId)));
                if (resultDelete == 0) {
                    Log.d(TAG, "Some error");
                } else {
                    Log.d(TAG, "Data deleted successfully");
                }
                break;

            case R.id.btnLoadAll:
                StringBuffer sb = new StringBuffer();
                Cursor cursor = dataBase.getAllRecords();

                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    sb.append(cursor.getInt(cursor.getColumnIndex(DataBase.ID)));
                    sb.append(" - ");
                    sb.append(cursor.getString(cursor.getColumnIndex(DataBase.FIRST_NAME)));
                    sb.append(" - ");
                    sb.append(cursor.getString(cursor.getColumnIndex(DataBase.LAST_NAME)));
                    sb.append(" - ");
                    sb.append(cursor.getString(cursor.getColumnIndex(DataBase.ADDRESS)));
                    sb.append(" - ");
                    sb.append(cursor.getDouble(cursor.getColumnIndex(DataBase.SALARY)));
                    sb.append("\n");
                }
                txtData.setText(sb);
                break;
        }
    }

    private String getValue(EditText edtText) {
        return edtText.getText().toString().trim();
    }

    // метод инициализации всех виджетов
    private void init() {
        edtId = (EditText) findViewById(R.id.edtID);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtSalary = (EditText) findViewById(R.id.edtSalary);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnLoadAll = (Button) findViewById(R.id.btnLoadAll);

        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnLoadAll.setOnClickListener(this);

        txtData = (TextView) findViewById(R.id.txtData);
    }

    // Вызываем метод открытия БД на нашем экземпляре при старте активности
    @Override
    protected void onStart() {
        super.onStart();
        dataBase.openDB();
    }

    // Вызываем метод закрытия БД на нашем экземпляре когда окно становится невидимым
    @Override
    protected void onStop() {
        super.onStop();
        dataBase.closeDB();
    }
}
