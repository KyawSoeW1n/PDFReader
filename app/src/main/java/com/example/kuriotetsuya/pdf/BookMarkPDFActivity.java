package com.example.kuriotetsuya.pdf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class BookMarkPDFActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener, View.OnClickListener {
    static PDFView pdfViewer;
    Animation animation;
    DatabaseBookMark dbasebookmark;
    ZoomControls zoomControls;
    TextView textview,taxtViewPageNumber;
    Button btnBookMark;
    ImageButton imgbtnBookMark;
    int pageNum = 0;
    float x, y;
    float maxZoom, minZoom;
    EditText ed, edname;
    RelativeLayout linear;
    static ArrayList<BookMarkData> bookmarkarraylist = new ArrayList();
     int bookmarkPage;
    int newBookMarkPageNumber;
    String s,file,filepath;
    CustomScrollHandler customHandler;
    BookListFragment bookListFragment;
    File selectedFile2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdfactivity);
        pdfViewer = (PDFView) findViewById(R.id.pdfViewer);
        textview = (TextView) findViewById(R.id.tview);
        linear = (RelativeLayout) findViewById(R.id.linear);

        Bundle b=getIntent().getExtras();

        selectedFile2=new File(b.getString("FILESTREET"));
        newBookMarkPageNumber=b.getInt("PAGENUMBER");

        file=b.getString("FILENAME");

        filepath=b.getString("filepath");





        imgbtnBookMark = (ImageButton) findViewById(R.id.imgbtnBookMark);
//        btnBookMark = (Button) findViewById(R.id.btnBookMark);

        zoomControls = (ZoomControls) findViewById(R.id.zoomControls);
        customHandler = new CustomScrollHandler(BookMarkPDFActivity.this);
        bookListFragment = new BookListFragment();
        zoomControls.hide();

        imgbtnBookMark.setOnClickListener(this);
//        btnBookMark.setOnClickListener(this);
        dbasebookmark=new DatabaseBookMark(BookMarkPDFActivity.this);
        dbasebookmark.openDb();
        openPDFViewer();

        //  Bundle b=getIntent().getExtras();

       //  file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + "222.pdf");




        maxZoom = pdfViewer.getMaxZoom();
        minZoom = pdfViewer.getMinZoom();
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = pdfViewer.getScaleX();
                y = pdfViewer.getScaleY();
                if (x == maxZoom && y == maxZoom) {
                    pdfViewer.setScaleX(maxZoom);
                    pdfViewer.setScaleY(maxZoom);
                } else {
                    pdfViewer.setScaleX(x + 1);
                    pdfViewer.setScaleY(y + 1);
                }

            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x = pdfViewer.getScaleX();
                y = pdfViewer.getScaleY();

                if (x == minZoom && y == minZoom) {
                    pdfViewer.setScaleX(minZoom);
                    pdfViewer.setScaleY(minZoom);
                } else {
                    pdfViewer.setScaleX(x - 1);
                    pdfViewer.setScaleY(y - 1);
                }

            }
        });

    }


    public void openPDFViewer() {
        pdfViewer.fromFile(selectedFile2)
                .defaultPage(newBookMarkPageNumber-1)
                .onPageChange(this)
//                .swipeHorizontal(true)
                .enableSwipe(true)
                .enableDoubletap(true)

                .onLoad(this)
                .enableAnnotationRendering(true)
                .scrollHandle(new CustomScrollHandler(this))
//                .onDraw(this)
                .load();
        pdfViewer.setSelected(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_lastpage:
                pageNum++;
                pdfViewer.jumpTo(pageNum);
                break;
            case R.id.action_back:
                pageNum -= 1;
                pdfViewer.jumpTo(pageNum);
//                pdfViewer.getPageAtPositionOffset(pageNum);
                break;
            case R.id.search:
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setCancelable(false);
                View v = getLayoutInflater().inflate(R.layout.alertview, null);
                alertDialog.setView(v);
                ed = (EditText) v.findViewById(R.id.edText);


                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        s = ed.getText().toString();
                        pageNum = Integer.valueOf(s) - 1;
                        pdfViewer.jumpTo(pageNum);
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
                break;
            case R.id.zoominout:
                if (zoomControls.isShown()) {
                    zoomControls.hide();
                } else {
                    zoomControls.show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNum = page;
        textview.setText(String.format("%s %s %s", file,"\n"+ " Page", page + 1, pageCount));
        textview.setSingleLine(true);
        textview.setTextSize(15);
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfViewer.getDocumentMeta();
        printBookMarks(pdfViewer.getTableOfContents(), "-");
    }

    private void printBookMarks(List<PdfDocument.Bookmark> tableOfContents, String s) {
        for (PdfDocument.Bookmark b : tableOfContents) {
            if (b.hasChildren()) {
                printBookMarks(b.getChildren(), s + "-");
            }
        }
    }


//    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//        canvas.drawLine(0,0,pageWidth,0,paint);
//        canvas.drawLine(0,pageHeight,pageWidth,pageHeight,paint);
//        canvas.drawLine(0,0,0,pageHeight,paint);
//        canvas.drawLine(pageWidth,0,pageWidth,pageHeight,paint);
//    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtnBookMark:
                bookmarkPage = pageNum + 1;
                nestedChecking(bookmarkPage, file, filepath);
                break;
          /*  case R.id.btnBookMark:
                pageChanges();
                break;*/

        }


    }

    private void nestedChecking(int bookmarkPage, String file, String filepath) {
        ArrayList<BookMarkData> abc = new ArrayList<>();
        abc = dbasebookmark.getPageNumber();
        int k = 0;

        int size = dbasebookmark.sizz();
        if (size == 0) {
            dbasebookmark.insertData(bookmarkPage, file, filepath);
            size++;
        }
        if (size >= 1) {
            boolean equal=false;
            for (int i = 0; i < abc.size(); i++) {

                if (bookmarkPage == abc.get(i).pageNumber) {
                    equal=true;
                    Toast.makeText(this, "Already Saved", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            if(!equal){
                dbasebookmark.insertData(bookmarkPage, file, filepath);
                Toast.makeText(this, "Add To BookMark", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void pageChanges() {
        Intent ii=new Intent(BookMarkPDFActivity.this,Main.class);
        startActivity(ii);
    }

    protected void onResume() {
        super.onResume();
        dbasebookmark.openDb();
    }

    protected void onPause() {
        super.onPause();
        dbasebookmark.closeDb();
    }


    public void onBackPressed() {
        super.onBackPressed();
        dbasebookmark.openDb();
        pageChanges();
        finish();
    }
}