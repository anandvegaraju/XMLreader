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
import android.widget.Toast;

import java.io.File;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private Button selectbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectbutton = (Button)findViewById(R.id.select_xml_button);
        selectbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new FileChooser(MainActivity.this).setFileListener(new FileChooser.FileSelectedListener() {
                            @Override public void fileSelected(final File file) {
                                // do something with the file
                                Intent gotonext = new Intent(MainActivity.this,DisplayXMLfile.class);
                                gotonext.putExtra("filepath",file.getAbsolutePath().toString());
                                startActivity(gotonext);

                            }}).showDialog();

                    }
                }
        );





    }



}

