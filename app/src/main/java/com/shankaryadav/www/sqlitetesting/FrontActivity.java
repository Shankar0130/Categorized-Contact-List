package com.shankaryadav.www.sqlitetesting;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.shankaryadav.www.sqlitetesting.fragments.CategoryFragment;
import com.shankaryadav.www.sqlitetesting.fragments.ContactsFragment;

public class FrontActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener () {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId ()) {
                case R.id.navigation_category:
                    getSupportFragmentManager ().beginTransaction ().replace (R.id.framelayout,new CategoryFragment ()).commit ();

                    return true;
                case R.id.navigation_contacts:
                    getSupportFragmentManager ().beginTransaction ().replace (R.id.framelayout,new ContactsFragment ()).commit ();

                    return true;
                case R.id.navigation_calls:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_category);
        BottomNavigationView navView = findViewById (R.id.nav_view);

        navView.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener);

        getSupportFragmentManager ().beginTransaction ().replace (R.id.framelayout,new CategoryFragment ()).commit ();
    }

}
