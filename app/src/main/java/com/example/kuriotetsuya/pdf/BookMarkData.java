package com.example.kuriotetsuya.pdf;

import java.io.File;


public class BookMarkData {
    int pageNumber;
    String bookName;
    String filePath;
    public BookMarkData(int pageNumber,String bookName,String filePath) {
        this.pageNumber=pageNumber;
        this.bookName=bookName;
        this.filePath=filePath;
    }

    public BookMarkData(String string) {
        this.filePath=string;
    }


    public String getFilePath(){
        return  filePath;
    }

    public int getPageNumber(){return pageNumber; }

    public String getBookName() {
        return bookName;
    }

}
