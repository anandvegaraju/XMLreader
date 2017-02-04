package com.example.anand.xmlreader;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Anand on 04-02-2017.
 */

public class DisplayXMLfile extends AppCompatActivity {

    TextView tv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_display_activity);



        Intent intent = getIntent();
        String parentnode = intent.getStringExtra("parent");
        String tagnames = intent.getStringExtra("tags");
        String filepath  = intent.getStringExtra("filepath");
        List<String> taglist = Arrays.asList(tagnames.split(";"));
        int no_tags = taglist.size();

        String filecontent = readFileAsString(filepath);
        String displaytext = "";


        tv1=(TextView)findViewById(R.id.textView);


        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(filecontent));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName(parentnode);

            // iterate the employees
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                NodeList name = element.getElementsByTagName(taglist.get(0));
                Element line = (Element) name.item(0);
                displaytext += taglist.get(0) + " : " + getCharacterDataFromElement(line) + "\n";

                for (int j = 1; j < no_tags ; j++) {
                    name = element.getElementsByTagName(taglist.get(j));
                    line = (Element) name.item(0);
                    displaytext += taglist.get(j) + " : " + getCharacterDataFromElement(line) + "\n";

                }

                displaytext += "-------------------------------------------------------\n";





            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        tv1.setText(displaytext);




    }




    public static String readFileAsString(String filePath) {
        String result = "";
        File file = new File(filePath);
        if ( file.exists() ) {
            FileInputStream fis = null;
            try {


                fis = new FileInputStream(file);
                char current;
                while (fis.available() > 0) {
                    current = (char) fis.read();
                    result = result + String.valueOf(current);
                }
            } catch (Exception e) {
                Log.d("vtusucks", e.toString());
            } finally {
                if (fis != null)
                    try {
                        fis.close();
                    } catch (IOException ignored) {
                    }
            }
        }
        return result;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}

