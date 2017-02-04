package com.example.anand.xmlreader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button selectbutton;
    private EditText tag_text, parent_node;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tag_text = (EditText)findViewById(R.id.taglist);
        parent_node = (EditText)findViewById(R.id.parentnode);
        selectbutton = (Button)findViewById(R.id.select_xml_button);

        selectbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new FileChooser(MainActivity.this).setFileListener(new FileChooser.FileSelectedListener() {
                            @Override public void fileSelected(final File file) {

                                Intent gotonext = new Intent(MainActivity.this,DisplayXMLfile.class);
                                gotonext.putExtra("filepath",file.getAbsolutePath().toString());
                                gotonext.putExtra("parent",parent_node.getText().toString());
                                gotonext.putExtra("tags",tag_text.getText().toString());
                                startActivity(gotonext);

                            }}).showDialog();

                    }
                }
        );





    }



}

