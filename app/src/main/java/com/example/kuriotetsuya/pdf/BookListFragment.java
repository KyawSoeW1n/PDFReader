package com.example.kuriotetsuya.pdf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


public class BookListFragment extends Fragment implements AdapterView.OnItemClickListener {
    //    File[] fileList;
    ArrayList<File> pdfFile;
    ListView listview;
    ProgressBar pb;
//    String filenamelist;

//    BookmarkListAdapter adapter;
    static File file;

    //    static ArrayList<String> fileNameList = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pdf_list, container, false);
        listview = (ListView) v.findViewById(R.id.listView);
        listview.setOnItemClickListener(this);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        pb = v.findViewById(R.id.pb);
        pdfFile = new ArrayList<>();
        pdfFile = getFiles(file);
        FilePickerListAdapter fileadapter = new FilePickerListAdapter(getContext(), pdfFile);
        listview.setAdapter(fileadapter);
        return v;
    }


    private ArrayList<File> getFiles(File file) {
        File fileList[] = file.listFiles();
        if (fileList != null && fileList.length > 0) {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    getFiles(fileList[i]);
                } else {
                    if (fileList[i].getName().endsWith(".pdf")) {
                        pdfFile.add(fileList[i]);
                    }
                }
            }
            pb.setVisibility(View.GONE);
            listview.setVisibility(View.VISIBLE);
        }
        return pdfFile;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String path = pdfFile.get((int) id).getAbsolutePath();
//        openIntent(path);
        File newFile = (File) parent.getItemAtPosition(position);
        if (newFile.isFile()) {
            Intent intent = new Intent(getActivity(), PDFActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("filepath", newFile.getAbsolutePath());
            bundle.putString("filename", newFile.getName());
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }


}
