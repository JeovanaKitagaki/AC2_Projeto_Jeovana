package com.example.projeto_1_jeovana_;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private EditText editTextValue;
    private EditText editTextDate;
    private Spinner spinnerPriority;
    private Button buttonAddTask;
    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.txt_normal_1);
        editTextValue = findViewById(R.id.txt_number_1);
        editTextDate = findViewById(R.id.txt_data_1);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        buttonAddTask = findViewById(R.id.button_1);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(tasks);
        recyclerViewTasks.setAdapter(taskAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priorities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(adapter);

        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String priority = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Priority selected: " + priority, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = editTextTask.getText().toString().trim();
                String value = editTextValue.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();

                if (!task.isEmpty() && !value.isEmpty() && !date.isEmpty()) {
                    tasks.add(new Task(task, value, "", date, false));
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.setText("");
                    editTextValue.setText("");
                    editTextDate.setText("");
                    showTaskAddedDialog(task);
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showTaskAddedDialog(String task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tarefa adicionada: " + task)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}