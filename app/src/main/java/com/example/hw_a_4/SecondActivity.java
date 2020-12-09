package com.example.hw_a_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private Button btngocalendar;
    private TextView txtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtDate = findViewById(R.id.txtDate);

        Intent incoming = getIntent();
        String date = incoming.getStringExtra("date");
        txtDate.setText(date);

        btngocalendar = findViewById(R.id.btngocalendar);
        btngocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, Calendar.class);
                startActivityForResult(intent, 22);
            }

        });

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        String text = getIntent().getStringExtra(MainActivity.LIST_KEY);
        editText.setHint(text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.LIST_KEY, txtDate.getText().toString());
                Log.d("TAG", "listkey SA: " + txtDate.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 & resultCode == RESULT_OK & data != null) {
            String text = data.getStringExtra("date");

            Log.d("TAG", "onActivityResult: " + text);
            txtDate.setText(text);
        }
    }
}