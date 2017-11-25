package com.umarfadil.notes;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.umarfadil.notes.helper.SqliteHelper;

public class AddActivity extends AppCompatActivity {

    EditText editJudul, editIsi;
    Button btn_simpan;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sqliteHelper = new SqliteHelper(this);

        editJudul       = (EditText) findViewById(R.id.editJudul);
        editIsi         = (EditText) findViewById(R.id.editIsi);
        btn_simpan      = (Button) findViewById(R.id.btnSimpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editJudul.getText().toString().equals("") || editIsi.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Isi data dengan benar",
                            Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase database = sqliteHelper.getWritableDatabase();
                    database.execSQL("INSERT INTO catatan(judul, catatan) VALUES('" +
                            editJudul.getText().toString()  + "','" + editIsi.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Catatan berhasil disimpan", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah");

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
