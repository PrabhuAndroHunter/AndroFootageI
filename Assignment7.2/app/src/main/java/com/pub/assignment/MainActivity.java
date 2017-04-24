package com.pub.assignment;


import java.util.ArrayList;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.pub.assignment.adapter.MyAdapter;
import com.pub.assignment.util.MyContactData;

public class MainActivity extends AppCompatActivity {
    ListView list;
    LinearLayout ll;
    Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LinearLayout) findViewById(R.id.LinearLayout1);

        list = (ListView) findViewById(R.id.listView1);


        loadBtn = (Button) findViewById(R.id.button1);
        loadBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                LoadContactsAyscn lca = new LoadContactsAyscn();
                lca.execute();
            }
        });

    }

    class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<MyContactData>> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = ProgressDialog.show(MainActivity.this, "Loading Contacts",
                    "Please Wait");
        }

        @Override
        protected ArrayList<MyContactData> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ArrayList<MyContactData> contactsList = new ArrayList<MyContactData>();

            Cursor c = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            while (c.moveToNext()) {

                String contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(new MyContactData(contactName, phNumber));
            }
            c.close();

            return contactsList;
        }

        @Override
        protected void onPostExecute(ArrayList<MyContactData> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);

            MyContactData[] contactArray;

            pd.cancel();
            ll.removeView(loadBtn);

            contactArray = new MyContactData[contacts.size()];
            contactArray = contacts.toArray(contactArray);
            MyAdapter adapter = new MyAdapter(getBaseContext(), contactArray);
            list.setAdapter(adapter);
        }
    }
}