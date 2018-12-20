/*
package com.example.kuriotetsuya.pdf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;


public class List_PDF extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listview;
    File[] fileList;
    String pdfList[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_list);
        listview= (ListView) findViewById(R.id.listView);
//        CustomListAdapter adapter=new CustomListAdapter(getApplicationContext(),arraylist);
        listview.setOnItemClickListener(this);
        File files= Environment.getExternalStorageDirectory();
        fileList=files.listFiles(new FilenameFilter() {

            public boolean accept(File dir, String filename) {
                return (filename.endsWith(".pdf"));
            }
        });
        pdfList=new String[fileList.length];
        for(int i=0;i<fileList.length;i++){
            pdfList[i]=fileList[i].getName();
        }
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,fileList);
        listview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String path=fileList[((int) id)].getAbsolutePath();
                openPdfIntent(path);
    }

    private void openPdfIntent(String path) {
        Intent intet=new Intent(List_PDF.this,PDFActivity.class);
        startActivity(intet);
    }
}
*/
