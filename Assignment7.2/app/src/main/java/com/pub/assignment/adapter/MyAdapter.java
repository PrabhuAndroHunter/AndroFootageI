package com.pub.assignment.adapter;

/**
 * Created by pub on 27-Oct-16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pub.assignment.R;
import com.pub.assignment.util.MyContactData;

public class MyAdapter extends BaseAdapter {

    MyContactData[] contact;
    Context context;
    LayoutInflater inflter;

    public MyAdapter(Context context, MyContactData[] contact) {
        this.context = context;
        this.contact = contact;
        inflter = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return contact.length;
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
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflter.inflate(R.layout.contact_item, null);
        }

        final MyContactData mContact = contact[position];

        TextView mNameTv = (TextView) view.findViewById(R.id.textView_name);
        TextView mPhoneNumberTv = (TextView) view.findViewById(R.id.textView_phonenumber);

        mNameTv.setText(mContact.getName());
        mPhoneNumberTv.setText(mContact.getPhNumber());
        return view;
    }
}
