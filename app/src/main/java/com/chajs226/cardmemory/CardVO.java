package com.chajs226.cardmemory;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by junseong on 2017-03-18.
 */

public class CardVO {

    private int id;
    private String kind;
    private String contents ;
    private String updt ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUpdt() {
        return updt;
    }

    public void setUpdt(String updt) {
        this.updt = updt;
    }





}
