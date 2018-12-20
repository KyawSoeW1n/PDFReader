package com.example.kuriotetsuya.pdf;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fg=null;
    switch (position){

        case 0:
            fg=new BookListFragment();
            break;
        case 1:
            fg=new Bookmark_ListFrag();
            break;

    }
        return fg;
    }


    @Override
    public int getCount() {
        return 2;
    }
}
