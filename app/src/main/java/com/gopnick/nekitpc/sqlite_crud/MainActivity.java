package com.gopnick.nekitpc.sqlite_crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtId, edtFirstName, edtLastName, edtAddress, edtSalary;
    Button btnInsert, btnUpdate, btnDelete, btnLoadAll;
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = (EditText) findViewById(R.id.edtID);
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtSalary = (EditText) findViewById(R.id.edtSalary);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnLoadAll = (Button) findViewById(R.id.btnLoadAll);

        txtData = (TextView) findViewById(R.id.txtData);
    }
}
