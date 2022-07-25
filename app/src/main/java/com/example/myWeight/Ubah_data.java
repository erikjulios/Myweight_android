package com.example.myWeight;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ubah_data extends Activity implements OnClickListener {
    private EditText tinggiText;
    private EditText beratText;
    private Button updateBtn, deleteBtn;
    private long _id;
    private DBManager dbManager;
    private SimpleCursorAdapter adapter;
    SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Data");
        setContentView(R.layout.ubahdata);
        dbManager = new DBManager(this);
        dbManager.open();

        tinggiText = (EditText) findViewById(R.id.tinggi_edittext);
        beratText = (EditText) findViewById(R.id.berat_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String tinggi = intent.getStringExtra("tinggi");
        String berat = intent.getStringExtra("berat");

        _id = Long.parseLong(id);
        tinggiText.setText(tinggi);
        beratText.setText(berat);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String tinggi = tinggiText.getText().toString();
                String berat = beratText.getText().toString();
                String keterangan = "";
                int t1 = Integer.parseInt(tinggi) - 100;
                int ideal = t1 - (t1/10);
                double b1 = Integer.parseInt(berat);
                if (b1 == ideal){
                     keterangan = "Selamat..!!!\nBerat badan anda sudah ideal";
                }
                else if(b1 < ideal){
                     keterangan = "Maaf\nBerat badan anda kurang ideal,\nsedangkan berat idealnya adalah "+ideal;
                }
                else{
                     keterangan = "Maaf\nBerat badan anda melebihi idealnya,\nsedangkan berat idealnya adalah "+ideal;

                }

                dbManager = new DBManager(this);
                dbManager.open();
                Cursor cursor = dbManager.ambiltgl();
                String tanggalubah = date.format(c.getTime());
                dbManager.update(_id,cursor.getString(1),tanggalubah,tinggi, berat,keterangan);
                this.returnHome();
                Toast.makeText(getApplicationContext(), "Data Diubah",Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                Toast.makeText(getApplicationContext(), "Data Dihapus",Toast.LENGTH_LONG).show();

                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), Lihat_data.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
