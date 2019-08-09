package com.shankaryadav.www.sqlitetesting.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shankaryadav.www.sqlitetesting.Adapters.CategoryAdapter;
import com.shankaryadav.www.sqlitetesting.R;
import com.shankaryadav.www.sqlitetesting.pojo.Category_pojo;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    Activity activity;

    List<Category_pojo> list = new ArrayList<Category_pojo> () ;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =   inflater.inflate (R.layout.fragment_category, container, false);

        activity= getActivity ();

        list.add (new Category_pojo (R.drawable.friends,"Friends Persons"));
        list.add (new Category_pojo (R.drawable.family,"Family Persons"));
        list.add (new Category_pojo (R.drawable.network,"Business Persons"));
        list.add (new Category_pojo (R.drawable.utility,"Utility Persons"));
        list.add (new Category_pojo (R.drawable.others,"Others Persons"));

        recyclerView = view.findViewById (R.id.category_recycler_view);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new GridLayoutManager (activity,2));
        recyclerView.setItemAnimator (new DefaultItemAnimator ());

        recyclerView.addItemDecoration (new DividerItemDecoration (activity,DividerItemDecoration.VERTICAL));


        recyclerView.setAdapter (new CategoryAdapter (list,activity));


        return view;
    }

}
