package com.intent.mylibrary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intent.mylibrary.R;
import com.intent.mylibrary.database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnStore, btnGet;
    private EditText edtName;
    private DatabaseHelper databaseHelper;
    private TextView tvNames;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        tvNames = findViewById(R.id.tvNames);

        btnStore = findViewById(R.id.btnStore);
        btnStore.setOnClickListener(this);
        btnGet = findViewById(R.id.btnGet);
        btnGet.setOnClickListener(this);
        edtName = findViewById(R.id.edtName);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnStore:
                actionStore();
                break;
            case R.id.btnGet:
                actionGet();
                break;
        }

    }

    private void actionStore() {
        databaseHelper.addStudentDetail(edtName.getText().toString());
        edtName.setText("");
        Toast.makeText(this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
    }

    private void actionGet() {
        arrayList = databaseHelper.getAllStudentsList();
        tvNames.setText("");
        for (int i = 0; i < arrayList.size(); i ++){
            tvNames.setText(tvNames.getText().toString()+", "+arrayList.get(i));
        }
    }
}
