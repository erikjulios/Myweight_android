package com.example.myWeight;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity implements OnClickListener {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    private Button addTodoBtn,ext;
    EditText et_tinggi;
    EditText et_berat;
    TextView rslt;

    SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        addTodoBtn = (Button) findViewById(R.id.add_record);
        addTodoBtn.setVisibility(View.INVISIBLE);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);

        rslt = findViewById(R.id.keterangan);
        rslt.setVisibility(View.INVISIBLE);

        et_tinggi = findViewById(R.id.etinggi);
        et_berat = findViewById(R.id.eberat);
        Button result = findViewById(R.id.hitung);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = validasi();
                if (status == true){
                    result();
                    addTodoBtn.setVisibility(View.VISIBLE);
                    rslt.setVisibility(View.VISIBLE);
                }
            }
        });
        Button tampil = findViewById(R.id.show);
        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, Lihat_data.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);

            }
        });
        Button btips = findViewById(R.id.btntips);
        btips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main2 = new Intent(MainActivity.this, tips.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main2);

            }
        });
        ext = (Button) this.findViewById(R.id.exit);
        ext.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View arg0) {
                MainActivity.this.finish(); }
        });
//        ext = findViewById(R.id.exit);
//        ext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               finish();
//
//
//            }
//        });

    }
    public void result() {
        String tinggi1 = et_tinggi.getText().toString();
        String berat1 = et_berat.getText().toString();
        int t1 = Integer.parseInt(tinggi1) - 100;
        int ideal = t1 - (t1/10);
        double b1 = Integer.parseInt(berat1);
        if (b1 == ideal){
            rslt.setText("Selamat..!!!\nBerat badan anda sudah ideal");
        }
        else if(b1 < ideal){
            rslt.setText("Berat badan anda kurang ideal,\nsedangkan berat idealnya adalah "+ideal);
        }
        else{
            rslt.setText("Berat badan anda melebihi idealnya,\nsedangkan berat idealnya adalah "+ideal);
        }

    }
    private boolean validasi() {
        if (et_tinggi.getText().toString().isEmpty()){
            et_tinggi.setError("Tinggi tidak boleh kosong.");
            return false;
        }
        if ( et_tinggi.getText().toString().equals("0")){
            et_tinggi.setError("Angka harus lebih dari nol");
            return false;
        }
        if (et_berat.getText().toString().isEmpty()){
            et_berat.setError("Berat tidak boleh kosong.");
            return false;
        }
        if ( et_berat.getText().toString().equals("0")){
            et_berat.setError("Angka harus lebih dari nol");
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_record:

                final String tinggi = et_tinggi.getText().toString();
                final String berat = et_berat.getText().toString();
                final String ket = rslt.getText().toString();

                String tanggalinput = date.format(c.getTime());
                dbManager.insert(tanggalinput,"", tinggi,berat,ket );
                Toast.makeText(getApplicationContext(), "Data disimpan",Toast.LENGTH_LONG).show();
                addTodoBtn.setVisibility(View.INVISIBLE);
                rslt.setVisibility(View.INVISIBLE);
                et_tinggi.setText("");
                et_berat.setText("");
                break;
    }
}
}
