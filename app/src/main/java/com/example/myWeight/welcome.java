package com.example.myWeight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class welcome extends Activity implements OnClickListener {
    private Button addTodoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        addTodoBtn = (Button) findViewById(R.id.welcomee);
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent main = new Intent(welcome.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
