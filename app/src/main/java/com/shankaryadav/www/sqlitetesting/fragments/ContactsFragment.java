package com.shankaryadav.www.sqlitetesting.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shankaryadav.www.sqlitetesting.Adapters.CategoryAdapter;
import com.shankaryadav.www.sqlitetesting.Adapters.ContactAdapter;
import com.shankaryadav.www.sqlitetesting.FeedReaderContract;
import com.shankaryadav.www.sqlitetesting.R;
import com.shankaryadav.www.sqlitetesting.pojo.Category_pojo;
import com.shankaryadav.www.sqlitetesting.pojo.Contacts_pojo;
import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends Fragment {

    Activity activity;
    List<Contacts_pojo> list = new ArrayList<> ();
    RecyclerView recyclerView;
    FeedReaderContract feedReaderContract;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate (R.layout.fragment_contacts,container,false);

        feedReaderContract = new FeedReaderContract (getActivity ());
        activity = getActivity ();
        checkpermission ();
        getContactList ();

        recyclerView = view.findViewById (R.id.contacts_recyclerview);

        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (activity));
        recyclerView.setItemAnimator (new DefaultItemAnimator ());

        recyclerView.addItemDecoration (new DividerItemDecoration (activity,DividerItemDecoration.VERTICAL));


        recyclerView.setAdapter (new ContactAdapter (list,activity));


        return  view;
    }

    public void checkpermission(){
        if ((ContextCompat.checkSelfPermission (activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission (activity, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission (activity, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions (activity,new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS}, 1);
        }else{
            Toast.makeText (activity, "permission not granted", Toast.LENGTH_SHORT).show ();
        }
    }

    private void getContactList() {



        try {

            Gson gson =  new Gson();

            ContentResolver cr = activity.getApplicationContext ().getContentResolver ();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);

            if ((cur != null ? cur.getCount() : 0) > 0) {
                while (cur != null && cur.moveToNext()) {
                    String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));


                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));

                            Contacts_pojo contacts_pojo = new Contacts_pojo ();

                            contacts_pojo.setName (name);
                            contacts_pojo.setNo (phoneNo);
                            contacts_pojo.setImgurl (getPhotoUri ());


                            try{

                                String toStoreObject = gson.toJson(contacts_pojo, Contacts_pojo.class);




                                feedReaderContract.insertNewTask (toStoreObject);

                            }catch (IllegalStateException| JsonSyntaxException e){
                                e.printStackTrace ();

                            }





                            Log.i("TAG----", "Name: " + name);
                            Log.i("TAG----",  "Phone Number: " + phoneNo);
                        }
                        pCur.close();
                    }
                }
            }


            if(cur!=null){
                cur.close();
            }

            try{

                List<String> contacts_pojoArrayList = feedReaderContract.getTaskList ();
                Log.i ("INFO------",""+contacts_pojoArrayList.size ());

                
                for (String model: contacts_pojoArrayList){

                    Contacts_pojo cpojo = gson.fromJson (model,Contacts_pojo.class);



                    list.add (cpojo);


                }

            }catch (IllegalStateException | JsonSyntaxException e){
                e.printStackTrace ();
            }



        }catch (SecurityException e){
            e.printStackTrace ();
        }


    }




    public Uri getPhotoUri() {
        try {
            Cursor cur = activity.getApplicationContext ().getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + "=" + this.getId() + " AND "
                            + ContactsContract.Data.MIMETYPE + "='"
                            + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'", null,
                    null);
            if (cur != null) {
                if (!cur.moveToFirst()) {
                    return null; // no photo
                }
            } else {
                return null; // error in cursor process
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long
                .parseLong(String.valueOf (getId ())));
        return Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
    }


}