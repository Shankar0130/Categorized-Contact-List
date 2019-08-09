package com.shankaryadav.www.sqlitetesting.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shankaryadav.www.sqlitetesting.R;
import com.shankaryadav.www.sqlitetesting.pojo.Category_pojo;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ListViewHolder> {

    List<Category_pojo> list;
    Context context;

    public CategoryAdapter(List<Category_pojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from (viewGroup.getContext ());
        View view = inflater.inflate (R.layout.category_item,viewGroup,false);

        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();

        lp.width = viewGroup.getMeasuredWidth ()/2;

        view.setLayoutParams (lp);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
    listViewHolder.textView.setText (list.get (i).getCatename ());
    listViewHolder.imageView.setImageResource (list.get (i).getImg ());
    }

    @Override
    public int getItemCount() {
        return list.size ();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;


        public ListViewHolder(@NonNull View itemView) {
            super (itemView);

            textView = itemView.findViewById (R.id.cat_name);
            imageView = itemView.findViewById (R.id.cat_icons);


        }


    }




}
