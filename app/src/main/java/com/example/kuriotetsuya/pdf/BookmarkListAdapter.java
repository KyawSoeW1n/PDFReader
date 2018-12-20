package com.example.kuriotetsuya.pdf;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class BookmarkListAdapter extends BaseAdapter {
    ArrayList arraylist=new ArrayList();
    ArrayList   bookNameList=new ArrayList();
    Context context;
    int bookmark;
    PDFActivity pdfActivity;

//    public BookmarkListAdapter(ArrayList arraylist, Context context) {
//        this.arraylist = arraylist;
//        this.context = context;
//    }

    public BookmarkListAdapter(Context context, int bookmark, ArrayList arraylist, ArrayList bookNameList) {
        this.context=context;
        this.bookmark=bookmark;
        this.arraylist=arraylist;
        this.bookNameList=bookNameList;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.pdflistviewlayout,null);
        TextView textViewBookName= (TextView) convertView.findViewById(R.id.textViewList);
        TextView textViewPageNumber= (TextView) convertView.findViewById(R.id.textViewPageNumber);

        textViewBookName.setText(bookNameList.get(position).toString());
        textViewPageNumber.setText("PageNumber "+arraylist.get(position).toString());
        return convertView;
    }
}
