package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText commentName;
    private EditText commentDescription;
    private EditText showCommentName;
    private EditText showCommentBody;

    private Spinner spinner;

    private Button createButton;
    private Button showButton;
    private Button deleteButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.databaseHelper = new DatabaseHelper(this);

        this.commentName = findViewById(R.id.new_comment);
        this.commentDescription = findViewById(R.id.text_new_comment);
        this.showCommentName = findViewById(R.id.show_comment);
        this.showCommentBody = findViewById(R.id.comment_body);

        this.spinner = findViewById(R.id.comments_spinner);


        updateComments();

        this.createButton = findViewById(R.id.create);
        this.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = commentName.getText().toString();
                String description = commentDescription.getText().toString();

                if (!name.equals("") && !description.equals("")) {
                    Comment comment = new Comment(name, description);
                    databaseHelper.addComment(comment);
                    updateComments();
                }

                commentName.setText("");
                commentDescription.setText("");
            }
        });

        this.showButton = findViewById(R.id.show);
        this.showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem() != null) {
                    ArrayList<Comment> comments = databaseHelper.getComments();
                    String comment = spinner.getSelectedItem().toString();
                    boolean finish = false;
                    int i = 0;
                    while (!finish) {
                        if (comments.get(i).getName().equals(comment)) {
                            showCommentName.setText(comments.get(i).getName());
                            showCommentBody.setText(comments.get(i).getDescription());
                            finish = true;
                        }
                        i++;
                    }
                }
            }
        });

        this.deleteButton = findViewById(R.id.delete);
        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem() != null) {
                    String comment = spinner.getSelectedItem().toString();
                    databaseHelper.removeComment(comment);
                    updateComments();
                    showCommentName.setText("");
                    showCommentBody.setText("");
                }
            }
        });
    }

    public void updateComments() {
        ArrayList<Comment> comments = this.databaseHelper.getComments();
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            names.add(comments.get(i).getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(arrayAdapter);
    }
}