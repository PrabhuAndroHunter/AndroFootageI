package com.pub.assignment.util;

import android.graphics.Bitmap;

/**
 * Created by pub on 23-Apr-17.
 */

public class MyContactData {

    private String name;
    private String phNumber;

    public MyContactData(String name, String phNumber) {
        this.name = name;
        this.phNumber = phNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhNumber() {
        return phNumber;
    }
}
