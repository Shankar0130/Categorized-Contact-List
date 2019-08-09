package com.shankaryadav.www.sqlitetesting.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shankaryadav.www.sqlitetesting.R;
import com.shankaryadav.www.sqlitetesting.pojo.Contacts_pojo;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    List<Contacts_pojo> list;
    Context context;

    public ContactAdapter(List<Contacts_pojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from (viewGroup.getContext ());
        View view = inflater.inflate (R.layout.contacts_item,viewGroup,false);

        return new ContactViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {

        contactViewHolder.name.setText (list.get (i).getName ());
        contactViewHolder.cont.setText (list.get (i).getNo ());
        contactViewHolder.img.setImageURI (list.get (i).getImgurl ());

    }

    @Override
    public int getItemCount() {
        return list.size ();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,cont;

        public ContactViewHolder(@NonNull View itemView) {
            super (itemView);

            img = itemView.findViewById (R.id.profile_image);
            name = itemView.findViewById (R.id.name);
            cont = itemView.findViewById (R.id.number);
        }
    }
}
