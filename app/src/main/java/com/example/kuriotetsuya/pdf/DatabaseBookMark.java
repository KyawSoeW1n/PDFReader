package com.example.kuriotetsuya.pdf;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import junit.runner.Version;

import java.util.ArrayList;


public class DatabaseBookMark {

    Dbase dbase;
    SQLiteDatabase sqLiteDatabase;
    Context context;
     ArrayList<BookMarkData>pagenumList;

    public DatabaseBookMark(Context context) {
        this.context = context;
    }

    public void openDb(){
        dbase=new Dbase(context);
        sqLiteDatabase=dbase.getWritableDatabase();
    }
    public void insertData(int pageNumber,String bookName,String filepath){
        sqLiteDatabase.execSQL("INSERT INTO "+Dbase.TB_NAME+" VALUES('"+pageNumber+"','"+bookName+"','"+filepath+"');'");
    }
    public void closeDb(){
        dbase.close();
    }

    public ArrayList<BookMarkData> getPageNumber(){
        pagenumList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(Dbase.TB_NAME,null,null,null,null,null,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int pageNumberId=cursor.getColumnIndex(Dbase.PAGENUM);
            int bookNameId=cursor.getColumnIndex(Dbase.BOOKNAME);
            int filePathId=cursor.getColumnIndex(Dbase.FILEPATH);
            pagenumList.add(new BookMarkData(cursor.getInt(pageNumberId),
                                             cursor.getString(bookNameId),
                                             cursor.getString(filePathId)));
        }
        return pagenumList;
    }



public ArrayList<BookMarkData> getFilePath(){
    ArrayList<BookMarkData> filepath=new ArrayList<>();
    Cursor cursor=sqLiteDatabase.query(Dbase.TB_NAME,null,null,null,null,null,null);
    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
        int filePathhId=cursor.getColumnIndex(Dbase.FILEPATH);
        filepath.add(new BookMarkData(cursor.getString(filePathhId)));
    }
    return  filepath;
}

    public int sizz() {

        return dbase.getProfileCount();
    }


    public static class Dbase extends SQLiteOpenHelper {
        static final String PAGENUM = "pagenumber";
        static final String TB_NAME = "BookMark1";
        static final String DB_NAME = "db_Bookmark";
        static final String BOOKNAME= "bookname";
        static  final  String FILEPATH="filepath";
        static final int VERSION=1;
        static final String TABLE_CREATE = "CREATE TABLE " +
                TB_NAME + "(" +
                PAGENUM+ " INTEGER(10),"+
                BOOKNAME+ " VARCHAR(30),"+
                FILEPATH+ " VARCHAR(100));";

        Context context;

       public Dbase(Context context){
           super(context,DB_NAME,null, VERSION);
           this.context=context;
       }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop Table If Exists" + TB_NAME);
            onCreate(db);
            Toast.makeText(context, "TB Upgrade", Toast.LENGTH_SHORT).show();
        }

        public int getProfileCount() {
            String countQuery="SELECT * FROM "+TB_NAME;
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(countQuery,null);
            int cnt=cursor.getCount();
            cursor.close();
            return cnt;
        }
    }



}
