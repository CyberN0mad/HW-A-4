package com.example.hw_a_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {

    public RecyclerView recyclerView;
    private MainAdapter adapter;
    public List<String> list;
    TextView textView;

    AlertDialog.Builder builder;

    public static final String LIST_KEY = "list_key";
    public static final int REQUEST = 50;
    public static final int REQUEST2 = 2;
    int position;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onCreateDialog(savedInstanceState);


    }

    private void init() {
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(itemDecoration);


        for (int i = 1; i < 21; i++) {
            list.add("Task #" + i + "      -Write some notes...");

        }

        adapter = new MainAdapter(list, this, this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);


    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
            | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int positionDrag = viewHolder.getAdapterPosition();
            int positionTarget = target.getAdapterPosition();
            Collections.swap(adapter.list, positionDrag, positionTarget);
            adapter.notifyItemMoved(positionDrag, positionTarget);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.list.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());


        }


    };

    @Override
    public void openActivity(int position) {
        this.position = position;

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(LIST_KEY, list.get(position));

        startActivityForResult(intent, REQUEST);
    }

    @Override
    public void onDelete(int position) {
        Toast.makeText(this, "Delete     " + position, Toast.LENGTH_SHORT).show();
        this.position = position;
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST == requestCode & resultCode == RESULT_OK & data != null) {
            String text = data.getStringExtra(LIST_KEY);
            Log.d("TAG", "onActivityResult: "+text);
            adapter.list.set(position, text);
            adapter.notifyDataSetChanged();
        }
    }




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want delelte?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        adapter.list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}