package com.umarfadil.notes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.umarfadil.notes.helper.SqliteHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView lstNotes;

    SqliteHelper sqliteHelper;
    Cursor cursor;

    ArrayList<HashMap<String, String>> catatans = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sqliteHelper = new SqliteHelper(this);
        //NotesAdapter();

        lstNotes = (ListView) findViewById(R.id.lstNotes);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity.this, AddActivity.class ));
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        NotesAdapter();
    }

    private void NotesAdapter(){

        catatans.clear(); // list_kas.setAdapter(null);

        SQLiteDatabase database = sqliteHelper.getReadableDatabase();
        cursor = database.rawQuery( "SELECT * FROM catatan", null);
        cursor.moveToFirst();

        int i;
        for (i=0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);

            //Toast.makeText(getApplicationContext(), "catatan: " + cursor.getString(2), Toast.LENGTH_LONG).show();

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("judul",       cursor.getString(1) );
            map.put("isi",       cursor.getString(2) );
            catatans.add(map);
        }

        if (i == 0){
            Toast.makeText(getApplicationContext(), "Tidak ada catatan untuk ditampilkan",
                    Toast.LENGTH_LONG).show();
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, catatans, R.layout.adapter_main,
                new String[] { "judul", "isi"},
                new int[] {R.id.edtJudul, R.id.editIsi});

        lstNotes.setAdapter(simpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
