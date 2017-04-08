package com.chajs226.cardmemory;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by junseong on 2017-03-18.
 */

public class ListViewItem {
    private Drawable iconDrawable ;
    private String mUpdt ;
    private String mContents ;
    private int mId;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setUpdt(String date) {
        mUpdt = date ;
    }
    public void setContents(String contents) {
        mContents = contents ;
    }
    public void setId(int id) {
        mId = id ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getUpdt() {
        return this.mUpdt ;
    }
    public String getContents() {
        return this.mContents ;
    }
    public int getId() {
        return this.mId ;
    }
}
