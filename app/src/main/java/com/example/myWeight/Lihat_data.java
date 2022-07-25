package com.example.myWeight;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.pm.ActivityInfo;

public class Lihat_data extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.TANGGAL1, DatabaseHelper.TANGGAL2 , DatabaseHelper.TINGGI, DatabaseHelper.BERAT, DatabaseHelper.KET };

    final int[] to = new int[] { R.id.id, R.id.tanggalinput,R.id.tanggalubah, R.id.tinggi, R.id.berat, R.id.keterangan };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lihatdata);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView tanggal1TextView = (TextView) view.findViewById(R.id.tanggalinput);
                TextView tanggal2TextView = (TextView) view.findViewById(R.id.tanggalubah);
                TextView tinggiTextView = (TextView) view.findViewById(R.id.tinggi);
                TextView beratTextView = (TextView) view.findViewById(R.id.berat);
                TextView keteranganTextView = (TextView) view.findViewById(R.id.keterangan);

                String id = idTextView.getText().toString();
                String tanggal1 = tanggal1TextView.getText().toString();
                String tanggal2 = tanggal2TextView.getText().toString();
                String tinggi = tinggiTextView.getText().toString();
                String berat = beratTextView.getText().toString();
                String keterangan = keteranganTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), Ubah_data.class);
                modify_intent.putExtra("tanggalinput", tanggal1);
                modify_intent.putExtra("tanggalubah", tanggal2);
                modify_intent.putExtra("tinggi", tinggi);
                modify_intent.putExtra("berat", berat);
                modify_intent.putExtra("keterangan", keterangan);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, MainActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}