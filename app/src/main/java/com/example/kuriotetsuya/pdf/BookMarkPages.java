package com.example.kuriotetsuya.pdf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;


public class BookMarkPages extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listviewBookMarkpages;
    PDFView pdf;
    PDFActivity pdfActivity;

    ArrayList arraylist=new ArrayList();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarkpages);
        listviewBookMarkpages= (ListView) findViewById(R.id.listviewBookMarkpages);
//        SharedPreferences sharedPreferences=getSharedPreferences(PDFActivity.SHARE_PREF,MODE_APPEND);
        pdfActivity=new PDFActivity();
       /* for(int i=0;i<PDFActivity.bookmarkarraylist.size();i++)
        {
            Toast.makeText(this, "Arrayinde"+PDFActivity.bookmarkarraylist.get(i), Toast.LENGTH_SHORT).show();

        }*/

        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,PDFActivity.bookmarkarraylist);
        listviewBookMarkpages.setAdapter(adapter);

        listviewBookMarkpages.setOnItemClickListener(this);
        }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int index= (int) arraylist.get(position);
        pdf.jumpTo(index);

    }
}
