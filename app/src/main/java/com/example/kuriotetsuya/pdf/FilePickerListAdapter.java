package com.example.kuriotetsuya.pdf;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class FilePickerListAdapter extends ArrayAdapter {
    private List<File> mObjects;

    public FilePickerListAdapter(Context context, List<File> objects) {

        super(context, R.layout.list_item, android.R.id.text1, objects);
        mObjects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.list_item, parent, false);
        } else
            row = convertView;

        File object = mObjects.get(position);

        ImageView imageView = (ImageView) row.findViewById(R.id.file_picker_image);
        TextView textView = (TextView) row.findViewById(R.id.file_picker_text);
        textView.setSingleLine(true);
        textView.setText(object.getName());


        if (object.isFile()) {
            imageView.setImageResource(R.drawable.pdficon);
            //  textPath.setText(object.getAbsolutePath());
        } else
            imageView.setImageResource(R.drawable.pdficon);

        return row;
    }
}