package com.example.hw_a_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        String text = getIntent().getStringExtra(MainActivity.LIST_KEY);
        editText.setText(text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.LIST_KEY,editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}