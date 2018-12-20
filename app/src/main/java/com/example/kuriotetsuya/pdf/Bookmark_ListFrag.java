package com.example.kuriotetsuya.pdf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;


public class Bookmark_ListFrag extends Fragment implements AdapterView.OnItemClickListener{
    ListView bookMarkListView;
    ArrayList arraylist = new ArrayList();
    ArrayList bookNameList=new ArrayList();
    DatabaseBookMark databasebookmark;
    PDFActivity pdfActivity=new PDFActivity();
    ArrayList<BookMarkData> list = null;
    ArrayList<BookMarkData> filepath=null;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bookmark, container, false);
        bookMarkListView = (ListView) v.findViewById(R.id.bookmarklistView);
        bookMarkListView.setOnItemClickListener(this);
        databasebookmark = new DatabaseBookMark(getActivity());
        databasebookmark.openDb();
        list = databasebookmark.getPageNumber();
        filepath=databasebookmark.getFilePath();
        return v;
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 0; i < list.size(); i++) {
            arraylist.add(list.get(i).pageNumber);
            bookNameList.add(list.get(i).bookName);

        }
        BookmarkListAdapter adapter = new BookmarkListAdapter(getContext(), R.layout.pdf_list, arraylist,bookNameList);
        bookMarkListView.setAdapter(adapter);

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //int index = (int) arraylist.get(position);
       // File f = (File) parent.getItemAtPosition(position);
        File  path=new File(filepath.get(position).filePath);

        Bundle bdl=new Bundle();
        bdl.putString("FILESTREET", path.getAbsolutePath());
        bdl.putInt("PAGENUMBER", (Integer) arraylist.get(position));
        bdl.putString("FILENAME", (String) bookNameList.get(position));
        Intent intent=new Intent(getActivity(),BookMarkPDFActivity.class);
        intent.putExtras(bdl);
        startActivity(intent);
        }



}

